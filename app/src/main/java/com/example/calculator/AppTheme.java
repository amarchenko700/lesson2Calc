package com.example.calculator;

public enum AppTheme {

    WINTER(R.style.ThemeWinter, "Winter"),
    SPRING(R.style.ThemeSpring, "Spring"),
    SUMMER(R.style.ThemeSummer, "Summer"),
    AUTUMN(R.style.ThemeAutumn, "Autumn");

    AppTheme(int resource, String key) {
        this.resource = resource;
        this.key = key;
    }

    private int resource;

    private String key;

    public int getResource() {
        return resource;
    }

    public String getKey() {
        return key;
    }
}
