package com.example.glovobot.Model;

public class AddressField {
    public String type;
    public String label;
    public String value;
    public boolean important;

    public AddressField(String type, String label, String value, boolean important) {
        this.type = type;
        this.label = label;
        this.value = value;
        this.important = important;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }
}
