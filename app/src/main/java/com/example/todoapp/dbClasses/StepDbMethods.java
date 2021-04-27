package com.example.todoapp.dbClasses;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.models.DatabaseHelper;
import com.example.todoapp.models.Step;
import com.example.todoapp.views.StepAdapter;

import java.util.ArrayList;

public class StepDbMethods {

    public static ArrayList<Step> select() {
        Cursor taskCursor;
        ArrayList<Step> steps = new ArrayList<>();
        taskCursor = DatabaseHelper.db.rawQuery(" select * from " + DatabaseHelper.TABLE_STEP_LIST, null);
        if (taskCursor != null) {
            while (taskCursor.moveToNext()) {
                steps.add(addStepList(taskCursor));
            }
        }
        return steps;
    }

    public static Step addStepList(Cursor taskCursor) {
        return new Step(taskCursor.getString(taskCursor.getColumnIndex(DatabaseHelper.COLUMN_NAME)),
                taskCursor.getInt(taskCursor.getColumnIndex(DatabaseHelper.COLUMN_STATUS)),
                taskCursor.getInt(taskCursor.getColumnIndex(DatabaseHelper.COLUMN_ID_GROUP)));
    }

    public static ArrayList<Step> selectFromDbForPk(int pK) {
        ArrayList<Step> step = select();
        ArrayList<Step> stepsForPr = new ArrayList<>();
        for (int i = 0; i < step.size(); i++) {
            if (step != null && (step.get(i).getForeignKey() == pK)) {
                stepsForPr.add(step.get(i));
            }
        }
        return stepsForPr;
    }

    //отображение Step из БД
    public static void showStepLists(Context context, Activity activity, int pK) {
        ArrayList<Step> stepsForPr = selectFromDbForPk(pK);
        RecyclerView recyclerView;
        if (activity.getClass().getSimpleName().equals("EditTaskActivity")) {
            recyclerView = activity.findViewById(R.id.stepslist);
        } else {
            recyclerView = activity.findViewById(R.id.step_list1);
        }
        StepAdapter stepAdapter = new StepAdapter(context, stepsForPr);
        if (stepsForPr != null) {
            recyclerView.setAdapter(stepAdapter);
            stepAdapter.notifyDataSetChanged();
        }
    }

    //отображение Step из ArrayList
    public static void showStepListsArray(ArrayList<Step> step, Context context, Activity activity) {
        RecyclerView recyclerView = activity.findViewById(R.id.stepslist);
        StepAdapter stepAdapter = new StepAdapter(context, step);
        recyclerView.setAdapter(stepAdapter);
        stepAdapter.notifyDataSetChanged();
    }

    public static void insertSteps(ArrayList<Step> steps) {
        for (int i = 0; i < steps.size(); i++) {
            Step step = steps.get(i);
            DatabaseHelper.db.execSQL("INSERT INTO stepList ("
                    + DatabaseHelper.COLUMN_NAME + ", "
                    + DatabaseHelper.COLUMN_STATUS + ", "
                    + DatabaseHelper.COLUMN_ID_GROUP + ") VALUES ('"
                    + step.getTitle() + "', '"
                    + step.getFinished() + "', '"
                    + step.getForeignKey() + "');");
        }
    }

    public static void setFinished(String name) {
        ContentValues cv = new ContentValues();
        cv.put("finished", 1);
        ArrayList<Step> steps = new ArrayList<>();
        String title;
        steps = select();
        for (int i = 0; i < steps.size(); i++) {
            if (steps.get(i).equals(name)) {
                title = steps.get(i).getTitle();
            }
        }
        int updCount = DatabaseHelper.db.update(DatabaseHelper.TABLE_STEP_LIST, cv, "name = ?",
        new String[] {name});
    }

    public static boolean checkFinished(String name) {
        ArrayList<Step> steps;
        steps = select();
        boolean check = false;
        for (int i = 0; i < steps.size(); i++) {
            if (steps.get(i).getTitle().equals(name)) {
                if (steps.get(i).getFinished() == 1) {
                    check = true;
                } else {
                    check = false;
                }
            }
        }
        return check;
    }

    public static void delStep(String name) {
        int delId = 1;
        Cursor taskCursor;
        taskCursor = DatabaseHelper.db.rawQuery(" select * from " + DatabaseHelper.TABLE_STEP_LIST, null);
        if (taskCursor != null) {
            while (taskCursor.moveToNext()) {
                if(taskCursor.getString(taskCursor.getColumnIndex(DatabaseHelper.COLUMN_NAME)) == name) {
                    delId = taskCursor.getInt(taskCursor.getColumnIndex(DatabaseHelper.COLUMN_ID)) ;
                }
            }
        }
        int delCount = DatabaseHelper.db.delete(DatabaseHelper.TABLE_STEP_LIST, DatabaseHelper.COLUMN_ID + "=" + delId, null);
    }
}
