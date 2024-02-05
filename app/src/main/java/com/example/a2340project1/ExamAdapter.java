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

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ExamViewHolder> {

    private List<Exam> examList;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public ExamAdapter(Context context, List<Exam> examList) {
        this.examList = examList;
        this.inflater = LayoutInflater.from(context);
    }

    public interface OnItemClickListener {
        void onItemClick(Exam exam);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.exam_item_layout, parent, false);
        return new ExamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamViewHolder holder, int position) {
        Exam exam = examList.get(position);
        holder.examNameTextView.setText(exam.getTaskName());
        holder.examDateTextView.setText(exam.getDueDate());
        holder.examLocationTextView.setText(exam.getLocation());

        // Set an OnClickListener for each item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(exam);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return examList.size();
    }
    public void updateData(List<Exam> newExamList) {
        examList = newExamList;
        notifyDataSetChanged();
    }

    public static class ExamViewHolder extends RecyclerView.ViewHolder {
        TextView examNameTextView;
        TextView examDateTextView;
        TextView examLocationTextView;

        public ExamViewHolder(@NonNull View itemView) {
            super(itemView);
            examNameTextView = itemView.findViewById(R.id.examNameTextView);
            examDateTextView = itemView.findViewById(R.id.examDateTextView);
            examLocationTextView = itemView.findViewById(R.id.examLocationTextView);
        }
    }
}

