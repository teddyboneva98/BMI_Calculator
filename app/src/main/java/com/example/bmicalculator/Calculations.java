package com.example.bmicalculator;

public class Calculations {
    private String weight;
    private String height;
    private String calculatedResult;

    public Calculations(String wei, String hei, String calculation) {
        weight = wei;
        height = hei;
        calculatedResult = calculation;
    }
    public String getWeight() {
        return weight;
    }

    public String getHeight() {
        return height;
    }

    public String getCalculatedResult() {
        return calculatedResult;
    }
}
