package com.example.glovobot.Model;

public class Pin {
    private double latitude;
    private double longitude;
    private String label;
    private String style;

    public Pin(double latitude, double longitude, String label, String style) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.label = label;
        this.style = style;
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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
