package com.example.todoapp.dbClasses;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.models.DatabaseHelper;
import com.example.todoapp.models.Task;
import com.example.todoapp.models.TaskList;
import com.example.todoapp.views.TaskListAdapter;
import com.example.todoapp.views.TaskListsAdapter;

import java.util.ArrayList;

public class TaskListDbMethods {

    String result;

    public static ArrayList<Task> select() {
        Cursor taskCursor;
        ArrayList<Task> tasks = new ArrayList<>();
        taskCursor = DatabaseHelper.db.rawQuery(" select * from " + DatabaseHelper.TABLE, null);
        if (taskCursor != null) {
            while (taskCursor.moveToNext()) {
                tasks.add(addTask(taskCursor));
            }
        }
        return tasks;
    }

    public static Task addTask(Cursor taskCursor) {
        return new Task(
                taskCursor.getInt(taskCursor.getColumnIndex(DatabaseHelper.COLUMN_ID)),
                taskCursor.getString(taskCursor.getColumnIndex(DatabaseHelper.COLUMN_NAME)),
                taskCursor.getString(taskCursor.getColumnIndex(DatabaseHelper.COLUMN_DISC)),
                Uri.parse(taskCursor.getString(taskCursor.getColumnIndex(DatabaseHelper.COLUMN_PICS_PATH))),
                taskCursor.getInt(taskCursor.getColumnIndex(DatabaseHelper.COLUMN_STATUS)),
                taskCursor.getString(taskCursor.getColumnIndex(DatabaseHelper.COLUMN_DATE_ALARM)),
                taskCursor.getInt(taskCursor.getColumnIndex(DatabaseHelper.COLUMN_ID_GROUP)));
    }

    public static void ShowTaskList(Context context, Activity activity) {
        String result = activity.getIntent().getAction();
        Cursor taskCursor;
        // открываем подключение
        ArrayList<Task> tasks = new ArrayList<>();
        //получаем данные из бд в виде курсора
        taskCursor = DatabaseHelper.db.rawQuery(" select * from " + DatabaseHelper.TABLE, null);

        if (taskCursor != null) {
            result = activity.getIntent().getAction();
            int result1;
            if (result != null && result.equals("main")) {
                result1 = Integer.valueOf(result) + 1;
            } else {
                result1 = 0;
            }
            while (taskCursor.moveToNext()) {
                if (taskCursor.getInt(taskCursor.getColumnIndex(DatabaseHelper.COLUMN_ID_GROUP)) == result1) {
                    tasks.add(addTask(taskCursor));
                    RecyclerView recyclerView = activity.findViewById(R.id.list);
                    TaskListAdapter taskListAdapter = new TaskListAdapter(context, tasks);
                    recyclerView.setAdapter(taskListAdapter);
                }
            }
        }
    }
}
