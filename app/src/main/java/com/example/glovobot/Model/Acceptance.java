package com.example.glovobot.Model;

public class Acceptance {
    private String status;
    private Object timer;

    public Acceptance(String status, Object timer) {
        this.status = status;
        this.timer = timer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getTimer() {
        return timer;
    }

    public void setTimer(Object timer) {
        this.timer = timer;
    }
}
