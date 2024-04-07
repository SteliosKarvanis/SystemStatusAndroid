package com.example.systemstatusapp.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.systemstatusapp.databinding.FragmentDetailsBinding;
import com.example.systemstatusapp.types.Item;
import com.example.systemstatusapp.ui.adapters.DetailsAdapter;


public class DetailsFragment extends Fragment {

    private FragmentDetailsBinding binding;
    protected DetailsAdapter statsAdapter;
    protected RecyclerView.LayoutManager layoutManager;
    Item item;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        item = (Item) getArguments().getSerializable("item");
        layoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerView.setLayoutManager(layoutManager);
        statsAdapter = new DetailsAdapter(item.getStats());
        binding.recyclerView.setAdapter(statsAdapter);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}