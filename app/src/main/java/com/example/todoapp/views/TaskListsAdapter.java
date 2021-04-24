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

public class TaskListsAdapter extends RecyclerView.Adapter<TaskListsAdapter.ViewHolder> {
    private static List<TaskList> taskList;

    public TaskListsAdapter(Context context, List<TaskList> taskList) {
        this.taskList = taskList;
    }

    @Override
    public TaskListsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tasks_list_item, parent, false);
        return new TaskListsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TaskList taskList = TaskListsAdapter.taskList.get(position);
        holder.nameView.setText(taskList.getName());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView;

        ViewHolder(View view) {
            super(view);
            nameView = view.findViewById(R.id.title);
            nameView.setOnClickListener(v -> {
                Toast.makeText(view.getContext(), nameView.getText().toString(), Toast.LENGTH_LONG).show();
            });
        }
    }
}
