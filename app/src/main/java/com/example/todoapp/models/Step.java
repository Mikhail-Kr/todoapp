package com.example.todoapp.models;

public class Step {
    private final String title;
    private int foreignKey;
    private int finished;

    public Step(String title,  int finished, int foreignKey) {
        if (title.length() >= 20) {
            throw new IllegalArgumentException();
        } else {
            this.title = title;
        }
        this.foreignKey = foreignKey;

        this.finished = finished;
    }

    public String getTitle() {
        return title;
    }

    public int getForeignKey() {
        return foreignKey;
    }

    public int getFinished() {
        return finished;
    }
}
