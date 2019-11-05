package com.apurba.infinityrecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DataItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DataItem> dataSet;

    DataItemAdapter(List<DataItem> dataSet){
        this.dataSet = dataSet;
    }

    public void addAll(List<DataItem> dataItems){
        dataSet.addAll(dataItems);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.data_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder)holder;
        viewHolder.tvName.setText(dataSet.get(position).getName());
        viewHolder.tvId.setText(dataSet.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvId;
        ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvId = itemView.findViewById(R.id.tv_id);
        }
    }
}
