package com.example.glovobot.Model;

public class OrderReadyInfo {
    public String status;
    public int minEtpSeconds;
    public int maxEtpSeconds;
    public int remainingEtpSeconds;
    public String message;
    public String countDownTimerMessage;

    public OrderReadyInfo(String status, int minEtpSeconds, int maxEtpSeconds, int remainingEtpSeconds, String message, String countDownTimerMessage) {
        this.status = status;
        this.minEtpSeconds = minEtpSeconds;
        this.maxEtpSeconds = maxEtpSeconds;
        this.remainingEtpSeconds = remainingEtpSeconds;
        this.message = message;
        this.countDownTimerMessage = countDownTimerMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMinEtpSeconds() {
        return minEtpSeconds;
    }

    public void setMinEtpSeconds(int minEtpSeconds) {
        this.minEtpSeconds = minEtpSeconds;
    }

    public int getMaxEtpSeconds() {
        return maxEtpSeconds;
    }

    public void setMaxEtpSeconds(int maxEtpSeconds) {
        this.maxEtpSeconds = maxEtpSeconds;
    }

    public int getRemainingEtpSeconds() {
        return remainingEtpSeconds;
    }

    public void setRemainingEtpSeconds(int remainingEtpSeconds) {
        this.remainingEtpSeconds = remainingEtpSeconds;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCountDownTimerMessage() {
        return countDownTimerMessage;
    }

    public void setCountDownTimerMessage(String countDownTimerMessage) {
        this.countDownTimerMessage = countDownTimerMessage;
    }
}
