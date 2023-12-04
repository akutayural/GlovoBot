package com.example.glovobot.Model;

import java.util.List;

public class UpdateLocationRequest {
        private float latitude;
        private float longitude;
        // private String reactionType; // Uncomment if needed
        private double accuracy;
        private int batteryLevel;
        private boolean batteryCharging;
        private long timestamp;
        private List<UpdateLocationActivity> activities;
        private double speed;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public boolean isBatteryCharging() {
        return batteryCharging;
    }

    public void setBatteryCharging(boolean batteryCharging) {
        this.batteryCharging = batteryCharging;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<UpdateLocationActivity> getActivities() {
        return activities;
    }

    public void setActivities(List<UpdateLocationActivity> activities) {
        this.activities = activities;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
