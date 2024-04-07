package com.example.systemstatusapp.ui.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.systemstatusapp.types.Item;

import java.util.List;
import com.example.systemstatusapp.R;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder> {
    private List<Item> items;
    private OnItemClickListener listener;
    public ItemsAdapter(List<Item> items) {
        this.items = items;
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public interface OnItemClickListener {
        void onItemClick(Item item);
    }
    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ItemsViewHolder(itemsView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {
        Item item = items.get(position);
        holder.descriptionView.setText(item.getDescription());
        holder.titleView.setText(item.getTitle());
        holder.progressBarText.setText(Integer.toString(item.getMainStatValue()) + "%");
        holder.progressBar.setProgress(item.getMainStatValue(), true);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(item);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ItemsViewHolder extends RecyclerView.ViewHolder {
        TextView titleView;
        TextView descriptionView;
        ProgressBar progressBar;
        TextView progressBarText;

        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.titleView);
            descriptionView = itemView.findViewById(R.id.descriptionView);
            progressBar = itemView.findViewById(R.id.progressBar);
            progressBarText = itemView.findViewById(R.id.progressBarText);
        }
    }
}
