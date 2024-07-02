package com.example.systemstatusapp.itemsHandler.parser;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;

import com.example.systemstatusapp.types.Item;

import java.io.IOException;

public class DownloadParser implements Parser {

    private final Context context;

    public DownloadParser(Context context) {
        this.context = context;
    }

    @Override
    public Item updateItem(Item item) throws IOException {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkCapabilities nc = cm.getNetworkCapabilities(cm.getActiveNetwork());
        int downSpeed = 0;
        if (nc != null) {
            downSpeed = nc.getLinkDownstreamBandwidthKbps();
        }
        if(downSpeed == 0){
            item.setMainStatValue(0);
        } else if(downSpeed < 2000){
            item.setMainStatValue(20);
        } else if(downSpeed < 4000){
            item.setMainStatValue(40);
        } else if(downSpeed < 6000){
            item.setMainStatValue(60);
        } else if(downSpeed < 8000){
            item.setMainStatValue(80);
        } else {
            item.setMainStatValue(100);
        }
        item.setNumberStatValue(downSpeed);
        item.addPoint();
        return item;
    }
}
