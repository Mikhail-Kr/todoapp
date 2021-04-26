/**
 * Info about this package doing something for package-info.java file.
 */
package com.example.todoapp.models;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class DatabaseHelper extends SQLiteOpenHelper {

    /** Имя базы данных {@value #DATABASE_NAME}.*/
    private static final String DATABASE_NAME = "taskApp.db"; // название бд

    /** Версия базы данных {@value #SCHEMA}.*/
    private static final int SCHEMA = 1;

    /** Имя таблицы БД, для хранения задач {@value #TABLE}.*/
    public static final String TABLE = "tasks";

    /** Имя таблицы БД, для хранения списков задач {@value #TABLE_LIST}.*/
    public static final String TABLE_LIST = "taskLists";

    /** Имя таблицы БД, для хранения списков задач {@value #TABLE_STEP_LIST}.*/
    public static final String TABLE_STEP_LIST = "stepList";

    /** Имя столбца таблицы БД, обозначение id {@value #COLUMN_ID}.*/
    public static final String COLUMN_ID = "_id";

    /** Имя столбца таблицы БД, обозначение имени {@value #COLUMN_NAME}.*/
    public static final String COLUMN_NAME = "name";

    /** Имя столбца таблицы БД, обозначение описания {@value #COLUMN_DISC}.*/
    public static final String COLUMN_DISC = "disc";

    /** Имя столбца таблицы БД, обозначение Uri хранения изображения
     *  {@value #COLUMN_PICS_PATH}.*/
    public static final String COLUMN_PICS_PATH = "picsPath";

    /** Имя столбца таблицы БД, обозначение статуса завершения
     *  {@value #COLUMN_STATUS}.*/
    public static final String COLUMN_STATUS = "finished";

    /** Имя столбца таблицы БД, обозначение ссылки на другую таблицу
     *  {@value #COLUMN_ID_GROUP}.*/
    public static final String COLUMN_ID_GROUP = "idgroup";

    /** Имя столбца таблицы БД, обозначение даты оповещения
     *  {@value #COLUMN_DATE_ALARM}.*/
    public static final String COLUMN_DATE_ALARM = "alarmdate";

    /** Имя БД {@link #db}.*/
    public static SQLiteDatabase db;

    /** Метод получения экземпляра БД {@link #db}.
     * @return SQLiteDatabase.*/
    public static SQLiteDatabase getDb() {
        return db;
    }

    /** Экземпляр класса DatabaseHelper.*/
    public static DatabaseHelper databaseHelper;

    /** Метод получения экземпляра БД {@link #databaseHelper}.
     * @return DatabaseHelper.*/
    public static DatabaseHelper getDatabaseHelper() {
        return databaseHelper;
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_LIST +" ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT);");

        db.execSQL("CREATE TABLE "+ TABLE +" ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DISC + " TEXT,"
                + COLUMN_PICS_PATH + " TEXT,"
                + COLUMN_DATE_ALARM + " TEXT,"
                + COLUMN_STATUS + " INTEGER,"
                + COLUMN_ID_GROUP + " INTEGER,"
                + " FOREIGN KEY (" + COLUMN_ID_GROUP + ") REFERENCES "+ TABLE_LIST +"(" + COLUMN_ID + ") ON DELETE SET NULL )");

        db.execSQL("CREATE TABLE "+ TABLE_STEP_LIST +" ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_STATUS + " INTEGER,"
                + COLUMN_ID_GROUP + " INTEGER,"
                + " FOREIGN KEY (" + COLUMN_ID + ") REFERENCES "+ TABLE +"(" + COLUMN_ID + "))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
