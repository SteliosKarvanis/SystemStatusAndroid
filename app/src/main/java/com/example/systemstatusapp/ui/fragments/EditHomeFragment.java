package com.example.systemstatusapp.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.systemstatusapp.ui.adapters.SelectItemsAdapter;
import com.example.systemstatusapp.databinding.FragmentEdithomeBinding;
import com.example.systemstatusapp.itemsHandler.ItemsHandler;
import com.example.systemstatusapp.types.Item;

import java.util.ArrayList;
import java.util.List;

public class EditHomeFragment extends Fragment {

    private FragmentEdithomeBinding binding;
    protected SelectItemsAdapter itemsAdapter;
    protected ItemsHandler statsParser;
    protected RecyclerView.LayoutManager layoutManager;
    protected List<Item> items = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEdithomeBinding.inflate(inflater, container, false);
        statsParser = ItemsHandler.getInstance();

        layoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerView.setLayoutManager(layoutManager);

        items = statsParser.getItems();
        itemsAdapter = new SelectItemsAdapter(items);
        binding.recyclerView.setAdapter(itemsAdapter);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}