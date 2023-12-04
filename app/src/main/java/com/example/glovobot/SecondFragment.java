package com.example.glovobot;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.glovobot.Model.AuthenticationRequest;
import com.example.glovobot.Model.AuthenticationResponse;
import com.example.glovobot.Model.OrderResponse;
import com.example.glovobot.databinding.FragmentSecondBinding;
import com.example.glovobot.services.GlovoApiService;
import com.example.glovobot.tasks.OrderPollingTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        android.util.Log.i("MyApplication", "onViewCreated executed");


        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    NavHostFragment.findNavController(SecondFragment.this)
                            .navigate(R.id.action_SecondFragment_to_FirstFragment);

                    String grantType = "password";
                    String userType = "courier";
                    String userName = "jchirinosfsrg@gmail.com";
                    String password = "jusdymar1";

                    AuthenticationRequest request = new AuthenticationRequest(grantType, password, userType, userName);
                    GlovoApiService glovoApiService = new GlovoApiService();

                    glovoApiService.authenticate(request).enqueue(new Callback<AuthenticationResponse>() {
                        @Override
                        public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {
                            if (response.isSuccessful()) {
                                android.util.Log.i("MyApplication", "OnResponse...");
                                int responseCode = response.code();

                                AuthenticationResponse authResponse = response.body();
                                android.util.Log.i("myApplication", "Response from /oauth/token " + responseCode);
                                String accessToken = authResponse.getAccessToken().toString();
                                glovoApiService.setAuthToken(accessToken);
                                //getOrders(authService);
                                //OrderPollingTask orderPollingTask = new OrderPollingTask(glovoApiService, 1000); // 1 second
                                //Thread orderPollingThread = new Thread(orderPollingTask);
                                //orderPollingThread.start();
                                //android.util.Log.i("MyApplication", "OrderPollingThread has been started. {}" + orderPollingThread.getId());


                            } else {
                                android.util.Log.w("myApplication", "Response is not gathered successfully! (/oauth/token)");

                            }
                        }

                        @Override
                        public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
                            android.util.Log.e("myApplication", "API call (/oauth/token) failed with the following error: " + t.getMessage());
                            android.util.Log.e("myApplication", "Stack trace: " + t);
                        }
                    });


                    //https://www.columbiasportswear.co.uk/p/womens-pike-lake-ii-cropped-puffer-jacket-2051361.html?dwvar_2051361_color=869
                } catch (Exception e) {
                    e.printStackTrace();
                    android.util.Log.e("myApplication","Exception: " + e.getMessage());
                    // Handle exceptions if any
                }


            }
        });
    }

    private void getOrders(GlovoApiService glovoApiService) {
        // Call getOrders using the accessToken
        glovoApiService.getOrders()
                .enqueue(new Callback<OrderResponse>() {
                    @Override
                    public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                        if (response.isSuccessful()) {
                            int responseCode = response.code();

                            OrderResponse orderResponse = response.body();

                        } else {
                            // Log the error message using SLF4J
                            Logger logger = LoggerFactory.getLogger(SecondFragment.class);
                            logger.error("API call failed with the following error: {}", response);

                        }
                    }

                    @Override
                    public void onFailure(Call<OrderResponse> call, Throwable t) {
                        // Handle failure in getOrders
                        Logger logger = LoggerFactory.getLogger(SecondFragment.class);
                        logger.error(t.getMessage());
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}