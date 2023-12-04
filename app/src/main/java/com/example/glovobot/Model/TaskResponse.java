package com.example.glovobot.Model;

import java.util.List;

public class TaskResponse {
    private List<GlovoTask> task_response;

    public TaskResponse(List <GlovoTask> task_response) {
        this.task_response = task_response;
    }

    public List<GlovoTask> getTask_response() {
        return task_response;
    }

    public void setTask_response(List<GlovoTask> task_response) {
        this.task_response = task_response;
    }
}
