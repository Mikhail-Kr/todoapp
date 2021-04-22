package com.example.todoapp.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.models.TaskList;

import java.util.List;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {
    private static List<TaskList> taskList;

    TaskListAdapter(Context context, List<TaskList> taskList) {
        this.taskList = taskList;
    }

    @Override
    public TaskListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tasks_list_item, parent, false);
        return new TaskListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TaskList taskList = TaskListAdapter.taskList.get(position);
        holder.nameView.setText(taskList.getName());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView nameView;

        ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            nameView = view.findViewById(R.id.title);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "position = " + getLayoutPosition(), Toast.LENGTH_SHORT).show();
            //go through each item if you have few items within recycler view
            if (getLayoutPosition() == 0) {
                //Do whatever you want here
            } else if (getLayoutPosition() == 1) {
                //Do whatever you want here
            } else if (getLayoutPosition() == 2) {
            } else if (getLayoutPosition() == 3) {
            } else if (getLayoutPosition() == 4) {
            } else if (getLayoutPosition() == 5) {
            }
            //or you can use For loop if you have long list of items. Use its length or size of the list as
            for (int i = 0; i < taskList.size(); i++) {
            }
        }
    }
}
