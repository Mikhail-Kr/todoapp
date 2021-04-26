package com.example.todoapp.dbClasses;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.renderscript.Sampler;

import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.models.DatabaseHelper;
import com.example.todoapp.models.Task;
import com.example.todoapp.models.TaskList;
import com.example.todoapp.views.TaskListsAdapter;

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
        String[] tasksName = new String[tasks.size() + 1];

        tasksName[0] = "без списка";
        for (int i = 1; i <= tasks.size(); i++) {
            tasksName[i] = tasks.get(i - 1).toString();
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

    public static String[] getNameFromPK(int pK) {

        Cursor taskCursor;
        ArrayList<TaskList> tasks = new ArrayList<>();
        taskCursor = DatabaseHelper.db.rawQuery(" select * from " + DatabaseHelper.TABLE_LIST, null);
        if (taskCursor != null) {
            while (taskCursor.moveToNext()) {
                if (taskCursor.getInt(taskCursor.getColumnIndex(DatabaseHelper.COLUMN_ID)) == pK) {
                    tasks.add(addTaskList(taskCursor));
                }
            }
        }
        if (pK != 1034 && pK != 0) {
            return new String[] {tasks.get(0).getName()};
        } else {
            return null;
        }
    }

    public static void editTaskListName(String newName, int newNameId) {
        Cursor taskCursor;
        String name = null;
        taskCursor = DatabaseHelper.db.rawQuery(" select * from " + DatabaseHelper.TABLE_LIST, null);
        if (taskCursor != null) {
            while (taskCursor.moveToNext()) {
                if (taskCursor.getInt(taskCursor.getColumnIndex(DatabaseHelper.COLUMN_ID)) == (newNameId+1)) {
                    name = (taskCursor.getString(taskCursor.getColumnIndex(DatabaseHelper.COLUMN_NAME)));
                }
            }
        }
        ContentValues cv = new ContentValues();
        cv.put("name", newName);
        int updCount = DatabaseHelper.db.update(DatabaseHelper.TABLE_LIST, cv, "name = ?",
                new String[] {name});
    }

    public static void delTaskListName(int id) {
        int delId = id + 1;
        int delCount = DatabaseHelper.db.delete(DatabaseHelper.TABLE_LIST, DatabaseHelper.COLUMN_ID + "=" + delId, null);
    }
}