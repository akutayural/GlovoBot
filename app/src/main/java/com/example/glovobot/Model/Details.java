package com.example.glovobot.Model;

public class Details {
    private String bodyText;
    private Object helperText;
    private String trailingText;
    private String trailingIcon;
    private String additionalIcon;
    private String leadingElement;

    public Details(String bodyText, Object helperText, String trailingText, String trailingIcon, String additionalIcon, String leadingElement) {
        this.bodyText = bodyText;
        this.helperText = helperText;
        this.trailingText = trailingText;
        this.trailingIcon = trailingIcon;
        this.additionalIcon = additionalIcon;
        this.leadingElement = leadingElement;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public Object getHelperText() {
        return helperText;
    }

    public void setHelperText(Object helperText) {
        this.helperText = helperText;
    }

    public String getTrailingText() {
        return trailingText;
    }

    public void setTrailingText(String trailingText) {
        this.trailingText = trailingText;
    }

    public String getTrailingIcon() {
        return trailingIcon;
    }

    public void setTrailingIcon(String trailingIcon) {
        this.trailingIcon = trailingIcon;
    }

    public String getAdditionalIcon() {
        return additionalIcon;
    }

    public void setAdditionalIcon(String additionalIcon) {
        this.additionalIcon = additionalIcon;
    }

    public String getLeadingElement() {
        return leadingElement;
    }

    public void setLeadingElement(String leadingElement) {
        this.leadingElement = leadingElement;
    }
}
