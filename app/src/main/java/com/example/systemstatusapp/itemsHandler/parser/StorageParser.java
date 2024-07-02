package com.example.systemstatusapp.itemsHandler.parser;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import com.example.systemstatusapp.types.Item;

import java.io.IOException;

public class StorageParser implements Parser {

    private Context context;

    public StorageParser(Context context) {
        this.context = context;
    }

    @Override
    public Item updateItem(Item item) throws IOException {
        // Get storage information
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());

        long totalBytes;
        long availableBytes;

        totalBytes = statFs.getTotalBytes();
        availableBytes = statFs.getAvailableBytes();

        // Convert bytes to gigabytes
        float totalGB = (float) totalBytes / (1024 * 1024);
        float availableGB = (float) availableBytes / (1024 * 1024);
        float usedGB = totalGB - availableGB;

        // Calculate used storage percentage
        int usedPercentage = (int) ((totalBytes - availableBytes) * 100 / totalBytes);

        // Update item with storage information
        item.setMainStatValue(usedPercentage); // Percentage of used storage
        item.setNumberStatValue((int) availableGB); // Used storage in GB
        item.addPoint();
        return item;
    }
}
