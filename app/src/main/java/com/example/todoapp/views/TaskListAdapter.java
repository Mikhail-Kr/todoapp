package com.example.todoapp.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.dbClasses.StepDbMethods;
import com.example.todoapp.dbClasses.TaskListDbMethods;
import com.example.todoapp.models.Task;
import com.example.todoapp.models.TaskList;

import java.util.List;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {
    private static List<Task> tasks;
    private static Context context1;
    private static Task task;

    public TaskListAdapter(Context context, List<Task> task) {
        this.tasks = task;
        context1 = context;
    }

    private static int getPos(String name) {
        int size = tasks.size();
        int pos = 0;
        for (int i = 0; i < size; i++) {
            if (tasks.get(i).toString().equals(name)) {
                pos = i;
            }
        }
        return pos;
    }

    @Override
    public TaskListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list_item, parent, false);
        TextView nameView = view.findViewById(R.id.list_name);
        if (StepDbMethods.checkFinished(nameView.getText().toString()) == true) {
            nameView.setPaintFlags(nameView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskListAdapter.ViewHolder holder, int position) {
        task = tasks.get(position);
        holder.picView.setImageURI(task.getPicPath());
        holder.nameView.setText(task.getName());

        if (StepDbMethods.checkFinished(holder.nameView.getText().toString()) == true) {
            holder.nameView.setPaintFlags(holder.nameView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        holder.discView.setText(task.getDisc());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView picView;
        final TextView nameView, discView, finishedView;

        ViewHolder(View view) {
            super(view);
            picView = view.findViewById(R.id.pic);
            nameView = view.findViewById(R.id.list_name);
            discView = view.findViewById(R.id.disc);
            finishedView = view.findViewById(R.id.finishedTask);
            discView.setOnClickListener(v -> {
                Intent intent = new Intent(context1, TaskActivity.class);
                intent.setAction(nameView.getText().toString());
                context1.startActivity(intent);
            });

            finishedView.setOnClickListener(v -> {
                TaskListDbMethods.setFinished(nameView.getText().toString());

                if (getLayoutPosition() == getPos(finishedView.getText().toString())) {
                    if (TaskListDbMethods.checkFinished(nameView.getText().toString()) == true) {
                        nameView.setPaintFlags(nameView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    }
                }
            });

        }
    }
}

