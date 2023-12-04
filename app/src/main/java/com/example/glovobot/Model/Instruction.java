package com.example.glovobot.Model;

public class Instruction {
    private String type;
    private String label;

    public Instruction(String type, String label) {
        this.type = type;
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
