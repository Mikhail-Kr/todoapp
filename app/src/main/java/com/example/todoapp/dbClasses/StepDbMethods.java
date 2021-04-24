package com.example.todoapp.dbClasses;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;

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
                taskCursor.getInt(taskCursor.getColumnIndex(DatabaseHelper.COLUMN_ID)));
    }

    //отображение Step из БД
    public static void showStepLists(Context context, Activity activity) {
        ArrayList<Step> step = select();
        RecyclerView recyclerView = activity.findViewById(R.id.stepslist);
        StepAdapter stepAdapter = new StepAdapter(context, step);
        recyclerView.setAdapter(stepAdapter);
        stepAdapter.notifyDataSetChanged();
    }
}
