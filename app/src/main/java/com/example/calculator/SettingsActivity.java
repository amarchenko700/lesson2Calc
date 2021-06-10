package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private AppTheme actualTheme;

    public static final String KEY_THEME_TO_DISPLAY = "KEY_THEME_TO_DISPLAY";
    public static final String KEY_RESULT = "KEY_RESULT";
    private final int[] radioBtns = new int[]{R.id.radioBtnWinter, R.id.radioBtnSpring,
            R.id.radioBtnSummer, R.id.radioBtnAutumn};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        View.OnClickListener radioButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.radioBtnWinter: {
                        actualTheme = AppTheme.WINTER;
                        break;
                    }
                    case R.id.radioBtnSpring: {
                        actualTheme = AppTheme.SPRING;
                        break;
                    }
                    case R.id.radioBtnSummer: {
                        actualTheme = AppTheme.SUMMER;
                        break;
                    }
                    case R.id.radioBtnAutumn: {
                        actualTheme = AppTheme.AUTUMN;
                        break;
                    }
                    case R.id.chooseTheme: {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra(KEY_RESULT, actualTheme);
                        setResult(Activity.RESULT_OK, resultIntent);
                        finish();
                        break;
                    }
                    case R.id.cancelChoose: {
                        finish();
                        break;
                    }
                }
            }
        };

        for (int radioBtn: radioBtns){
            RadioButton rb = findViewById(radioBtn);
            rb.setOnClickListener(radioButtonListener);
            rb.setChecked(false);
        }

        findViewById(R.id.chooseTheme).setOnClickListener(radioButtonListener);
        findViewById(R.id.cancelChoose).setOnClickListener(radioButtonListener);

        AppTheme currentTheme = (AppTheme) getIntent().getSerializableExtra(KEY_THEME_TO_DISPLAY);
        if(currentTheme != null) {
            RadioButton rb;
            switch (currentTheme) {
                case WINTER: {
                    rb = findViewById(R.id.radioBtnWinter);
                    break;
                }
                case SPRING: {
                    rb = findViewById(R.id.radioBtnSpring);
                    break;
                }
                case SUMMER: {
                    rb = findViewById(R.id.radioBtnSummer);
                    break;
                }
                case AUTUMN: {
                    rb = findViewById(R.id.radioBtnAutumn);
                    break;
                }
                default:{
                    rb = findViewById(R.id.radioBtnWinter);
                }
            }
            rb.setChecked(true);
        }
    }
}