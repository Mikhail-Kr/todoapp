package com.example.todoapp.dbClasses;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.models.DatabaseHelper;
import com.example.todoapp.models.Task;
import com.example.todoapp.views.TaskListAdapter;

import java.util.ArrayList;

public class TaskListDbMethods {

    public static Task addTask (Cursor taskCursor) {
        return new Task(
                taskCursor.getString(taskCursor.getColumnIndex(DatabaseHelper.COLUMN_NAME)),
                taskCursor.getString(taskCursor.getColumnIndex(DatabaseHelper.COLUMN_DISC)),
                Uri.parse(taskCursor.getString(taskCursor.getColumnIndex(DatabaseHelper.COLUMN_PICS_PATH))),
                taskCursor.getString(taskCursor.getColumnIndex(DatabaseHelper.COLUMN_PICS_NAME)));
    }

    public static void ShowTaskList (Context context, Activity activity) {
        Cursor taskCursor;
        // открываем подключение
        ArrayList<Task> tasks = new ArrayList<>();
        //DatabaseHelper.db = DatabaseHelper.databaseHelper.getReadableDatabase();
        //получаем данные из бд в виде курсора
        taskCursor = DatabaseHelper.db.rawQuery(" select * from " + DatabaseHelper.TABLE, null);
        if (taskCursor != null) {
            while (taskCursor.moveToNext()) {
                tasks.add(addTask(taskCursor));
                RecyclerView recyclerView = activity.findViewById(R.id.list);
                TaskListAdapter taskListAdapter = new TaskListAdapter(context, tasks);
                recyclerView.setAdapter(taskListAdapter);
            }
        }
    }
}
