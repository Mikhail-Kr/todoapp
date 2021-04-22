package com.example.todoapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.example.todoapp.models.DatabaseHelper;
import com.example.todoapp.R;
import com.example.todoapp.models.TaskList;

public class MainActivity extends AppCompatActivity {
    TaskList tasklist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper.databaseHelper = new DatabaseHelper(getApplicationContext());
        DatabaseHelper.db = DatabaseHelper.databaseHelper.getReadableDatabase();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //DatabaseHelper.db.close();
    }

    public void onClickTaskList(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Имя списка задач");

// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tasklist = new TaskList(input.getText().toString());
                DatabaseHelper.db.execSQL("INSERT INTO taskList (" + DatabaseHelper.COLUMN_NAME + ") VALUES ('" + tasklist.getName() + "');");
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    public void onClickToTaskList(View view) {
        Intent intent = new Intent(this, TaskListActivity.class);
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