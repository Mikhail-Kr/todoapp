package com.example.todoapp.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.models.Step;

import java.util.List;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.ViewHolder> {
    private static List<Step> steps;

    StepAdapter(Context context, List<Step> step) {
        this.steps = step;
    }

    @Override
    public StepAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.step_list_item, parent, false);
        return new StepAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepAdapter.ViewHolder holder, int position) {
        Step step = steps.get(position);
        holder.stepView.setText(step.getStep());
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView stepView;

        ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            stepView = view.findViewById(R.id.step_list);
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
            for (int i = 0; i < steps.size(); i++) {
            }
        }
    }
}
