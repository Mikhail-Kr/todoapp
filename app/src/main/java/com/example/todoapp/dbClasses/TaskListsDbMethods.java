package com.example.todoapp.dbClasses;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;

import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.models.DatabaseHelper;
import com.example.todoapp.models.TaskList;
import com.example.todoapp.views.TaskListsAdapter;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskListsDbMethods {

    public static ArrayList<TaskList> select() {
        Cursor taskCursor;
        ArrayList<TaskList> tasks = new ArrayList<>();
        taskCursor = DatabaseHelper.db.rawQuery(" select * from " + DatabaseHelper.TABLE_LIST, null);
        if (taskCursor != null) {
            while (taskCursor.moveToNext()) {
                tasks.add(addTaskList(taskCursor));
            }
        }
        return tasks;
    }

    public static String[] selectTaskListsName() {
        ArrayList<TaskList> tasks = select();
        String[] tasksName = new String[tasks.size()+1];

        tasksName[0] = "без списка";
        for (int i = 1; i <= tasks.size(); i++) {
            tasksName[i] = tasks.get(i-1).toString();
        }
        return tasksName;
    }


    public static TaskList addTaskList(Cursor taskCursor) {
        return new TaskList(taskCursor.getString(taskCursor.getColumnIndex(DatabaseHelper.COLUMN_NAME)),
                taskCursor.getInt(taskCursor.getColumnIndex(DatabaseHelper.COLUMN_ID)));
    }

    //отображение TaskLists из БД
    public static void showTaskLists(Context context, Activity activity) {
        ArrayList<TaskList> tasks = select();
        RecyclerView recyclerView = activity.findViewById(R.id.tasks_list);
        TaskListsAdapter taskListsAdapter = new TaskListsAdapter(context, tasks);
        recyclerView.setAdapter(taskListsAdapter);
        taskListsAdapter.notifyDataSetChanged();
    }
}