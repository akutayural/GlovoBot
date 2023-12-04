package com.example.glovobot.Model;

import java.util.List;

public class StepResponse {
    private final long version;
    private int currentStep;
    private long id;
    private List<Steps> steps;

    public StepResponse(long version, int currentStep, long id, List<Steps> steps) {
        this.version = version;
        this.currentStep = currentStep;
        this.id = id;
        this.steps = steps;
    }

    public long getVersion() {
        return version;
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(int currentStep) {
        this.currentStep = currentStep;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Steps> getSteps() {
        return steps;
    }

    public void setSteps(List<Steps> steps) {
        this.steps = steps;
    }
}
