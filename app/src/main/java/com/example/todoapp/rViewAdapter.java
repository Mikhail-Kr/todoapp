package com.example.todoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class rViewAdapter extends RecyclerView.Adapter<rViewAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<Task> tasks;

    rViewAdapter(Context context, List<Task> task) {
        this.tasks = task;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public rViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(rViewAdapter.ViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.picView.setImageURI(task.getPicPath());
        holder.nameView.setText(task.getName());
        holder.discView.setText(task.getDisc());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView picView;
        final TextView nameView, discView;
        ViewHolder(View view){
            super(view);
            picView = view.findViewById(R.id.pic);
            nameView = view.findViewById(R.id.name);
            discView = view.findViewById(R.id.disc);
        }
    }
}
