package com.example.a2340project1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.AssignmentViewHolder> {

    private List<Assignment> assignmentList;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public AssignmentAdapter(Context context, List<Assignment> assignmentList) {
        this.assignmentList = assignmentList;
        this.inflater = LayoutInflater.from(context);
    }

    public interface OnItemClickListener {
        void onItemClick(Assignment assignment);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public AssignmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.assignment_item_layout, parent, false);
        return new AssignmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentViewHolder holder, int position) {
        Assignment assignment = assignmentList.get(position);
        holder.assignmentNameTextView.setText(assignment.getTaskName());
        holder.assignmentDateTextView.setText(assignment.getDueDate());
        holder.assignmentTimeTextView.setText(assignment.getTime());

        // Set an OnClickListener for each item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(assignment);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return assignmentList.size();
    }
    public void updateData(List<Assignment> newAssignmentList) {
        assignmentList = newAssignmentList;
        notifyDataSetChanged();
    }

    public static class AssignmentViewHolder extends RecyclerView.ViewHolder {
        TextView assignmentNameTextView;
        TextView assignmentDateTextView;
        TextView assignmentTimeTextView;

        public AssignmentViewHolder(@NonNull View itemView) {
            super(itemView);
            assignmentNameTextView = itemView.findViewById(R.id.assignmentNameTextView);
            assignmentDateTextView = itemView.findViewById(R.id.assignmentDateTextView);
            assignmentTimeTextView = itemView.findViewById(R.id.assignmentTimeTextView);
        }
    }
}


