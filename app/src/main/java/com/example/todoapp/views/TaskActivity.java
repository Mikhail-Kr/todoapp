package com.example.todoapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.todoapp.R;
import com.example.todoapp.dbClasses.StepDbMethods;
import com.example.todoapp.dbClasses.TaskListDbMethods;
import com.example.todoapp.models.Task;

import java.util.ArrayList;

public class TaskActivity extends AppCompatActivity {
    String result;
    ArrayList<Task> taskList = TaskListDbMethods.select();
    Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
    }

    @Override
    protected void onResume() {
        super.onResume();
            for (int i = 0; i < taskList.size(); i++) {
                if (taskList.get(i).getName().equals(result)) {
                    task = taskList.get(i);
                }
            }
            TextView content = findViewById(R.id.task_name);
            content.setText(task.getName());
            TextView content2 = findViewById(R.id.task_disc);
            content2.setText(task.getDisc());
            StepDbMethods.showStepLists(this, this, task.getPrimaryKey());
            Uri uri = task.getPicPath();
            ImageView imageView = findViewById(R.id.imageView);
            imageView.setImageURI(uri);
    }
}