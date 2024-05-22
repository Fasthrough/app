package com.example.todolist;

public class ModelToDo {

    private String task, date;

    public String getTask() {
        return task;
    }

    public String getDate() {
        return date;
    }

    public ModelToDo(String task, String date) {
        this.task = task;
        this.date = date;
    }
}
