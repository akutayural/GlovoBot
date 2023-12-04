package com.example.glovobot.PythonApi;



import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;


public class PythonApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String apiBaseUrl) {


        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient();

            retrofit = new Retrofit.Builder()
                    .baseUrl(apiBaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }

}
