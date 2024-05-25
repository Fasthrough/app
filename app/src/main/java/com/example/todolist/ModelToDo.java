package com.example.todolist;

public class ModelToDo {

    private int id;  // New field for the ID
    private String task;
    private String date;

    // Constructor including ID (for retrieving from the database)
    public ModelToDo(int id, String task, String date) {
        this.id = id;
        this.task = task;
        this.date = date;
    }

    // Constructor without ID (for inserting new tasks, ID will be auto-generated)
    public ModelToDo(String task, String date) {
        this.task = task;
        this.date = date;
    }

    // Getter and Setter for ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
