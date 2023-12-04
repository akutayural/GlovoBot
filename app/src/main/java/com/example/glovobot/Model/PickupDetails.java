package com.example.glovobot.Model;

public class PickupDetails {
    public String style;
    public String message;

    public PickupDetails(String style, String message) {
        this.style = style;
        this.message = message;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
