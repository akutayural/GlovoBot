package com.example.glovobot;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import com.example.glovobot.Model.AuthenticationRequest;
import com.example.glovobot.Model.AuthenticationResponse;
import com.example.glovobot.services.GlovoApiService;
import com.example.glovobot.services.PythonApiService;
import com.example.glovobot.tasks.OrderPollingTask;
import com.example.glovobot.tasks.RemoteTaskService;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.glovobot.databinding.ActivityUser2Binding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_user extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityUser2Binding binding;
    private String deviceId;
    private String mail;
    private String password;
    private GlovoApiService glovoApiService = new GlovoApiService();

    private OrderPollingTask orderPollingTask;
    private LocationManager mLocationManager;
    private LocationListener mLocationListener;


    private void stop(){
        Intent serviceIntent = new Intent(this, RemoteTaskService.class);
        serviceIntent.putExtra("device_id", deviceId);
        serviceIntent.putExtra("mail", mail);
        serviceIntent.putExtra("password", password);

        try {
            stopService(serviceIntent);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Intent intent = this.getIntent();
        deviceId = intent.getStringExtra("device_id");
        mail = intent.getStringExtra("mail");
        password = intent.getStringExtra("password");


        stop();

        Intent serviceIntent = new Intent(this, RemoteTaskService.class);
        serviceIntent.setAction("start");
        serviceIntent.putExtra("device_id", deviceId);
        serviceIntent.putExtra("mail", mail);
        serviceIntent.putExtra("password", password);



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService( serviceIntent);
        } else {
            startService(serviceIntent);
        }



//        startTaskWatcher();
        Button logoutButton = findViewById(R.id.logoutButton);
        TextView mailMsg = findViewById(R.id.mailMsg);
        mailMsg.setText("You logged in as " + mail);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(orderPollingTask != null)
                    orderPollingTask.stopThread();

                if(mLocationManager != null && mLocationListener != null)
                    mLocationManager.removeUpdates(mLocationListener); // Stop location updates

                stop();

                Intent intent = new Intent(activity_user.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void startTaskWatcher() {
        String grantType = "password";
        String userType = "courier";
        String userName = mail;
        String password = this.password;

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
//                    orderPollingTask = new OrderPollingTask(glovoApiService, new PythonApiService(deviceId), 1000); // 1 second
                    //Thread orderPollingThread = new Thread(orderPollingTask);
                    //orderPollingThread.start();
                    //android.util.Log.i("MyApplication", "OrderPollingThread has been started. {}" + orderPollingThread.getId());


                } else {
                    Toast.makeText(activity_user.this, "Authentication to Glovo system failed. Please contact support.", Toast.LENGTH_LONG).show();

                    android.util.Log.w("myApplication", "Response is not gathered successfully! (/oauth/token)");

                }
            }

            @Override
            public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
                android.util.Log.e("myApplication", "API call (/oauth/token) failed with the following error: " + t.getMessage());
                android.util.Log.e("myApplication", "Stack trace: " + t);
            }
        });
    }

}