package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class TaskActivity extends AppCompatActivity {

    Task task = new Task();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
    }

    public void onClick(View view) {
        EditText content = findViewById(R.id.name);
        String data = content.getText().toString();
        EditText content2 = findViewById(R.id.message);
        String disc = content2.getText().toString();
        task.put(data, disc);
        String mess = task.getTasks();

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Task.class.getSimpleName(), mess);
        startActivity(intent);
    }
}