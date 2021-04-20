package com.example.todoapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.todoapp.models.DatabaseHelper;
import com.example.todoapp.R;
import com.example.todoapp.models.Task;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView taskList;
    Cursor taskCursor;
    SimpleCursorAdapter taskAdapter;
    ArrayList<Task> tasks= new ArrayList<>();
    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //taskList = (ListView) findViewById(R.id.listView);
        DatabaseHelper.databaseHelper = new DatabaseHelper(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        // открываем подключение
        DatabaseHelper.db = DatabaseHelper.databaseHelper.getReadableDatabase();
        //получаем данные из бд в виде курсора
        taskCursor = DatabaseHelper.db.rawQuery(" select * from " + DatabaseHelper.TABLE ,  data);
        if (taskCursor != null) {
            while (taskCursor.moveToNext()) {
                tasks.add(new Task(taskCursor.getString(taskCursor.getColumnIndex(DatabaseHelper.COLUMN_NAME)),
                        taskCursor.getString(taskCursor.getColumnIndex(DatabaseHelper.COLUMN_DISC)),
                        Uri.parse(taskCursor.getString(taskCursor.getColumnIndex(DatabaseHelper.COLUMN_PICS_PATH))),
                        taskCursor.getString(taskCursor.getColumnIndex(DatabaseHelper.COLUMN_PICS_NAME))));
                RecyclerView recyclerView = findViewById(R.id.list);
                TaskAdapter taskAdapter = new TaskAdapter(this, tasks);
                recyclerView.setAdapter(taskAdapter);
            }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DatabaseHelper.db.close();
    }
}