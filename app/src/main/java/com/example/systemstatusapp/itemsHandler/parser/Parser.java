package com.example.systemstatusapp.itemsHandler.parser;

import com.example.systemstatusapp.types.Item;

import java.io.IOException;

public interface Parser {
    Item updateItem(Item item) throws IOException;
}
