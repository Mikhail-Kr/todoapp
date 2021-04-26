package com.example.todoapp.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.models.Step;

import org.w3c.dom.Text;

import java.util.List;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.ViewHolder> {
    private static List<Step> steps;
    Context context1;

    public StepAdapter(Context context, List<Step> steps) {
        Context context1 = context;
        this.steps = steps;
    }

    @Override
    public StepAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.step_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepAdapter.ViewHolder holder, int position) {
        Step step = steps.get(position);
        holder.stepView.setText(step.getTitle());
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final CheckedTextView stepView;
        final TextView delText;

        ViewHolder(View view) {
            super(view);
            stepView = view.findViewById(R.id.title_step);
            delText = view.findViewById(R.id.delText);
            delText.setOnClickListener(v -> {
                Toast.makeText(view.getContext(), stepView.getText().toString(), Toast.LENGTH_SHORT).show();;
            });
            stepView.setOnClickListener(v -> {
                stepView.toggle();
            });
        }
    }
}
