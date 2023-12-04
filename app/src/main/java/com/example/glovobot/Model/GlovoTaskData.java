package com.example.glovobot.Model;

public class GlovoTaskData {
    private TaskResponse data;
    private String status;

    public TaskResponse getData() {
        return data;
    }

    public void setData(TaskResponse data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
