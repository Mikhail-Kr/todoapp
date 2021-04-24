package com.example.todoapp.dbClasses;

import com.example.todoapp.models.DatabaseHelper;
import com.example.todoapp.models.Task;

public class EditTaskDbMethods {

    public static void insert (Task task) {
        DatabaseHelper.db.execSQL("INSERT INTO tasks (" + DatabaseHelper.COLUMN_NAME + ", "
                + DatabaseHelper.COLUMN_DISC + " , " + DatabaseHelper.COLUMN_PICS_PATH + " , "
                + DatabaseHelper.COLUMN_PICS_NAME + ") VALUES ('" + task.getName() + "', '"
                + task.getDisc() + "', '" + task.getPicPath() + "', '"+ task.getPicName() +"' );");
    }
}
