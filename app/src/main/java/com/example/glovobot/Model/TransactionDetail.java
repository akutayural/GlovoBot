package com.example.glovobot.Model;

public class TransactionDetail {
    public String location;
    public String paymentMethod;
    public String text;
    public Double price;
    public String reminder;

    public TransactionDetail(String location, String paymentMethod, String text, Double price, String reminder) {
        this.location = location;
        this.paymentMethod = paymentMethod;
        this.text = text;
        this.price = price;
        this.reminder = reminder;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }
}
