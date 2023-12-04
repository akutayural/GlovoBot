package com.example.glovobot.Model;

import java.util.List;

public class StepData {
    private List<Location> points;
    public StoreDetails storeDetails;
    public List<Order> orders;
    public OrderReadyInfo orderReadyInfo;
    public Header header;
    public InfoBox infoBox;
    public String name;

    public List<Location> getPoints() {
        return points;
    }

    public void setPoints(List<Location> points) {
        this.points = points;
    }

    public StoreDetails getStoreDetails() {
        return storeDetails;
    }

    public void setStoreDetails(StoreDetails storeDetails) {
        this.storeDetails = storeDetails;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public OrderReadyInfo getOrderReadyInfo() {
        return orderReadyInfo;
    }

    public void setOrderReadyInfo(OrderReadyInfo orderReadyInfo) {
        this.orderReadyInfo = orderReadyInfo;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public InfoBox getInfoBox() {
        return infoBox;
    }

    public void setInfoBox(InfoBox infoBox) {
        this.infoBox = infoBox;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
