package com.example.glovobot.Model;

public class Steps {
    private long id;
    private long orderId;
    private String name;
    private Object completedAt;
    private Boolean completed;
    private Object checkedInAt;
    private Location location;
    private String title;
    private String subtitle;
    private Object formattedSubtitle;
    private Object locationDetails;
    private Details details;
    private Button button;
    private String confirmationLabel;
    private Acceptance acceptance;
    private String locationId;
    private StepData data;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Object completedAt) {
        this.completedAt = completedAt;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Object getCheckedInAt() {
        return checkedInAt;
    }

    public void setCheckedInAt(Object checkedInAt) {
        this.checkedInAt = checkedInAt;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Object getFormattedSubtitle() {
        return formattedSubtitle;
    }

    public void setFormattedSubtitle(Object formattedSubtitle) {
        this.formattedSubtitle = formattedSubtitle;
    }

    public Object getLocationDetails() {
        return locationDetails;
    }

    public void setLocationDetails(Object locationDetails) {
        this.locationDetails = locationDetails;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public String getConfirmationLabel() {
        return confirmationLabel;
    }

    public void setConfirmationLabel(String confirmationLabel) {
        this.confirmationLabel = confirmationLabel;
    }

    public Acceptance getAcceptance() {
        return acceptance;
    }

    public void setAcceptance(Acceptance acceptance) {
        this.acceptance = acceptance;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public StepData getData() {
        return data;
    }

    public void setData(StepData data) {
        this.data = data;
    }
}
