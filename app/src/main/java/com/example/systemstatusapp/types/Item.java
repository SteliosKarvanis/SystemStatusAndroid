package com.example.systemstatusapp.types;

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

    public Item(String title, String ProgressBarDescription, String NumberDescription, boolean visible) {
        this.title = title;
        this.ProgressBarDescription = ProgressBarDescription;
        this.NumberDescription = NumberDescription;
        this.visible = visible;
        this.mainStatValue = 0;
        this.numberStatValue = 0;
        this.stats = new ArrayList<>();
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
}
