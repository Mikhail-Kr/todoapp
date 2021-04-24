package com.example.todoapp.models;

import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.net.Uri;

import com.example.todoapp.dbClasses.TaskListsDbMethods;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "taskApp.db"; // название бд
    private static final int SCHEMA = 1; // версия базы данных
    public static final String TABLE = "tasks";
    public static final String TABLE_LIST = "taskLists";
    public static final String TABLE_STEP_LIST = "stepList";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DISC = "disc";
    public static final String COLUMN_PICS_PATH = "picsPath";
    public static final String COLUMN_PICS_NAME = "picsName";
    public static final String COLUMN_STATUS = "finished";
    public static final String COLUMN_ID_GROUP = "id_group";
    public static SQLiteDatabase db;
    public static DatabaseHelper databaseHelper;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE +" ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DISC + " TEXT,"
                + COLUMN_PICS_PATH + " TEXT,"
                + COLUMN_PICS_NAME + " TEXT);");

        db.execSQL("CREATE TABLE taskLists ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT);");

        db.execSQL("CREATE TABLE stepList ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_STATUS + " INTEGER,"
                + COLUMN_ID_GROUP + " INTEGER,"
                + " FOREIGN KEY ("+COLUMN_ID_GROUP+") REFERENCES "+TABLE+"("+COLUMN_ID_GROUP+"))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
