package com.example.glovobot.Model;

public class NewMessageRequest {
    private String type;
    private NewMessageRequestParams params;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public NewMessageRequestParams getParams() {
        return params;
    }

    public void setParams(NewMessageRequestParams params) {
        this.params = params;
    }
}
