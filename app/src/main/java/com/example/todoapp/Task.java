package com.example.todoapp;

import java.io.Serializable;


public class Task implements Serializable {
    private final String name;
    private final String disc;

    public Task(String name, String disc) {
        this.name = name;
        this.disc = disc;
    }

    public String getName() {
        return name;
    }

    public String getDisc() {
        return disc;
    }
}
