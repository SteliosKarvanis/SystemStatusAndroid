package com.example.systemstatusapp.parser;

public class ParserFacade {
    CpuParser cpuParser;

    public ParserFacade() {
        this.cpuParser = new CpuParser();
    }

    public float getCpuStats() {
        return cpuParser.readCpuUsage();
    }
}
