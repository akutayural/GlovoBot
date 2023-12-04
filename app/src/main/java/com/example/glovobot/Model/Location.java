package com.example.glovobot.Model;

public class Location {
    private double latitude;
    private double longitude;
    private Pin pin;
    private String label;

    public Location(){

    }
    public Location(double latitude, double longitude, Pin pin, String label) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.pin = pin;
        this.label = label;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Pin getPin() {
        return pin;
    }

    public void setPin(Pin pin) {
        this.pin = pin;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
