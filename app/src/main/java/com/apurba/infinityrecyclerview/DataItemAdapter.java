package com.apurba.infinityrecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class DataItemAdapter extends PagedListAdapter<DataItem, DataItemAdapter.ViewHolder> {


    public DataItemAdapter() {
        super(DIFF_CALLBACK);
    }
    public static final DiffUtil.ItemCallback<DataItem> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<DataItem>() {
                @Override
                public boolean areItemsTheSame(DataItem oldItem, DataItem newItem) {
                    return oldItem.getId() == newItem.getId();
                }
                @Override
                public boolean areContentsTheSame(DataItem oldItem, DataItem newItem) {
                    return (oldItem.getName().equals(newItem.getName()) && oldItem.getId() == newItem.getId());
                }
            };


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.data_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataItem dataItem = getItem(position);
        if (dataItem != null) {
            holder.bindTo(dataItem);
        } else {
            // Null defines a placeholder item - PagedListAdapter automatically
            // invalidates this row when the actual object is loaded from the
            // database.
            holder.clear();
        }
    }



    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvId;
        ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvId = itemView.findViewById(R.id.tv_id);
        }
        void bindTo(DataItem dataItem){
            tvName.setText(dataItem.getName());
            tvId.setText(dataItem.getId() + "");
        }

        void clear(){
            tvName.setText("");
            tvId.setText("");
        }
    }
}
