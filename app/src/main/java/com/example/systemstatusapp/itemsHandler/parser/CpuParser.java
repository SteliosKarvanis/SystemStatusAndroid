package com.example.systemstatusapp.itemsHandler.parser;

import android.util.Log;

import com.example.systemstatusapp.types.Item;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.regex.Pattern;
import java.util.concurrent.ThreadLocalRandom;

public class CpuParser implements Parser {
    public CpuParser() {
    }

    private static int sLastCpuCoreCount = -1;

    public Item updateItem(Item item) throws IOException {
        double mediamCPU = 0.0F;
        for (int i = 0; i < calcCpuCoreCount(); i++) {
            mediamCPU += (double) takeCurrentCpuFreq(i);
        }
        int cont = calcCpuCoreCount();
        item.setMainStatValue((int) (mediamCPU / cont));
        item.setNumberStatValue(cont);
        item.addPoint();
        return item;
    }



    private static int readIntegerFile(String filePath) {
        try {
            return new RandomAccessFile(filePath, "r").read();
        } catch (Exception e) {
            return 0;
        }
    }

    private static int takeCurrentCpuFreq(int coreIndex) {
        return readIntegerFile("/sys/devices/system/cpu/cpu" + coreIndex + "/cpufreq/scaling_cur_freq");
    }
    private static int takeMinCpuFreq(int coreIndex){
        return readIntegerFile("/sys/devices/system/cpu/cpu" + coreIndex + "/cpufreq/cpuinfo_min_freq");
    }
    private static int takeMaxCpuFreq(int coreIndex){
        return readIntegerFile("/sys/devices/system/cpu/cpu" + coreIndex + "/cpufreq/cpuinfo_max_freq");
    }

    public static int calcCpuCoreCount() {
        if (sLastCpuCoreCount >= 1) {
            return sLastCpuCoreCount;
        }

        try {
            final File dir = new File("/sys/devices/system/cpu/");
            final File[] files = dir.listFiles(new FileFilter() {
                public boolean accept(File pathname) {
                    return Pattern.matches("cpu[0-9]", pathname.getName());
                }
            });
            assert files != null;
            sLastCpuCoreCount = files.length;
        } catch(Exception e) {
            sLastCpuCoreCount = Runtime.getRuntime().availableProcessors();
        }
        return sLastCpuCoreCount;
    }
}
