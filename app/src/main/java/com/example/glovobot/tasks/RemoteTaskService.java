package com.example.glovobot.tasks;

import static android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import com.example.glovobot.MainActivity;
import com.example.glovobot.R;
import com.example.glovobot.activity_user;
import com.example.glovobot.services.GlovoApiService;
import com.example.glovobot.services.PythonApiService;

public class RemoteTaskService extends Service {
    private OrderPollingTask orderPollingTask;
    private LocationManager mLocationManager;
    private LocationListener mLocationListener;
    int LOCATION_REFRESH_TIME = 4000; // 15 seconds to update
    int LOCATION_REFRESH_DISTANCE = 50; // 500 meters to update
    private PowerManager.WakeLock wakeLock;
    private boolean isServiceStarted;

    @Override
    public void onCreate() {
        super.onCreate();
        startForeground();
    }

    public void startForeground(){
        Notification notification = createNotification();

        if (Build.VERSION.SDK_INT >= 29) {
            startForeground(111, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_LOCATION | FOREGROUND_SERVICE_TYPE_DATA_SYNC);
        } else {
            startForeground(111, notification);
        }
    }

    @Override
    public  int onStartCommand(Intent intent,  int flags, int startId) {

        startForeground();
        startServicethread(intent);
        return START_STICKY;

//
//
//
//        if(intent !=null) {
//            android.util.Log.i("MyApplication", "onStartCommand " + intent.getAction() + " flag" + flags);
//
//
//            String action = intent.getAction();
//            android.util.Log.i("MyApplication","using an intent with action " + action);
//            if ("start".equals(action)) {
//                startServicethread(intent);
//            } else if ("stop".equals(action)) {
//                stopService();
//            } else {
//                android.util.Log.i("MyApplication","This should never happen. No action in the received intent");
//            }
//
//        }
//
//        return START_STICKY;
    }




    private void startServicethread(Intent intent) {
        if (isServiceStarted) return;
        android.util.Log.i("MyApplication","Starting the foreground service task");
        isServiceStarted = true;
        // setServiceState(this, ServiceState.STARTED);

        // we need this lock so our service gets not affected by Doze Mode
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        if (powerManager != null) {
            wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "EndlessService::lock");
            wakeLock.acquire();
        }


        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            mLocationListener = new LocationListener() {
                @Override
                public void onLocationChanged(@NonNull Location location) {
                    orderPollingTask.setCurrentLocation(location);
                }
                @Override
                public void onProviderEnabled(@NonNull String provider) {

                }

                @Override
                public void onProviderDisabled(@NonNull String provider) {

                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

            };

            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME,
                        LOCATION_REFRESH_DISTANCE, mLocationListener);
            }
        }



        String deviceId = intent.getStringExtra("device_id");
        String mail = intent.getStringExtra("mail");
        String password = intent.getStringExtra("password");
        orderPollingTask = new OrderPollingTask(deviceId, mail, password, new GlovoApiService(), new PythonApiService(deviceId), 500); // 1 second

    }
    @Override
    public void onDestroy() {
        stopService();
        super.onDestroy();
    }
    private void stopService() {
        android.util.Log.i("MyApplication","Stopping the foreground service");
        try {
            if (wakeLock != null && wakeLock.isHeld()) {
                wakeLock.release();
            }
            stopForeground(true);
            stopSelf();
        } catch (Exception e) {
            android.util.Log.i("MyApplication","Service stopped without being started: " + e.getMessage());
        }
        isServiceStarted = false;
        if(orderPollingTask != null)
            orderPollingTask.stopThread();

        // setServiceState(this, ServiceState.STOPPED);
    }

    private Notification createNotification() {
        String notificationChannelId = "com.example.glovobot.notifys2";

        // depending on the Android API that we're dealing with, we will have
        // to use a specific method to create the notification
        NotificationCompat.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel(
                    notificationChannelId,
                    "Watches your orders.",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription("GlovoBot");
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            notificationManager.createNotificationChannel(channel);

            builder = new NotificationCompat.Builder(this, notificationChannelId);
        } else {
            builder = new NotificationCompat.Builder(this);
        }

        Intent notificationIntent = new Intent(this, activity_user.class);
        PendingIntent pendingIntent =
        PendingIntent.getActivity(
                this,
                0,
                notificationIntent,
                PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT // setting the mutability flag
        );
        return builder.setContentTitle("GlovoBot")
                .setContentText("We watch orders.")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("GlovoBot")
                .setPriority(NotificationCompat.PRIORITY_HIGH) // for under android 26 compatibility
                .build();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
