package com.example.glovobot.Model;

public class InfoBox {
    public String bodyText;
    public String helperText;
    public String action;

    public InfoBox(String bodyText, String helperText, String action) {
        this.bodyText = bodyText;
        this.helperText = helperText;
        this.action = action;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public String getHelperText() {
        return helperText;
    }

    public void setHelperText(String helperText) {
        this.helperText = helperText;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
