package com.example.systemstatusapp.itemsHandler;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.systemstatusapp.itemsHandler.parser.CpuParser;
import com.example.systemstatusapp.itemsHandler.parser.MemoryParser;
import com.example.systemstatusapp.itemsHandler.parser.StorageParser;
import com.example.systemstatusapp.types.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemsHandler {
    // TODO: remove Singleton hack to pass data between fragments
    private static ItemsHandler instance;
    private static Map<String, ItemExtractor> extractors_map = new HashMap<>();
    private Context context;

    private ItemsHandler(Context context) {
        this.context = context;
        List<ItemExtractor> extractors = new ArrayList<>();
        extractors.add(new ItemExtractor(new CpuParser(), new Item("CPU", "Uso de CPU", "Número de Núcleos", true)));
        extractors.add(new ItemExtractor(new MemoryParser(context), new Item("Memória RAM", "Utilizada %", "Memória Usada (MB)", true)));
        extractors.add(new ItemExtractor(new StorageParser(context), new Item("Memória", "Lotação", "Memória Livre (MB)", true)));

        ingestExtractors(extractors);
    }

    public static ItemsHandler getInstance(Context context) {
        // Check if the instance is null, then create a new instance
        if (instance == null) {
            instance = new ItemsHandler(context);
        }
        return instance;
    }

    public void updateItems() throws IOException {
        Log.d("Update", "Updating");
        for (ItemExtractor extractor : extractors_map.values()){
            extractor.updateItem();
        }
    }

    public List<Item> getItems(){
        List<Item> items = new ArrayList<>();
        for (ItemExtractor extractor : extractors_map.values()){
            items.add(extractor.getItem());
        }
        return items;
    }

    public List<Item> getVisibleItems(){
        List<Item> items = new ArrayList<>();
        for (ItemExtractor extractor : extractors_map.values()){
            if (extractor.getItem().isVisible()){
                items.add(extractor.getItem());
            }
        }
        return items;
    }

    public void setItemVisibilityFromTitle(String title, boolean visible){
        @Nullable ItemExtractor extractor = extractors_map.get(title);
        if (extractor != null){
            extractor.getItem().setVisibility(visible);
        }
    }

    private void ingestExtractors(List<ItemExtractor> extractors){
        for (ItemExtractor extractor : extractors) {
            updateItem(extractor);
        }
    }

    private void updateItem(ItemExtractor extractor){
        extractors_map.put(extractor.getItem().getTitle(), extractor);
    }
}
