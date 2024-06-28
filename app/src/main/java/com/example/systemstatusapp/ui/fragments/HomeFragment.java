package com.example.systemstatusapp.ui.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.systemstatusapp.R;
import com.example.systemstatusapp.ui.adapters.ItemsAdapter;
import com.example.systemstatusapp.databinding.FragmentHomeBinding;
import com.example.systemstatusapp.itemsHandler.ItemsHandler;
import com.example.systemstatusapp.types.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//public class HomeFragment extends Fragment implements ItemsAdapter.OnItemClickListener {
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    protected ItemsAdapter itemsAdapter;
    protected ItemsHandler statsParser;
    protected RecyclerView.LayoutManager layoutManager;
    protected List<Item> items = new ArrayList<>();
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable updateRunnable;
    private static final int UPDATE_INTERVAL = 1000;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Set up recyclerView layout
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        layoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerView.setLayoutManager(layoutManager);

        // Parse Data
        statsParser = ItemsHandler.getInstance(getContext());
        items = statsParser.getItems();
        try {
            statsParser.updateItems();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Set up Adapter
        itemsAdapter = new ItemsAdapter(items);
//        itemsAdapter.setOnItemClickListener(this);
        binding.recyclerView.setAdapter(itemsAdapter);

        // Set up periodic update
        updateRunnable = new Runnable() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void run() {
                try {
                    statsParser.updateItems();
                    itemsAdapter.notifyDataSetChanged();
                } catch (IOException e) {
//                    e.printStackTrace();
                }
                // Re-agenda o runnable para rodar novamente após o intervalo definido
                handler.postDelayed(this, UPDATE_INTERVAL);
            }
        };
        handler.postDelayed(updateRunnable, UPDATE_INTERVAL);

        return binding.getRoot();
    }

//    @Override
//    public void onItemClick(Item item) {
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("item", item);
//        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
//        navController.navigate(R.id.nav_details, bundle);
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacks(updateRunnable); // Remove o runnable para evitar vazamento de memória
        binding = null;
    }
}
