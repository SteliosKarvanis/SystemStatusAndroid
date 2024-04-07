package com.example.systemstatusapp.types;

public class Stat {
    private String name;
    private int value;

    public Stat(String name, int value) {
        this.name = name;
        this.value = value;
    }
    public Stat() {
        this.name = "";
        this.value = 0;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
