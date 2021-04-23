package com.example.todoapp.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.models.Task;

import java.util.List;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {
    private static List<Task> tasks;

    TaskListAdapter(Context context, List<Task> task) {
        this.tasks = task;
    }

    @Override
    public TaskListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskListAdapter.ViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.picView.setImageURI(task.getPicPath());
        holder.nameView.setText(task.getName());
        holder.discView.setText(task.getDisc());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final ImageView picView;
        final TextView nameView, discView;

        ViewHolder(View view) {
            super(view);
            CheckedTextView ct = itemView.findViewById(R.id.list_name);
            ct.setOnClickListener(this);
            picView = view.findViewById(R.id.pic);
            nameView = view.findViewById(R.id.list_name);
            discView = view.findViewById(R.id.disc);
        }

        @Override
        public void onClick(View view) {
/*            Toast.makeText(view.getContext(), "position = " + getLayoutPosition(), Toast.LENGTH_SHORT).show();
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
            for (int i = 0; i < tasks.size(); i++) {
            }*/
            ((CheckedTextView) view).toggle();
        }
    }
}

