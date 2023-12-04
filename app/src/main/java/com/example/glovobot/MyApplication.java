package com.example.glovobot;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.example.glovobot.tasks.OrderPollingTask;


public class MyApplication extends Application {
    private OrderPollingTask orderPollingTask;
    private Thread orderPollingThread;

    @Override
    public void onCreate() {
        super.onCreate();
        android.util.Log.i("myApplication", "Application has successfully opened!");

        // Initialize your application here
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private void onAppBackgrounded() {
        Log.d("MyApplication", "App in background");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private void onAppForegrounded() {
        Log.d("MyApplication", "App in foreground");
    }

    @Override
    public void onTerminate() {
        // This method is called when the application is about to be terminated
        // Stop the OrderPollingThread if it's running
        if (orderPollingTask != null && orderPollingThread != null) {
            orderPollingTask.stopThread();
            orderPollingTask = null;
            android.util.Log.i("myApplication", "Thread killed!");

        }

        android.util.Log.i("myApplication", "Application has successfully shut down!");
        super.onTerminate();

    }

    public void setOrderPollingTask(Thread thread) {
        this.orderPollingThread = thread;
    }
}
