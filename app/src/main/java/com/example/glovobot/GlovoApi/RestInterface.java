package com.example.glovobot.GlovoApi;

import com.example.glovobot.Model.AuthenticationRequest;
import com.example.glovobot.Model.AuthenticationResponse;
import com.example.glovobot.Model.DeliveryOrderRequest;
import com.example.glovobot.Model.OrderResponse;
import com.example.glovobot.Model.PickupOrderRequest;
import com.example.glovobot.Model.StartOrderRequest;
import com.example.glovobot.Model.StatusUpdateRequest;
import com.example.glovobot.Model.StepResponse;
import com.example.glovobot.Model.UpdateLocationRequest;
import com.example.glovobot.Model.UpdateLocationResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RestInterface {

    @POST("/oauth/token")
    Call<AuthenticationResponse> authenticate(@HeaderMap Map<String, String> headers, @Body AuthenticationRequest request);

    @GET("/v3/couriers/app/deliveries")
    Call<OrderResponse> getOrders(@HeaderMap Map<String, String> headers);

    @GET("/v3/deliveries/{id}/steps")
    Call<StepResponse> getDeliverySteps(@Path("id") String id, @HeaderMap Map<String, String> headers);
    @POST("/v3/courier/position")
    Call<Void> updateLocation(@HeaderMap Map<String, String> headers, @Body UpdateLocationRequest request);

    @POST("/v3/deliveries/{id}/start")
    Call<Object> startOrder(@Path("id") String id, @HeaderMap Map<String, String> headers, @Body StartOrderRequest request);

    @POST("/v3/couriers/online")
    Call<Object> statusUpdate(@HeaderMap Map<String, String> headers, @Body StatusUpdateRequest request);

    @POST("/v3/deliveries/{id}/request_reassignment")
    Call<Object> reAssignmentOrder(@Path("id") String id, @HeaderMap Map<String, String> headers, @Body StartOrderRequest request);

    @POST("/v3/deliveries/{id}/pickup_arrival")
    Call<Object> pickUpArrivel(@Path("id") Long id, @HeaderMap Map<String, String> headers, @Body PickupOrderRequest request);

    @POST("/v3/deliveries/{id}/orders/{id}/delivery")
    Call<Object> deliveryOrder(@Path("id") Long id, @HeaderMap Map<String, String> headers, @Body DeliveryOrderRequest request);

    /*
    @GET("v2/top-headlines")
    Call<TotalNews> getTotalNews(@Query("country") String country, @Query("apiKey") String apiKey);
     */
}
