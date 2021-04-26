package com.example.todoapp.dbClasses;

import android.content.ContentValues;
import android.net.Uri;

import com.example.todoapp.models.DatabaseHelper;
import com.example.todoapp.models.Step;
import com.example.todoapp.models.Task;

import java.util.ArrayList;

public class EditTaskDbMethods {

    public static void insertTask(Task task) {
        DatabaseHelper.db.execSQL("INSERT INTO tasks ("
                + DatabaseHelper.COLUMN_NAME + ", "
                + DatabaseHelper.COLUMN_DISC + " , "
                + DatabaseHelper.COLUMN_PICS_PATH + " , "
                + DatabaseHelper.COLUMN_STATUS + " , "
                + DatabaseHelper.COLUMN_DATE_ALARM + " , "
                + DatabaseHelper.COLUMN_ID_GROUP +
                ") VALUES ('"
                + task.getName() + "', '"
                + task.getDisc() + "', '"
                + task.getPicPath() + "', '"
                + task.getStatus() + "', '"
                + task.getDateAlarm() + "', '"
                + task.getForeignKey() +"' );");
    }

    public static void editTask(String name, String disc, Uri picsPath, String alardate) {
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("disc", disc);
        cv.put("picsPath", picsPath.toString());
        cv.put("alarmdate", alardate);
        ArrayList<Task> tasks = new ArrayList<>();
        String title;
        tasks = TaskListDbMethods.select();
        int updCount = DatabaseHelper.db.update(DatabaseHelper.TABLE, cv, "name = ?",
                new String[] {name});
    }
}
