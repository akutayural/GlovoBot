package com.example.glovobot.Model;

public class OrderResponse {
    private final ActiveDeliveries activeDeliveries;
    private final CurrentDaySummary currentDaySummary;

    public OrderResponse(ActiveDeliveries activeDeliveries, CurrentDaySummary currentDaySummary) {
        this.activeDeliveries = activeDeliveries;
        this.currentDaySummary = currentDaySummary;
    }

    public ActiveDeliveries getActiveDelivers() {
        return activeDeliveries;
    }

    public CurrentDaySummary getCurrentDaySummary() {
        return currentDaySummary;
    }


}
