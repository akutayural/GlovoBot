package com.example.glovobot.Model;

public class TaskRequest {
    private int battery_level;


    public TaskRequest(int batteryLevel) {
        this.battery_level = batteryLevel;
    }

    public int getBatteryLevel() {
        return battery_level;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.battery_level = batteryLevel;
    }
}
