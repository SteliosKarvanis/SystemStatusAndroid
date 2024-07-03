package com.example.systemstatusapp.types;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Item implements Serializable {
    private final String title;
    private final String ProgressBarDescription;
    private String NumberDescription;
    private boolean visible;
    private int mainStatValue;
    private int numberStatValue;
    private List<Stat> stats;
    private BarData barData;
    private List<BarEntry> barEntries;
    private static final int maxLen = 20;

    public Item(String title, String ProgressBarDescription, String NumberDescription, boolean visible) {
        this.title = title;
        this.ProgressBarDescription = ProgressBarDescription;
        this.NumberDescription = NumberDescription;
        this.visible = visible;
        this.mainStatValue = 0;
        this.numberStatValue = 0;
        this.stats = new ArrayList<>();
        this.barEntries = new ArrayList<>();
    }
    public String getTitle() {
        return title;
    }
    public String getProgressBarDescription() {
        return ProgressBarDescription;
    }
    public String getNumberDescription() { return NumberDescription; }
    public boolean isVisible() {
        return visible;
    }
    public void setVisibility(boolean visible) {
        this.visible = visible;
    }
    public int getMainStatValue() {
        return mainStatValue;
    }
    public int getNumberStatValue(){
        return numberStatValue;
    }
    public void setMainStatValue(int mainStatValue) {
        this.mainStatValue = mainStatValue;
    }
    public void setNumberStatValue(int numberStatValue) {
        this.numberStatValue = numberStatValue;
    }
    public void setNumberDescription(String description) {this.NumberDescription = description;}
    public List<Stat> getStats() {
        return stats;
    }
    public void setStats(List<Stat> stats) {
        this.stats = stats;
    }

    public void addPoint() {
        barEntries.add(new BarEntry(barEntries.size(), mainStatValue));
        if (barEntries.size() > maxLen) {
            barEntries.remove(0);
        }
        for (int i = 0; i < barEntries.size(); i++) {
            barEntries.get(i).setX(i);
        }
        BarDataSet barDataSet = new BarDataSet(barEntries, "Infos");
        this.barData = new BarData(barDataSet);
    }

    public void addPoint2() {
        barEntries.add(new BarEntry(barEntries.size(), numberStatValue));
        if (barEntries.size() > maxLen) {
            barEntries.remove(0);
        }
        for (int i = 0; i < barEntries.size(); i++) {
            barEntries.get(i).setX(i);
        }
        BarDataSet barDataSet = new BarDataSet(barEntries, "Infos");
        this.barData = new BarData(barDataSet);
    }

    public BarData getBarData(){
        return this.barData;
    }
}
