package com.example.systemstatusapp.itemsHandler;

import com.example.systemstatusapp.itemsHandler.parser.Parser;
import com.example.systemstatusapp.types.Item;

import java.io.IOException;

public class ItemExtractor {
    private Item item;
    private Parser parser;

    public ItemExtractor(Parser parser, Item item) {
        this.item = item;
        this.parser = parser;
    }

    public void updateItem() throws IOException {
        item = parser.updateItem(item);
    }
    public Item getItem() {
        return item;
    }
}
