package com.example.systemstatusapp.itemsHandler;

import com.example.systemstatusapp.itemsHandler.parser.Parser;
import com.example.systemstatusapp.types.Item;

public class ItemExtractor {
    public Item item;
    private Parser parser;

    public ItemExtractor(Parser parser, Item item) {
        this.item = item;
        this.parser = parser;
    }

    public void updateItem() {
        item = parser.updateItem(item);
    }
    public Item getItem() {
        return item;
    }
}
