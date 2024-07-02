package com.example.systemstatusapp.ui.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import com.example.systemstatusapp.R;
import com.example.systemstatusapp.types.Stat;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ItemsViewHolder> {
    private List<Stat> stats;
    public DetailsAdapter(List<Stat> stats) {
        this.stats = stats;
    }
    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_details_layout, parent, false);
        return new ItemsViewHolder(itemsView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {
        Stat stat = stats.get(position);
        holder.titleView.setText(stat.getName());
        holder.progressBarText.setText(Integer.toString(stat.getValue()) + "%");
        holder.progressBar.setProgress(stat.getValue(), true);

    }
    @Override
    public int getItemCount() {
        return this.stats.size();
    }

    public static class ItemsViewHolder extends RecyclerView.ViewHolder {
        TextView titleView;
        ProgressBar progressBar;
        TextView progressBarText;

        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.titleView);
            progressBar = itemView.findViewById(R.id.progressBar);
            progressBarText = itemView.findViewById(R.id.progressBarText);
        }
    }
}
