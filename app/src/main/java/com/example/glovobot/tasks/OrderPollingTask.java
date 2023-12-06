package com.example.glovobot.tasks;

import android.location.Location;

import com.example.glovobot.Model.AuthenticationRequest;
import com.example.glovobot.Model.AuthenticationResponse;
import com.example.glovobot.Model.Delivery;
import com.example.glovobot.Model.DeliveryListInfo;
import com.example.glovobot.Model.GlovoTask;
import com.example.glovobot.Model.GlovoTaskData;
import com.example.glovobot.Model.NewMessageRequest;
import com.example.glovobot.Model.NewMessageRequestParams;
import com.example.glovobot.Model.NewNotificationRequest;
import com.example.glovobot.Model.Point;
import com.example.glovobot.Model.StepResponse;
import com.example.glovobot.Model.Steps;
import com.example.glovobot.Model.TaskRequest;
import com.example.glovobot.Model.TaskType;
import com.example.glovobot.Model.UpdateLocationActivity;
import com.example.glovobot.Model.UpdateLocationRequest;

import com.example.glovobot.services.GlovoApiService;
import com.example.glovobot.Model.OrderResponse;
import com.example.glovobot.services.PythonApiService;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import retrofit2.Response;

public class OrderPollingTask implements Runnable {
    private final GlovoApiService glovoApiService;
    private final PythonApiService pythonApiService;
    private final long pollingInterval; // in milliseconds
    private volatile boolean isRunning = true;
    private final Thread orderPollingThread;
    private List<GlovoTask> glovoTasks;
    private final String device_id;
    private final String mail;
    private final String password;
    private long tokenExpireTs;
    private String token;
    private final List<Integer> acceptedOrderIds;
    private final List<Integer> processedTaskIds;
    private final List<Delivery> deliveries;
    private boolean isCourierOnline;
    private Location currentLocation;

    public OrderPollingTask(String device_id, String mail, String password, GlovoApiService glovoApiService,PythonApiService pythonApiService, long pollingInterval) {
        this.glovoApiService = glovoApiService;
        this.pollingInterval = pollingInterval;
        isCourierOnline = false;
        this.device_id = device_id;
        this.password = password;
        this.mail = mail;
        processedTaskIds = new ArrayList<>();
        deliveries = new ArrayList<>();
        acceptedOrderIds = new ArrayList<>();
        orderPollingThread = new Thread(this);
        orderPollingThread.start();
//        MyApplication myApplication = new MyApplication();
//        myApplication.setOrderPollingTask(orderPollingThread);
//
        this.pythonApiService = pythonApiService;

    }

    private void fetchTasks(){
        Response<GlovoTaskData> response = null;
        try {
            response = this.pythonApiService.fetchTask(new TaskRequest(0)).execute();
            if (response.isSuccessful()){
                int statusCode = response.code();

                if (statusCode == 200){
                    android.util.Log.i("myApplication", "Response successfully gathered from /client/update");
                }
                GlovoTaskData glovoTaskData = response.body();
                List<GlovoTask> remoteTasks = glovoTaskData.getData().getTask_response();
                glovoTasks = remoteTasks;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private long getTs(){
        return TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
    }

    private boolean makeSureGlovoAuth(){
        if( getTs() < this.tokenExpireTs )
            return true;

        glovoApiService.setAuthToken(null);
        String grantType = "password";
        String userType = "courier";
        String userName = mail;
        String password = this.password;
        AuthenticationRequest request = new AuthenticationRequest(grantType, password, userType, userName);
        try {
            Response<AuthenticationResponse> authResponse = glovoApiService.authenticate(request).execute();
            if(authResponse.isSuccessful()){
                String accessToken = Objects.requireNonNull(authResponse.body()).getAccessToken();
                glovoApiService.setAuthToken(accessToken);

                this.tokenExpireTs =getTs() + authResponse.body().getExpiresIn();
                this.token = accessToken;

                return true;
            }
        } catch (IOException e) {
            return false;
        }
        return false;


//        GlovoApiService glovoApiService = new GlovoApiService();

        //getOrders(authService);
        //Thread orderPollingThread = new Thread(orderPollingTask);
        //orderPollingThread.start();
        //android.util.Log.i("MyApplication", "OrderPollingThread has been started. {}" + orderPollingThread.getId());
        //                    Toast.makeText(activity_user.this, "Authentication to Glovo system failed. Please contact support.", Toast.LENGTH_LONG).show();
    }
    private void sendRealTimeLocation(){
        if(isCourierOnline){
            if(currentLocation != null){
                boolean foundOrderTask = false;

                if(glovoTasks != null){

                    for(GlovoTask task : glovoTasks){
                        if(task.getType() == TaskType.CHECK_ORDERS){
                            foundOrderTask = true;
                            break;
                        }
                    }


                }else
                    foundOrderTask = true;


                if(foundOrderTask){
                    sendUpdateLocation(String.valueOf(currentLocation.getLatitude()), String.valueOf(currentLocation.getLongitude()));
                    sendCheckOrders();
                }

            }
        }
    }
    @Override
    public void run() {
        //Make user online at the first login. Then the user can change their status with /offline or /online
        isCourierOnline = true;
        try {
            pythonApiService.sendClear().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        while (isRunning) {
            try {
                android.util.Log.i("MyApplication", "OrderPollingTask : start ============= {}" + orderPollingThread.getId());
                Thread.sleep(pollingInterval);
                fetchTasks();
                sendRealTimeLocation();


                if(glovoTasks == null || glovoTasks.size() == 0)
                    continue;
                android.util.Log.i("MyApplication", "OrderPollingTask : makeSureAuth {}" + orderPollingThread.getId());

                if(!makeSureGlovoAuth())
                    continue;

                android.util.Log.i("MyApplication", "OrderPollingTask : orderTasks for {}" + orderPollingThread.getId());



                for(GlovoTask task : glovoTasks){
                    if(TaskType.CHECK_ORDERS.equals(task.getType()))
                        taskDoCheckOrders(task);
                    else if(TaskType.ACCEPT_ORDER.equals(task.getType()))
                        taskDoAcceptOrders(task);
                    else if(TaskType.CANCEL_ORDER.equals(task.getType()))
                        taskDoCancelOrders(task);
                    else if(TaskType.PICKUP_ORDER.equals(task.getType()))
                        taskDoPickupOrders(task);
                    else if(TaskType.STATUS_UPDATE.equals(task.getType()))
                        taskDoStatusUpdate(task);
                    else if(TaskType.REQUEST_DELIVERY_LIST.equals(task.getType()))
                        taskDoRequestDeliveryList(task);
                    else if(TaskType.DELIVERY_ORDER.equals(task.getType()))
                        taskDoDeliveryOrder(task);


                }





            } catch (InterruptedException e) {
                // Handle the InterruptedException
                e.printStackTrace();
            }
        }
    }




    private void sendUpdateLocation(String lat, String lng) {
        UpdateLocationActivity activity = new UpdateLocationActivity();
        activity.setConfidence(0);
        activity.setTypes("");
        activity.setConfidence(0);

        List<UpdateLocationActivity> activities = new ArrayList<>();
        activities.add(activity);


        UpdateLocationRequest request = new UpdateLocationRequest();
        request.setAccuracy(10);
        request.setBatteryLevel(100);
        request.setBatteryCharging(true);
        request.setTimestamp(getTs());
        request.setSpeed(0);
        request.setLatitude(Float.parseFloat(lat));
        request.setLongitude(Float.parseFloat(lng));
        request.setActivities(activities);

        try {
            this.glovoApiService.updateLocation(request).execute();
        } catch (IOException e) {
            android.util.Log.i("myApplication", "glovoApiService.updateLocation]");

        }

    }
    private void sendCheckOrders(){
        Response<OrderResponse> response = null;
        try {
            response = this.glovoApiService.getOrders().execute();
            if(response.isSuccessful()){
                OrderResponse orderResponse = response.body();
                android.util.Log.i("myApplication", "Response from (/v3/couriers/app/deliveries) is successful [" + response.code() + "]");

                if (orderResponse != null && orderResponse.getActiveDelivers().getDeliveries() != null) {
                    List<Delivery> deliveryList = orderResponse.getActiveDelivers().getDeliveries();
                    for (int i = 0; i < deliveryList.size(); i++) {
                        Delivery delivery = deliveryList.get(i);

                        if( deliveries.stream().anyMatch(d->d.getId() == delivery.getId()) )
                            continue;

                        long deliveryId = deliveryList.get(i).getId();
                        String deliveryIdString = String.valueOf(deliveryId);

                        Response<StepResponse> responseStep = glovoApiService.getDeliverySteps(deliveryIdString).execute();

                        if(!responseStep.isSuccessful())
                            continue;

                        StepResponse stepData = responseStep.body();

                        NewNotificationRequest notificationRequest = new NewNotificationRequest();
                        notificationRequest.setId(delivery.getId());
                        notificationRequest.setDeliveryCode(delivery.getDeliveryCode());
                        notificationRequest.setDeliveryType(delivery.getDeliveryType());
                        notificationRequest.setTotalDistance(delivery.getTotalDistance());
                        notificationRequest.setPoints(delivery.getPoints());
                        notificationRequest.setTotalCompensation(delivery.getTotalCompensation());
                        pythonApiService.sendNewNotification(notificationRequest).execute();
                        deliveries.add(delivery);

                        android.util.Log.i("myApplication", "Response from (step) is successful [" + responseStep.code() + "]");



                    }
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//
//                .enqueue(new Callback<OrderResponse>() {
//            @Override
//            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
//                if (response.isSuccessful()) {
//                    int responseCode = response.code();
//                    OrderResponse orderResponse = response.body();
//                    android.util.Log.i("myApplication", "Response from (/v3/couriers/app/deliveries) is successful [" + responseCode + "]");
//
//                    if (orderResponse != null && orderResponse.getActiveDelivers().getDeliveries() != null) {
//                        List<Delivery> deliveryList = orderResponse.getActiveDelivers().getDeliveries();
//                        for (int i = 0; i < deliveryList.size(); i++) {
//                            long deliveryId = deliveryList.get(i).getId();
//                            String deliveryIdString = String.valueOf(deliveryId);
//                            glovoApiService.getDeliverySteps(deliveryIdString).enqueue(new Callback<StepResponse>() {
//                                @Override
//                                public void onResponse(Call<StepResponse> call, Response<StepResponse> response) {
//                                    if (response.isSuccessful()) {
//                                        StepResponse stepResponse = response.body();
//                                        android.util.Log.i("myApplication", "Response from (/v3/deliveries/:id/steps) is successful [" + responseCode + "]");
//
//
//                                    }else {
//                                        android.util.Log.w("myApplication", "Response is not gathered successfully! (/v3/deliveries/:id/steps)");
//                                    }
//                                }
//
//                                @Override
//                                public void onFailure(Call<StepResponse> call, Throwable t) {
//                                    android.util.Log.e("myApplication", "API call (/v3/deliveries/:id/steps) failed with the following error: " + t.getMessage());
//                                    android.util.Log.e("myApplication", "Stack trace: " + t);
//                                }
//                            });
//
//
//                        }
//                    }
//                } else {
//                    android.util.Log.w("myApplication", "Response is not gathered successfully! (/v3/couriers/app/deliveries)");
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<OrderResponse> call, Throwable t) {
//                android.util.Log.e("myApplication", "API call (/v3/couriers/app/deliveries) failed with the following error: " + t.getMessage());
//                android.util.Log.e("myApplication", "Stack trace: " + t);
//            }
//        });

    }

    private void taskDoDeliveryOrder(GlovoTask task){
        JsonObject params = task.getParams();
        if(processedTaskIds.contains(task.getId()))
            return;
        Long deliveryId = params.get("id").getAsLong();
        long stepId = params.get("stepId").getAsLong();


        Optional<Delivery> delivery = deliveries.stream().filter(e->e.getOrderId() == deliveryId).findFirst();

        try {
            Response<StepResponse> stepResponse = glovoApiService.getDeliverySteps(String.valueOf(deliveryId)).execute();

            if(stepResponse.isSuccessful()){
                Optional<Steps> step = Objects.requireNonNull(stepResponse.body()).getSteps().stream().filter(t->t.getId() == stepId).findFirst();
                if(step.isPresent()){
                    Response<Object> response =glovoApiService.deliveryOrder(deliveryId, step.get().getLocationId(), stepResponse.body().getVersion(), stepId).execute();
                    if(response.isSuccessful()){
                        if(delivery.isPresent()){
                            pythonApiService.sendDirectMessage("Entregastes la orden con exito.#" + delivery.get().getDeliveryCode());
                        }
                        else
                            pythonApiService.sendDirectMessage("Successfully delivered.");
                    }

                }


            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void taskDoRequestDeliveryList(GlovoTask task) {
        JsonObject params = task.getParams();
        if(processedTaskIds.contains(task.getId()))
            return;

        List<Integer> acceptedOrderIds = this.acceptedOrderIds.stream().collect(Collectors.toList());
        if(acceptedOrderIds.isEmpty()){
            pythonApiService.sendDirectMessage("Could not find any accepted orders.");
            return;
        }

        List<StepResponse> stepResponses = new ArrayList<>();

        for(Integer order_id : acceptedOrderIds){

            try {
                Response<StepResponse> response = glovoApiService.getDeliverySteps(order_id.toString()).execute();
                if(response.isSuccessful())
                    stepResponses.add(response.body());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if(stepResponses.isEmpty()){
            pythonApiService.sendDirectMessage("Could not fetch any accepted orders, try again");
            return;
        }

        List<DeliveryListInfo> deliveryList = new ArrayList<>();
        for(StepResponse stepResponse : stepResponses){

            List<Steps> dropOffSteps = stepResponse.getSteps().stream().filter(t -> t.getName().equalsIgnoreCase("DROP_OFF") || t.getName().equalsIgnoreCase("GO_TO_SINGLE_PICKUP_POINT")).collect(Collectors.toList());
            for(Steps step : dropOffSteps){
                Optional<Delivery> delivery = deliveries.stream().filter(t->t.getId() == step.getOrderId()).findFirst();
                if(!delivery.isPresent())
                    continue;

                String buttonText = String.format("%s - %s",delivery.get().getDeliveryCode(), step.getTitle());
                long stepId = step.getId();
                long orderId = step.getOrderId();

                DeliveryListInfo listInfo = new DeliveryListInfo();
                listInfo.setButtonText(buttonText);
                listInfo.setOrderId(orderId);
                listInfo.setStepId(stepId);
                deliveryList.add(listInfo);





            }
        }

        pythonApiService.sendDeliveryList(deliveryList);

    }
    private void taskDoAcceptOrders(GlovoTask task){
        JsonObject params = task.getParams();
        android.util.Log.i("MyApplication", "OrderPollingTask : CheckOrders:taskDoAcceptOrders {}" + orderPollingThread.getId());
        Integer orderId = params.get("order_id").getAsInt();
        Optional<Delivery> delivery = deliveries.stream().filter(d->d.getId() == orderId).findFirst();
        if(!delivery.isPresent())
            return;

        if(acceptedOrderIds.contains(orderId)){
            List< Point > points = delivery.get().getPoints();
            if(points.size() > 0){
                glovoApiService.setPosition(points.get(0).getLatitude(), points.get(0).getLongitude());
                android.util.Log.i("MyApplication", "OrderPollingTask : setPosition {}" + task.getId());
            }
            return;
        }




        NewMessageRequestParams requestParams = new NewMessageRequestParams();
        requestParams.setId(orderId);
        requestParams.setDeliveryCode(delivery.get().getDeliveryCode());


        NewMessageRequest request = new NewMessageRequest();
        request.setType("ORDER_ACCEPTED");
        request.setParams(requestParams);
        try {
            Response<Object> response = glovoApiService.startOrder(delivery.get().getId(), delivery.get().getPoints().get(0).getLatitude(),  delivery.get().getPoints().get(0).getLongitude()).execute();
            if(!response.isSuccessful()){
                pythonApiService.sendDirectMessage("Failed to accept order.");
            }else{
                pythonApiService.sendMessage(request).execute();
            }
            acceptedOrderIds.add(orderId);
        } catch (IOException e) {

            pythonApiService.sendDirectMessage("Failed to accept order.");
        }
    }
    private void taskDoCheckOrders(GlovoTask task) {
        JsonObject params = task.getParams();
        android.util.Log.i("MyApplication", "OrderPollingTask : CheckOrders:sendUpdateLocation {}" + orderPollingThread.getId());

        sendUpdateLocation(params.get("lat").getAsString(), params.get("lng").getAsString());
        android.util.Log.i("MyApplication", "OrderPollingTask : CheckOrders:sendCheckOrders {}" + orderPollingThread.getId());

        sendCheckOrders();


        // Call the getOrders API using the authService


    }

    private void taskDoCancelOrders(GlovoTask task) {
        JsonObject params = task.getParams();
        int orderId = params.get("order_id").getAsInt();
        if(processedTaskIds.contains(task.getId()))
            return;

        try {
            Response<Object> response = glovoApiService.reAssignmentOrder(orderId).execute();
            if(response.isSuccessful()){
                pythonApiService.sendDirectMessage("Order cancelled successfully.");
                processedTaskIds.add(task.getId());
            }else
                pythonApiService.sendDirectMessage("Order cancelled failed, try again.");

        } catch (IOException e) {
            pythonApiService.sendDirectMessage("Order cancelled failed, try again.");

        }


    }

    private void taskDoPickupOrders(GlovoTask task) {
        JsonObject params = task.getParams();
        Long orderId = params.get("order_id").getAsLong();
        if(processedTaskIds.contains(task.getId()))
            return;

        try {

            Response<StepResponse> stepResponse = glovoApiService.getDeliverySteps(orderId.toString()).execute();
            if(stepResponse.isSuccessful()){
                List<Steps> steps = Objects.requireNonNull(stepResponse.body()).getSteps();
                if(steps.size() > 0){
                    Steps firstStep = steps.get(0);
                    com.example.glovobot.Model.Location stepLocation = firstStep.getLocation();
                    Long stepId = firstStep.getId();
                    Long stepOrderId = firstStep.getOrderId();

                    if(stepLocation != null){
                        glovoApiService.setPosition(stepLocation.getLatitude(), stepLocation.getLongitude()).execute();
                        glovoApiService.pickUpArrivel(orderId, firstStep.getLocationId(), stepId, stepLocation.getLatitude(), stepLocation.getLongitude(), stepResponse.body().getVersion()).execute();
                    }else{
                        com.example.glovobot.Model.Location firstStepLocation = firstStep.getData().getPoints().get(0);
                        glovoApiService.setPosition(firstStepLocation.getLatitude(), firstStepLocation.getLongitude());
                        glovoApiService.pickUpArrivel(orderId, firstStep.getLocationId(), stepId, firstStepLocation.getLatitude(), firstStepLocation.getLongitude(), stepResponse.body().getVersion()).execute();


                    }

                    pythonApiService.sendDirectMessage("Estás en el sitio de ent88rega, por favor espera a que el pedido esté listo.");
                    this.acceptedOrderIds.removeIf(e->e == Integer.valueOf( orderId.toString() ));
                }else
                    pythonApiService.sendDirectMessage("Failed to pickUp order.");



            }
            processedTaskIds.add(task.getId());
        } catch (IOException e) {
            pythonApiService.sendDirectMessage("Failed to pickUp order.");
        }
    }
    private void taskDoStatusUpdate(GlovoTask task){
        android.util.Log.i("MyApplication", "taskDoStatusUpdate {}" + orderPollingThread.getId());


        if(processedTaskIds.contains(task.getId()))
            return;
        JsonObject params = task.getParams();
        boolean isOnline = params.get("operation").getAsString().equals("ONLINE");
        try {
            this.glovoApiService.statusUpdate(isOnline).execute();
            processedTaskIds.add(task.getId());
            isCourierOnline = isOnline;
        } catch (IOException e) {

        }
    }

    public void stopThread(){
        try{
            isRunning = false;
            if(this.orderPollingThread != null)
                this.orderPollingThread.join();
        }catch (Exception e){

        }
    }

    public Thread getOrderPollingThread() {
        return orderPollingThread;
    }

    public void setCurrentLocation(Location location) {
        this.currentLocation = location;
    }
}
