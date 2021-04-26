package com.example.todoapp.views;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.dbClasses.StepDbMethods;
import com.example.todoapp.models.Step;

import org.w3c.dom.Text;

import java.util.List;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.ViewHolder> {
    private static List<Step> steps;
    Context context1;

    private static int getPos(String name) {
        int size = steps.size();
        int pos = 0;
        for (int i = 0; i < size; i++) {
            if (steps.get(i).toString().equals(name)) {
                pos = i;
            }
        }
        return pos;
    }

    public StepAdapter(Context context, List<Step> steps) {
        Context context1 = context;
        this.steps = steps;
    }

    @Override
    public StepAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.step_list_item, parent, false);
        TextView stepView = view.findViewById(R.id.title_step);
        if (StepDbMethods.checkFinished(stepView.getText().toString()) == true) {
            stepView.setPaintFlags(stepView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepAdapter.ViewHolder holder, int position) {
        Step step = steps.get(position);
        holder.stepView.setText(step.getTitle());
        if (StepDbMethods.checkFinished(holder.stepView.getText().toString()) == true) {
            holder.stepView.setPaintFlags(holder.stepView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        };
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView stepView;
        final TextView delText;
        final TextView finText;

        ViewHolder(View view) {
            super(view);
            stepView = view.findViewById(R.id.title_step);
            delText = view.findViewById(R.id.delText);
            finText = view.findViewById(R.id.finished);
            delText.setOnClickListener(v -> {
                Toast.makeText(view.getContext(), stepView.getText().toString(), Toast.LENGTH_SHORT).show();
            });
            finText.setOnClickListener(v -> {
                StepDbMethods.setFinished(stepView.getText().toString());

                if (getLayoutPosition() == getPos(stepView.getText().toString())) {
                    if (StepDbMethods.checkFinished(stepView.getText().toString()) == true) {
                        stepView.setPaintFlags(stepView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    }
                }
            });
        }
    }
}
