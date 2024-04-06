package com.example.systemstatusapp.itemsHandler.parser;

import android.util.Log;

import com.example.systemstatusapp.types.Item;

import java.io.IOException;
import java.io.RandomAccessFile;

public class CpuParser implements Parser {
    public CpuParser() {
    }

    public Item updateItem(Item item) {
        try {
            RandomAccessFile reader = new RandomAccessFile("/proc/stat", "r");
            String load = reader.readLine();

            String[] toks = load.split(" +");  // Split on one or more spaces

            long idle1 = Long.parseLong(toks[4]);
            long cpu1 = Long.parseLong(toks[2]) + Long.parseLong(toks[3]) + Long.parseLong(toks[5])
                    + Long.parseLong(toks[6]) + Long.parseLong(toks[7]) + Long.parseLong(toks[8]);

            try {
                Thread.sleep(360);
            } catch (Exception e) {}

            reader.seek(0);
            load = reader.readLine();
            reader.close();

            toks = load.split(" +");

            long idle2 = Long.parseLong(toks[4]);
            long cpu2 = Long.parseLong(toks[2]) + Long.parseLong(toks[3]) + Long.parseLong(toks[5])
                    + Long.parseLong(toks[6]) + Long.parseLong(toks[7]) + Long.parseLong(toks[8]);

            float usage = (float)(cpu2 - cpu1) / ((cpu2 + idle2) - (cpu1 + idle1));
            // TODO: check if 100* is correct
            usage = 100*usage;
            Log.d("CPU", "CPU: " + Float.toString(usage));
            item.setMainStatValue(Math.round(usage));
            return item;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        item.setMainStatValue(0);
        return item;
    }
}