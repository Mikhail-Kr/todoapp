package com.example.todoapp.models;

import android.net.Uri;

import java.io.Serializable;


public class Task {
    private final String name;
    private final String disc;
    private final Uri picPath;
    private final int finished;
    private final int foreignKey;

    public Task(String name, String disc, Uri pic, int finished, int foreignKey) {
        if (name.length() >= 20) {
            throw new IllegalArgumentException();
        } else {
            this.name = name;
        }

        if (name.length() >= 20) {
            throw new IllegalArgumentException();
        } else {
            this.disc = disc;
        }
        this.picPath = pic;
        this.finished = finished;
        this.foreignKey = foreignKey;
    }

    public String getName() {
        return name;
    }

    public String getDisc() {
        return disc;
    }

    public Uri getPicPath() { return picPath; }

    public int getStatus() { return finished; }

    public int getForeignKey() {
        return foreignKey;
    }
}
