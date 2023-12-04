package com.example.glovobot.Model;

public class DeliveryListInfo {
    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    public long getStepId() {
        return stepId;
    }

    public void setStepId(long stepId) {
        this.stepId = stepId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    private String buttonText;
    private long stepId;
    private long orderId;

}
