package com.magiri.KaziBora;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HelperAdapter extends RecyclerView.Adapter<HelperAdapter.ViewHolder> {
    Context context;
    ArrayList<Task> task;

    public HelperAdapter(Context context, ArrayList<Task> task) {
        this.context = context;
        this.task = task;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task job=task.get(position);
        holder.taskTitle.setText(job.getTaskTitle());
        holder.taskDescription.setText(job.getTaskDescription());

    }

    @Override
    public int getItemCount() {
        return task.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView taskTitle,taskDescription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskTitle = itemView.findViewById(R.id.taskTxt);
            taskDescription = itemView.findViewById(R.id.descTxt);
        }
    }
    public interface TaskClickListener{
        void onTaskClick(int position);
    }
}
