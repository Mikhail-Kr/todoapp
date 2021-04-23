package com.example.todoapp.models;

import java.io.Serializable;

public class Step implements Serializable {
    private final String step;

    public Step(String step) {
        if (step.length() >= 20) {
            throw new IllegalArgumentException();
        } else {
            this.step = step;
        }
    }

    public String getStep() {
        return step;
    }
}
