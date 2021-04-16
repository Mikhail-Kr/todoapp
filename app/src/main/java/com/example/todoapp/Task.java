package com.example.todoapp;

import java.io.Serializable;


public class Task implements Serializable {
    private String name;
    private String disc;

    public Task(String name, String disc) {
        this.name = name;
        this.disc = disc;
    }

    public Task() {}

    public String getName() {
        return name;
    }

    public String getDisc() {
        return disc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  void setDisc(String disc) {
        this.disc = disc;
    }
}
