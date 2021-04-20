package com.example.todoapp.models;

import android.net.Uri;

import java.io.Serializable;


public class Task implements Serializable {
    private final String name;
    private final String disc;
    private final Uri picPath;
    private final String picName;

    public Task(String name, String disc, Uri pic, String picName) {
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
        this.picName = picName;
    }

    public String getName() {
        return name;
    }

    public String getDisc() {
        return disc;
    }

    public Uri getPicPath() { return picPath; }

    public String getPicName() { return picName; }
}
