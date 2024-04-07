package com.example.systemstatusapp.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.systemstatusapp.R;
import com.example.systemstatusapp.ui.adapters.ItemsAdapter;
import com.example.systemstatusapp.databinding.FragmentHomeBinding;
import com.example.systemstatusapp.itemsHandler.ItemsHandler;
import com.example.systemstatusapp.types.Item;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements ItemsAdapter.OnItemClickListener{

    private FragmentHomeBinding binding;
    protected ItemsAdapter itemsAdapter;
    protected ItemsHandler statsParser;
    protected RecyclerView.LayoutManager layoutManager;
    protected List<Item> items = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        statsParser = ItemsHandler.getInstance();

        layoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerView.setLayoutManager(layoutManager);

        items = statsParser.getItems();
        itemsAdapter = new ItemsAdapter(items);
        itemsAdapter.setOnItemClickListener(this);
        binding.recyclerView.setAdapter(itemsAdapter);

        return binding.getRoot();
    }
    @Override
    public void onItemClick(Item item) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("item", item);
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
        navController.navigate(R.id.nav_details, bundle);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}