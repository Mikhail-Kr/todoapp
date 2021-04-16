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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
    }

    public void onClick(View view) {
        EditText content = findViewById(R.id.name);
        String name = content.getText().toString();
        EditText content2 = findViewById(R.id.message);
        String disc = content2.getText().toString();
        Task task = new Task(name, disc);

        MainActivity.db.execSQL("INSERT INTO tasks VALUES ('"+task.getName()+"', '"+task.getDisc()+"', null, null);");

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("name", task.getName());
        intent.putExtra("disc", task.getDisc());
        setResult(RESULT_OK, intent);
        finish();
    }
}