package com.example.todoapp.views;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.todoapp.R;
import com.example.todoapp.dbClasses.StepDbMethods;
import com.example.todoapp.dbClasses.TaskListDbMethods;
import com.example.todoapp.dbClasses.TaskListsDbMethods;
import com.example.todoapp.models.Task;

import java.util.ArrayList;

public class TaskActivity extends AppCompatActivity {
    String result;
    ArrayList<Task> taskList;
    Task task;
    String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        taskList = TaskListDbMethods.select();
        result = getIntent().getAction();
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
        if (uri != null) {
            imageView.setImageURI(uri);
        }

        String[] copy = new String[] {"без списка"};
        String[] lists = TaskListsDbMethods.getNameFromPK(TaskListDbMethods.selectFK(result));
        if (lists == null) {
            lists = copy;
        }
        //отрисовка спинера с заданным именем
        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lists);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Получаем выбранный объект
                item = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);
        //конец отрисовки спинера
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        return true;
    }

    public void onClickEditTask(View view) {
        Intent intent = new Intent(this, EditTaskActivity.class);
        intent.setAction(result);
        startActivity(intent);
    }

    public void onClickDelTask(View view) {
        TextView tv = this.findViewById(R.id.task_name);
        TaskListDbMethods.delTask(tv.getText().toString());
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}