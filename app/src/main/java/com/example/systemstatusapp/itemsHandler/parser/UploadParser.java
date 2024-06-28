package com.example.systemstatusapp.itemsHandler.parser;

import android.content.Context;

import com.example.systemstatusapp.types.Item;

import java.io.IOException;

public class UploadParser implements Parser {

    private final Context context;

    public UploadParser(Context context) {
        this.context = context;
    }

    @Override
    public Item updateItem(Item item) throws IOException {
        item.setMainStatValue(0);
        item.setNumberStatValue(0);

        return item;
    }
}
