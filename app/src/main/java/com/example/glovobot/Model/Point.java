package com.example.glovobot.Model;

public class Point {
    private boolean isDelivery;
    private double latitude;
    private double longitude;
    private String label;
    private boolean obfuscated;
    private Object banner;

    public Point(boolean isDelivery, double latitude, double longitude, String label, boolean obfuscated, Object banner) {
        this.isDelivery = isDelivery;
        this.latitude = latitude;
        this.longitude = longitude;
        this.label = label;
        this.obfuscated = obfuscated;
        this.banner = banner;
    }

    public boolean isDelivery() {
        return isDelivery;
    }

    public void setDelivery(boolean delivery) {
        isDelivery = delivery;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isObfuscated() {
        return obfuscated;
    }

    public void setObfuscated(boolean obfuscated) {
        this.obfuscated = obfuscated;
    }

    public Object getBanner() {
        return banner;
    }

    public void setBanner(Object banner) {
        this.banner = banner;
    }
}
