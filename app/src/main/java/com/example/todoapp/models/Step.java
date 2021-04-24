package com.example.todoapp.models;

public class Step {
    private final String title;

    public Step(String title) {
        if (title.length() >= 20) {
            throw new IllegalArgumentException();
        } else {
            this.title = title;
        }
    }

    public String getTitle() {
        return title;
    }
}
