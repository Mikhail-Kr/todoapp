package com.example.todoapp.dbClasses;

import com.example.todoapp.models.DatabaseHelper;
import com.example.todoapp.models.Task;

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
}
