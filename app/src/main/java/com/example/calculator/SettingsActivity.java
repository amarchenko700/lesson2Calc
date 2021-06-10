package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private String ThemeName;
    public static final String KEY_TEXT_TO_DISPLAY = "KEY_TEXT_TO_DISPLAY";
    public static final String KEY_RESULT = "KEY_RESULT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        String value = getIntent().getStringExtra(KEY_TEXT_TO_DISPLAY);

        ThemeName = "";

        View.OnClickListener radioButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.radioBtnWinter: {
                        ThemeName = "Winter";
                        break;
                    }
                    case R.id.radioBtnSpring: {
                        ThemeName = "Spring";
                        break;
                    }
                    case R.id.radioBtnSummer: {
                        ThemeName = "Summer";
                        break;
                    }
                    case R.id.radioBtnAutumn: {
                        ThemeName = "Autumn";
                        break;
                    }
                    case R.id.chooseTheme: {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra(KEY_RESULT, ThemeName);
                        setResult(Activity.RESULT_OK, resultIntent);
                        finish();
                        break;
                    }
                    case R.id.cancelChoose: {
                        Intent resultIntent = new Intent();
                        finish();
                        break;
                    }
                }
            }
        };

        findViewById(R.id.radioBtnWinter).setOnClickListener(radioButtonListener);
        findViewById(R.id.radioBtnSpring).setOnClickListener(radioButtonListener);
        findViewById(R.id.radioBtnSummer).setOnClickListener(radioButtonListener);
        findViewById(R.id.radioBtnAutumn).setOnClickListener(radioButtonListener);

        findViewById(R.id.chooseTheme).setOnClickListener(radioButtonListener);
        findViewById(R.id.cancelChoose).setOnClickListener(radioButtonListener);
    }
}