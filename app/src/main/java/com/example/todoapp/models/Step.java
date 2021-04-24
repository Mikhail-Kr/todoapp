package com.example.todoapp.models;

public class Step {
    private final String title;
    private int foreignKey;

    public Step(String title) {
        if (title.length() >= 20) {
            throw new IllegalArgumentException();
        } else {
            this.title = title;
        }
    }

    public Step(String title, int foreignKey) {
        if (title.length() >= 20) {
            throw new IllegalArgumentException();
        } else {
            this.title = title;
        }
        this.foreignKey = foreignKey;
    }

    public String getTitle() {
        return title;
    }

    public int getForeignKey() {
        return foreignKey;
    }
}
