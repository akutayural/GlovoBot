package com.example.glovobot.Model;

public class CurrentDaySummary {

    private Long id;
    private String earnings;


    public CurrentDaySummary(Long id, String earnings) {
        this.id = id;
        this.earnings = earnings;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEarnings(String earnings) {
        this.earnings = earnings;
    }

    public Long getId() {
        return id;
    }

    public String getEarnings() {
        return earnings;
    }
}
