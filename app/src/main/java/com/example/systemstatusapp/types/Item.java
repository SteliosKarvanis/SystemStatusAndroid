package com.example.systemstatusapp.types;

import java.io.Serializable;

public class Item implements Serializable {
    private String title;
    private String description;
    private int used_percentage;
    private boolean visibility;

    public boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUsed_percentage() {
        return used_percentage;
    }

    public void setUsed_percentage(int used_percentage) {
        this.used_percentage = used_percentage;
    }
}
