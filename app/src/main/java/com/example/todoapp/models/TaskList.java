package com.example.todoapp.models;

import java.io.Serializable;

public class TaskList implements Serializable {
    private final String name;

    public TaskList(String name) {
        if (name.length() >= 20) {
            throw new IllegalArgumentException();
        } else {
            this.name = name;
        }
    }

    public String getName() {
        return name;
    }
}
