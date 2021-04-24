package com.example.todoapp.models;

import java.io.Serializable;

public class TaskList implements Serializable {
    private final String name;
    private int foreingKey;

    public TaskList(String name, int foreingKey) {
        if (name.length() >= 20) {
            throw new IllegalArgumentException();
        } else {
            this.name = name;
        }
        this.foreingKey = foreingKey;
    }

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

    public int getForeingKey() {
        return foreingKey;
    }

    @Override
    public String toString() {
        return name;
    }
}
