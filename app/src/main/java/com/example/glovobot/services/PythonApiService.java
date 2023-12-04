package com.example.glovobot.services;

import com.example.glovobot.Model.ClearRequest;
import com.example.glovobot.Model.DeliveryListInfo;
import com.example.glovobot.Model.GlovoTaskData;
import com.example.glovobot.Model.NewMessageRequest;
import com.example.glovobot.Model.NewMessageRequestParams;
import com.example.glovobot.Model.NewNotificationRequest;
import com.example.glovobot.Model.PythonAuthResponse;
import com.example.glovobot.Model.TaskRequest;
import com.example.glovobot.PythonApi.PythonApiClient;
import com.example.glovobot.PythonApi.PythonRestInterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

public class PythonApiService {
    private final PythonRestInterface pythonRestInterface;
    private String deviceId;

    public PythonApiService(String deviceId) {
        pythonRestInterface = PythonApiClient.getClient("http://103.45.245.50:8000").create(PythonRestInterface.class);
        this.deviceId = deviceId;
    }

    public void setDeviceId(String deviceId) {
        android.util.Log.i("myApplication", "DeviceId Setted! " + deviceId);
        this.deviceId = deviceId;
    }

    public Call<PythonAuthResponse> auth() {
        return pythonRestInterface.auth(getHeaders());
    }


    public Call<GlovoTaskData> fetchTask(TaskRequest request){
        return pythonRestInterface.fetchTask(getHeaders(), request);
    }
    public Call<GlovoTaskData> sendNewNotification(NewNotificationRequest request){
        return pythonRestInterface.sendNewNotification(getHeaders(), request);
    }
    public Call<Void> sendMessage(NewMessageRequest request){
        return pythonRestInterface.sendMessage(getHeaders(), request);
    }

    public Call<Object> sendClear(){
        ClearRequest request = new ClearRequest();
        return pythonRestInterface.sendClear(getHeaders(), request);
    }

    public void sendDirectMessage(String message){


        NewMessageRequestParams requestParams = new NewMessageRequestParams();


        NewMessageRequest request = new NewMessageRequest();
        request.setType("DIRECT");
        request.setMessage(message);
        request.setParams(requestParams);
        try {
            pythonRestInterface.sendMessage(getHeaders(), request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private Map<String, String> getHeaders(){
        Map<String, String> headers = new HashMap<>();
        headers.put("Device-Id", this.deviceId);

        return headers;
    }


    public void sendDeliveryList(List<DeliveryListInfo> deliveryList) {
        try {
            pythonRestInterface.sendDeliveryList(getHeaders(), deliveryList).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
