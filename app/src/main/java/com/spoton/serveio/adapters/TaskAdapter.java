package com.spoton.serveio.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.spoton.serveio.R;
import com.spoton.serveio.model.Task;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

    ArrayList<Task> array_tasks;
    Context context;

    public TaskAdapter(Context context, ArrayList<Task> array_tasks) {
        this.array_tasks = array_tasks;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_task_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(array_tasks.get(position).getTitle());
        holder.location.setText(array_tasks.get(position).getTaskLocation());
    }

    @Override
    public int getItemCount() {
        return array_tasks.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title,location;
        ImageView myImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTextView);
            location = itemView.findViewById(R.id.locTextView);
            myImage = itemView.findViewById(R.id.iconImageView);
        }
    }
}
