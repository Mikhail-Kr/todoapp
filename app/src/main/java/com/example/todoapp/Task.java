package com.example.todoapp;

import android.widget.EditText;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Task extends HashMap implements Serializable {
    Map<String, String> task = new HashMap<>();

    // Конструктор с параметрами
    public Task(String name, String disc) {
        task.put(name, disc);
    }

    public Task() {
    }

    public void putTask(String name, String disc) {
        task.put(name, disc);
    }

    public String getTasks () {
        String temp = Collections.singletonList(task.keySet().toString()).toString();
/*        for (int i = 0; i < task.size(); i++) {
            tasks[i] = task.keySet();
        }*/
        return temp;
    }
}
