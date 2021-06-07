package com.example.calculator;

public enum AppTheme {

    PORTRAIT(R.style.CalculatorTheme, "portrait"),
    LANDSCAPE(R.style.CalculatorThemeLand, "landscape");

    AppTheme(int resource, String key) {
        this.resource = resource;
        this.key = key;
    }

    private int resource;

    private String key;

    public int getResource() {
        return resource;
    }

    public String  getKey() {
        return key;
    }
}
