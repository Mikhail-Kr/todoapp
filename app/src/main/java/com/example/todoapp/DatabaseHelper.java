package com.example.todoapp;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "taskApp.db"; // название бд
    private static final int SCHEMA = 1; // версия базы данных
    static final String TABLE = "tasks"; // название таблицы в бд
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
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
    }
}
