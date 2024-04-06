package com.example.systemstatusapp.itemsHandler;

import android.util.Log;

import com.example.systemstatusapp.itemsHandler.parser.CpuParser;
import com.example.systemstatusapp.types.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemsHandler {
    private static ItemsHandler instance;
    private ItemsHandler() {
        extractors = new ArrayList<>();
        extractors.add(new ItemExtractor(new CpuParser(), new Item("CPU", "CPU Stats", true)));
    }
    public static ItemsHandler getInstance() {
        // Check if the instance is null, then create a new instance
        if (instance == null) {
            instance = new ItemsHandler();
        }
        return instance;
    }
    private static List<ItemExtractor> extractors;
    public void updateItems(){
        Log.d("Update", "Updating");
        for (ItemExtractor extractor : extractors){
            // TODO: to create a item historic, maybe will be needed to always extract the item
            if (extractor.item.isVisible()){
                extractor.updateItem();
            }
        }
    }

    public List<Item> getItems(){
        List<Item> items = new ArrayList<>();
        for (ItemExtractor extractor : extractors){
            if (extractor.item.isVisible()){
                items.add(extractor.getItem());
            }
        }
        return items;
    }
}
