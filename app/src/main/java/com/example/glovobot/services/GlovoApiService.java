package com.example.glovobot.services;
import com.example.glovobot.Model.AuthenticationRequest;
import com.example.glovobot.Model.AuthenticationResponse;
import com.example.glovobot.Model.DeliveryOrderRequest;
import com.example.glovobot.Model.Location;
import com.example.glovobot.Model.OrderResponse;
import com.example.glovobot.Model.PickupOrderRequest;
import com.example.glovobot.Model.StartOrderRequest;
import com.example.glovobot.Model.StatusUpdateRequest;
import com.example.glovobot.Model.StepResponse;
import com.example.glovobot.Model.UpdateLocationActivity;
import com.example.glovobot.Model.UpdateLocationRequest;
import com.example.glovobot.Model.UpdateLocationResponse;
import com.example.glovobot.helpers.UuidGenerator;
import com.example.glovobot.GlovoApi.ApiClient;
import com.example.glovobot.GlovoApi.RestInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;

public class GlovoApiService {
    private final RestInterface restInterface;
    private String token;
    private Long deliveryId;


    public GlovoApiService() {
        // Initialize the RestInterface using the ApiClient
        restInterface = ApiClient.getClient("https://api.glovoapp.com").create(RestInterface.class);
    }


    public Call<Void> setPosition(double lat, double lng){
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
        request.setTimestamp(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        request.setSpeed(0);
        request.setLatitude(Float.parseFloat(String.valueOf(lat)));
        request.setLongitude(Float.parseFloat(String.valueOf(lng)));
        request.setActivities(activities);
        return restInterface.updateLocation(getHeaders(), request);
    }

    public Call<Void> updateLocation(
            @Body UpdateLocationRequest request
    ) {
        return restInterface.updateLocation(getHeaders(), request);
    }
    public Call<AuthenticationResponse> authenticate(
            @Body AuthenticationRequest request
    ) {
        return restInterface.authenticate(getHeaders(), request);
    }

    private Map<String, String> getHeaders(){
        Map<String, String> headers = new HashMap<>();

        headers.put("Glovo-App-Platform", "iOS");
        headers.put("Glovo-App-Version", "2.205.0");
        headers.put("Glovo-Client-Info", "ios-courier/2.205.0-16127-app-store");
        headers.put("Glovo-Api-Version", "8");
        headers.put("Glovo-App-Type", "courier");
        headers.put("Glovo-Device-Osversion", "16.7.1");
        headers.put("Glovo-App-Development-State", "Production");
        headers.put("Glovo-App-Build", "16127");
        headers.put("User-Agent", "Glover/16127 CFNetwork/1410.0.3 Darwin/22.6.0");
        headers.put("Glovo-Language-Code", "es");
        headers.put("Glovo-Request-Id", UuidGenerator.generateUuidV4());
        headers.put("Glovo-Installation-Id", UuidGenerator.generateUuidV4());
        headers.put("Glovo-Device-Id", UuidGenerator.generateUuidV4());
        headers.put("Glovo-Dynamic-Session-Id", UuidGenerator.generateUuidV4());

        if(token != null){
            headers.put("Authorization", this.token);
        }

        return headers;
    }

    public void setAuthToken(String token){
        this.token = token;
    }

    public Call<OrderResponse> getOrders() {
        return restInterface.getOrders(getHeaders());
    }

    public Call<StepResponse> getDeliverySteps(String deliveryId){
        return restInterface.getDeliverySteps(deliveryId, getHeaders());
    }
    public Call<Object> deliveryOrder(Long deliveryId, String locationId, Long version, Long stepId){
        DeliveryOrderRequest req = new DeliveryOrderRequest();
        req.setLocationId(locationId);
        req.setPaymentMethod("NONE");
        req.setVersion(version);
        req.setStepId(stepId);

        return restInterface.deliveryOrder(deliveryId, getHeaders(), req);
    }



    public Call<Object> startOrder(long id, double latitude, double longitude) {
        StartOrderRequest request = new StartOrderRequest();
        request.setAccuracy(10.0);
        request.setLatitude(latitude);
        request.setLongitude(longitude);
        return restInterface.startOrder(String.valueOf(id), getHeaders(), request);
    }
    public Call<Object> reAssignmentOrder(long id){
        StartOrderRequest request = new StartOrderRequest();
        return restInterface.reAssignmentOrder(String.valueOf(id), getHeaders(), request);
    }


    public Call<Object> pickUpArrivel(Long id, String locationId, Long stepId, Double latitude, Double longtitude, long version){
        Location location = new Location();
        location.setLatitude(latitude);
        location.setLongitude(longtitude);

        PickupOrderRequest request = new PickupOrderRequest();
        request.setLocationId(locationId);
        request.setStepId(stepId);
        request.setLocation(location);
        request.setVersion(version);

        return restInterface.pickUpArrivel(id,getHeaders(), request);
    }


    public Call<Object> statusUpdate(boolean isOnline) {
        StatusUpdateRequest request = new StatusUpdateRequest();

        request.setStatus( isOnline ? "ONLINE" : "OFFLINE" );
        request.setSource("COURIER_PROFILE");
        request.setReason("COURIER_PROFILE");
        return restInterface.statusUpdate(getHeaders(), request);
    }




}
