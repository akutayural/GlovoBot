package com.example.glovobot.Model;

import java.util.List;

public class NewNotificationRequest {
    private Long id;
    private String totalCompensation;
    private String deliveryType;

    private String deliveryCode;

    private String totalDistance;
    private List<Point> points;
    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTotalCompensation() {
        return totalCompensation;
    }

    public void setTotalCompensation(String totalCompensation) {
        this.totalCompensation = totalCompensation;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(String totalDistance) {
        this.totalDistance = totalDistance;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
