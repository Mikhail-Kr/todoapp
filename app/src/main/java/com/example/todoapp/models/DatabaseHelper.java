package com.example.todoapp.models;

import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.net.Uri;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "taskApp.db"; // название бд
    private static final int SCHEMA = 1; // версия базы данных
    public static final String TABLE = "tasks"; // название таблицы в бд
    // названия столбцов
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DISC = "disc";
    public static final String COLUMN_PICS_PATH = "picsPath";
    public static final String COLUMN_PICS_NAME = "picsName";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tasks (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME
                + " TEXT," + COLUMN_DISC + " TEXT," + COLUMN_PICS_PATH
                + " TEXT," + COLUMN_PICS_NAME + " TEXT);");
        db.execSQL("CREATE TABLE taskList (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public static Task addTask (Cursor taskCursor) {
        return new Task(
                taskCursor.getString(taskCursor.getColumnIndex(DatabaseHelper.COLUMN_NAME)),
                taskCursor.getString(taskCursor.getColumnIndex(DatabaseHelper.COLUMN_DISC)),
                Uri.parse(taskCursor.getString(taskCursor.getColumnIndex(DatabaseHelper.COLUMN_PICS_PATH))),
                taskCursor.getString(taskCursor.getColumnIndex(DatabaseHelper.COLUMN_PICS_NAME)));
    }

    public static void addTaskList (String name) {

    }

    public static SQLiteDatabase db;
    public static DatabaseHelper databaseHelper;
}
