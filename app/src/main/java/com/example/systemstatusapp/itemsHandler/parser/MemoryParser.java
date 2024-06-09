package com.example.systemstatusapp.itemsHandler.parser;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import com.example.systemstatusapp.types.Item;

import java.io.IOException;

public class MemoryParser implements Parser {

    private final Context context;

    public MemoryParser(Context context) {
        this.context = context;
    }

    @Override
    public Item updateItem(Item item) throws IOException {
        // Get memory information
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(memoryInfo);

        float totalMemory = (float) memoryInfo.totalMem / (1024 * 1024);
        float availableMemory = (float) memoryInfo.availMem / (1024 * 1024);
        int usedMemoryPercentage = (int) ((memoryInfo.totalMem - memoryInfo.availMem) * 100 / memoryInfo.totalMem);

        // Update item with memory information
        item.setMainStatValue(usedMemoryPercentage); // Percentage of used RAM
        item.setNumberStatValue((int) (totalMemory - availableMemory)); // Used memory in GB

        return item;
    }
}
