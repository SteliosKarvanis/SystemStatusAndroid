package com.example.systemstatusapp.types;

import java.util.List;

public class Item {
    private String title;
    private String description;
    public boolean visible;
    private int mainStatValue;
    private List<Stat> stats;

    public Item(String title, String description, boolean visible) {
        this.title = title;
        this.description = description;
        this.visible = visible;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public boolean isVisible() {
        return visible;
    }
    public int getMainStatValue() {
        return mainStatValue;
    }
    public void setMainStatValue(int mainStatValue) {
        this.mainStatValue = mainStatValue;
    }
    public List<Stat> getStats() {
        return stats;
    }
    public void setStats(List<Stat> stats) {
        this.stats = stats;
    }
}
