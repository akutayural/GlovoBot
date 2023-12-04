package com.example.glovobot.Model;

public class AcceptanceTimer {
    private int totalDurationSeconds;
    private int remainingDurationSeconds;

    public AcceptanceTimer(int totalDurationSeconds, int remainingDurationSeconds) {
        this.totalDurationSeconds = totalDurationSeconds;
        this.remainingDurationSeconds = remainingDurationSeconds;
    }

    public int getTotalDurationSeconds() {
        return totalDurationSeconds;
    }

    public void setTotalDurationSeconds(int totalDurationSeconds) {
        this.totalDurationSeconds = totalDurationSeconds;
    }

    public int getRemainingDurationSeconds() {
        return remainingDurationSeconds;
    }

    public void setRemainingDurationSeconds(int remainingDurationSeconds) {
        this.remainingDurationSeconds = remainingDurationSeconds;
    }
}
