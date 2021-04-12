package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {


        // получаем элемент ListView
        super.onResume();
        ListView listView = (ListView) findViewById(R.id.listView);

        Bundle arguments = getIntent().getExtras();

        Task task = new Task();
        if (arguments != null) {
            task = (Task) arguments.getSerializable(Task.class.getSimpleName());
        }


        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, Collections.singletonList(task.keySet().toString()));

        // устанавливаем для списка адаптер
        listView.setAdapter(adapter);
    }

    //ListView

    public void onClick(View view) {
        Intent intent = new Intent(this, TaskActivity.class);
        startActivity(intent);
    }

}