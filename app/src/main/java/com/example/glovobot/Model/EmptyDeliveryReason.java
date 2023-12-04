package com.example.glovobot.Model;

public class EmptyDeliveryReason {
    private String title;
    private String description;
    private String label;
    private String bookingStatus;

    public EmptyDeliveryReason(String title, String description, String label, String bookingStatus) {
        this.title = title;
        this.description = description;
        this.label = label;
        this.bookingStatus = bookingStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
