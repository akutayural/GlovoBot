package com.example.glovobot.GlovoApi;



import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

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
