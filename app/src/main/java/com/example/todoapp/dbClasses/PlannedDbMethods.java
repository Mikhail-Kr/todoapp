package com.example.todoapp.dbClasses;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;

import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.models.DatabaseHelper;
import com.example.todoapp.models.Task;
import com.example.todoapp.views.TaskListAdapter;

import java.util.ArrayList;

public class PlannedDbMethods {

    public static void ShowTaskList(Context context, Activity activity) {
        String result = activity.getIntent().getAction();
        Cursor taskCursor;
        // открываем подключение
        ArrayList<Task> tasks = new ArrayList<>();
        //получаем данные из бд в виде курсора
        taskCursor = DatabaseHelper.db.rawQuery(" select * from " + DatabaseHelper.TABLE, null);

        if (taskCursor != null) {
            while (taskCursor.moveToNext()) {
                if (taskCursor.getString(taskCursor.getColumnIndex(DatabaseHelper.COLUMN_DATE_ALARM)) != null) {
                    tasks.add(TaskListDbMethods.addTask(taskCursor));
                    RecyclerView recyclerView = activity.findViewById(R.id.listPlan);
                    TaskListAdapter taskListAdapter = new TaskListAdapter(context, tasks);
                    recyclerView.setAdapter(taskListAdapter);
                }
            }
        }
    }
}
