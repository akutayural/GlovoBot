package com.example.glovobot.Model;

public class PythonAuthResponse {
    private PythonAuthResponseData data;
    private String status;

    public PythonAuthResponseData getData() {
        return data;
    }

    public void setData(PythonAuthResponseData data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

