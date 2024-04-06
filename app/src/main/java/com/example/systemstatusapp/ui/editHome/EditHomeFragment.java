package com.example.systemstatusapp.ui.editHome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.systemstatusapp.adapters.ItemsAdapter;
import com.example.systemstatusapp.adapters.SelectItemsAdapter;
import com.example.systemstatusapp.databinding.FragmentEdithomeBinding;
import com.example.systemstatusapp.databinding.FragmentHomeBinding;
import com.example.systemstatusapp.parser.ParserFacade;
import com.example.systemstatusapp.types.Item;

import java.util.ArrayList;
import java.util.List;

public class EditHomeFragment extends Fragment {

    private FragmentEdithomeBinding binding;
    protected SelectItemsAdapter itemsAdapter;
    protected ParserFacade statsParser;
    protected RecyclerView.LayoutManager layoutManager;
    protected List<Item> items = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEdithomeBinding.inflate(inflater, container, false);
        statsParser = new ParserFacade();

        Item item = new Item();
        item.setTitle("First");
        item.setDescription("custom description");
        item.setUsed_percentage(Math.round(statsParser.getCpuStats()));
        item.setVisibility(true);
        items.add(item);

        layoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerView.setLayoutManager(layoutManager);

        itemsAdapter = new SelectItemsAdapter(items);
        binding.recyclerView.setAdapter(itemsAdapter);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void updateList(ArrayList<Item> newDataList) {
        items.clear();
        items.addAll(newDataList);
        itemsAdapter.notifyDataSetChanged();
    }

}