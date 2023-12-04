package com.example.glovobot.PythonApi;

import com.example.glovobot.Model.AuthenticationRequest;
import com.example.glovobot.Model.AuthenticationResponse;
import com.example.glovobot.Model.ClearRequest;
import com.example.glovobot.Model.DeliveryListInfo;
import com.example.glovobot.Model.GlovoTask;
import com.example.glovobot.Model.GlovoTaskData;
import com.example.glovobot.Model.NewMessageRequest;
import com.example.glovobot.Model.NewNotificationRequest;
import com.example.glovobot.Model.PythonAuthResponse;
import com.example.glovobot.Model.TaskRequest;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PythonRestInterface {

    @GET("/client/me")
    Call<PythonAuthResponse> auth(@HeaderMap Map<String, String> headers);

    @POST("client/update")
    Call<GlovoTaskData> fetchTask(@HeaderMap Map<String, String> headers, @Body TaskRequest request);
    //Call<AuthenticationResponse> authenticate(@HeaderMap Map<String, String> headers, @Body AuthenticationRequest request);
    @POST("client/newOrder")
    Call<GlovoTaskData> sendNewNotification(@HeaderMap Map<String, String> headers, @Body NewNotificationRequest request);
    @POST("client/sendMessage")
    Call<Void> sendMessage(@HeaderMap Map<String, String> headers, @Body NewMessageRequest request);
    @POST("client/clear")
    Call<Object> sendClear(@HeaderMap Map<String, String> headers, @Body ClearRequest request);
    @POST("client/deliveryList")
    Call<Object> sendDeliveryList(@HeaderMap Map<String, String> headers, @Body List<DeliveryListInfo> request);





    /*
    @GET("v2/top-headlines")
    Call<TotalNews> getTotalNews(@Query("country") String country, @Query("apiKey") String apiKey);
     */
}
