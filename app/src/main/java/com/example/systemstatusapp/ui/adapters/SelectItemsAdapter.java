package com.example.systemstatusapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.systemstatusapp.types.Item;

import java.util.List;
import com.example.systemstatusapp.R;

public class SelectItemsAdapter extends RecyclerView.Adapter<SelectItemsAdapter.SelectItemsViewHolder> {
    private List<Item> items;
    public SelectItemsAdapter(List<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public SelectItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_item_layout, parent, false);
        return new SelectItemsViewHolder(itemsView);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectItemsViewHolder holder, int position) {
        Item item = items.get(position);
        holder.checkBox.setChecked(item.isVisible());
        holder.checkBox.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class SelectItemsViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;

        public SelectItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }
}
