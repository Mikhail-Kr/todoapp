package com.example.todoapp.views;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.todoapp.R;
import com.example.todoapp.models.DatabaseHelper;
import com.example.todoapp.models.Task;

import java.util.ArrayList;

public class TaskListActivity extends AppCompatActivity {
    Cursor taskCursor;
    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // открываем подключение
        ArrayList<Task> tasks = new ArrayList<>();
        //DatabaseHelper.db = DatabaseHelper.databaseHelper.getReadableDatabase();
        //получаем данные из бд в виде курсора
        taskCursor = DatabaseHelper.db.rawQuery(" select * from " + DatabaseHelper.TABLE, data);
        if (taskCursor != null) {
            while (taskCursor.moveToNext()) {
                tasks.add(DatabaseHelper.addTask(taskCursor));
                RecyclerView recyclerView = findViewById(R.id.list);
                TaskAdapter taskAdapter = new TaskAdapter(this, tasks);
                recyclerView.setAdapter(taskAdapter);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, TaskActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
    }
}