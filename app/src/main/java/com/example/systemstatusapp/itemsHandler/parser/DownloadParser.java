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
        if(downSpeed < 10000){
            item.setMainStatValue(downSpeed/100);
        } else {
            item.setMainStatValue(100);
        }
        item.setNumberStatValue(downSpeed);
        item.addPoint2();
        return item;
    }
}
