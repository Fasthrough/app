package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterToDo extends RecyclerView.Adapter<AdapterToDo.ViewHolderToDo> {
    private ArrayList<ModelToDo> modelToDos;
    private Context context;
    private OnItemClickListener listener;

    public AdapterToDo(Context context, ArrayList<ModelToDo> modelToDo) {
        this.context = context;
        this.modelToDos = modelToDo;
    }

    @NonNull
    @Override
    public ViewHolderToDo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
        return new ViewHolderToDo(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderToDo holder, int position) {
        ModelToDo now = modelToDos.get(position);
        holder.task.setText(now.getTask());
        holder.date.setText(now.getDate());
    }

    @Override
    public int getItemCount() {
        return modelToDos.size();
    }

    public class ViewHolderToDo extends RecyclerView.ViewHolder {
        public TextView task, date;
        public ImageView del;

        public ViewHolderToDo(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            task = itemView.findViewById(R.id.tv_task);
            date = itemView.findViewById(R.id.tv_date);
            del = itemView.findViewById(R.id.btt_del);

            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            listener.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
