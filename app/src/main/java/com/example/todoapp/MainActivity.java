package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    public static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS tasks (name TEXT, disc TEXT, alarm TEXT, pic BLOB)");
    }

    @Override
    protected void onResume() {
        super.onResume();
        // получаем элемент ListView
        ListView listView = findViewById(R.id.listView);

        Cursor query = db.rawQuery("SELECT * FROM tasks;", null);


        while(query.moveToNext()){
            String nameToPrint = query.getString(0);
            if (nameToPrint != null) {
                // создаем адаптер
                ArrayAdapter<String> adapter = new ArrayAdapter(this,
                        android.R.layout.simple_list_item_1, Collections.singletonList(nameToPrint));
                // устанавливаем для списка адаптер
                listView.setAdapter(adapter);
            }
        }
        query.close();
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
        db.close();
    }

}