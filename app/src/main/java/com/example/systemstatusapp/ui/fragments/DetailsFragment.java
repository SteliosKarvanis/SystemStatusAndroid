package com.example.systemstatusapp.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.systemstatusapp.R;
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
        // Set up recyclerView layout
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        layoutManager = new LinearLayoutManager(getActivity());
        // Parse Item
        assert getArguments() != null;
        item = (Item) getArguments().getSerializable("item");
        // Set up Adapter
        assert item != null;
        statsAdapter = new DetailsAdapter(item.getStats());
        // TODO: add button action to return
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}