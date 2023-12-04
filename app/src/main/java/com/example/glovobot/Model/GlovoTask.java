package com.example.glovobot.Model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Map;

public class GlovoTask {
    private Integer id;
    private TaskType type;
    private String params;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public JsonObject getParams() {
        JsonParser parser = new JsonParser();
        JsonObject json = (JsonObject) parser.parse(this.params);
        return json;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
