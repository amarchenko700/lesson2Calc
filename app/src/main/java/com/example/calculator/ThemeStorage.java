package com.example.calculator;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class ThemeStorage {

    private static final String KEY_APP_THEME = "KEY_APP_THEME";

    private final SharedPreferences sharedPreferences;

    public ThemeStorage(Context context) {
        sharedPreferences = context.getSharedPreferences("app_themes", MODE_PRIVATE);
    }

    public AppTheme getTheme() {
        String key = sharedPreferences.getString(KEY_APP_THEME, AppTheme.PORTRAIT.getKey());

        for (AppTheme theme : AppTheme.values()) {
            if (theme.getKey().equals(key)) {
                return theme;
            }
        }

        throw new IllegalStateException("Wrong!");
    }

    public void setTheme(AppTheme theme) {
        sharedPreferences.edit()
                .putString(KEY_APP_THEME, theme.getKey())
                .apply();
    }
}
