package com.example.systemstatusapp.itemsHandler.parser;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

import com.example.systemstatusapp.types.Item;

import java.io.IOException;

public class BateryParser implements Parser {

    private final Context context;

    public BateryParser(Context context) {
        this.context = context;
    }

    @Override
    public Item updateItem(Item item) throws IOException {
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);
        BatteryManager mBatteryManager = (BatteryManager)context.getSystemService(Context.BATTERY_SERVICE);

        assert batteryStatus != null;
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        float batteryPct = level * 100 / (float)scale;
        long rTime = 0;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            rTime = mBatteryManager.computeChargeTimeRemaining()/1000;
        }

        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;

        if(isCharging){
            item.setNumberDescription("Tempo de Carga (min)");
        } else{
            item.setNumberDescription("Descarregando");
        }

        item.setMainStatValue((int) batteryPct);
        item.setNumberStatValue((int) rTime/60);
        item.addPoint();
        return item;
    }
}
