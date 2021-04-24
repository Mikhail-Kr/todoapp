package com.example.todoapp.dbClasses;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;

import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.models.DatabaseHelper;
import com.example.todoapp.models.TaskList;
import com.example.todoapp.views.TaskListsAdapter;

import java.util.ArrayList;

public class TaskListsDbMethods {


    public static TaskList addTaskList (Cursor taskCursor) {
        return new TaskList (taskCursor.getString(taskCursor.getColumnIndex(DatabaseHelper.COLUMN_NAME)));
    }

    //отображение TaskLists из БД
    public static void showTaskLists(Context context, Activity activity) {
        Cursor taskCursor;
        ArrayList<TaskList> tasks = new ArrayList<>();
        //получаем данные из бд в виде курсора
        taskCursor = DatabaseHelper.db.rawQuery(" select * from " + DatabaseHelper.TABLE_LIST, null);
        if (taskCursor != null) {
            while (taskCursor.moveToNext()) {
                tasks.add(addTaskList(taskCursor));
                RecyclerView recyclerView = activity.findViewById(R.id.tasks_list);
                TaskListsAdapter taskListsAdapter = new TaskListsAdapter(context, tasks);
                recyclerView.setAdapter(taskListsAdapter);
                taskListsAdapter.notifyDataSetChanged();
            }
        }
    }
}