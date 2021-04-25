package com.example.todoapp.models;

import android.net.Uri;

import java.io.Serializable;


public class Task {
    private final String name;
    private final String disc;
    private final Uri picPath;
    private final int finished;
    private final int foreignKey;
    private int primaryKey;
    private String dateAlarm;

    public Task(String name, String disc, Uri pic, int finished, String dateAlarm, int foreignKey) {
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
        this.dateAlarm = dateAlarm;
    }

    public Task(int primaryKey, String name, String disc, Uri pic, int finished, String dateAlarm, int foreignKey) {
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
        this.primaryKey = primaryKey;
        this.dateAlarm = dateAlarm;
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getName() {
        return name;
    }

    public String getDisc() {
        return disc;
    }

    public Uri getPicPath() {
        return picPath;
    }

    public int getStatus() {
        return finished;
    }

    public int getForeignKey() {
        return foreignKey;
    }

    public int getPrimaryKey() {
        return primaryKey;
    }

    public String getDateAlarm() {
        return dateAlarm;
    }
}
