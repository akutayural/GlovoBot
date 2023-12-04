package com.example.glovobot.Model;

public class Header {
    public String name;
    public String icon;
    public String action;

    public Header(String name, String icon, String action) {
        this.name = name;
        this.icon = icon;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
