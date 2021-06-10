package com.example.calculator;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.audiofx.BassBoost;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String OBJECT_CALCULATOR = "OBJECT_CALCULATOR";
    private Calculator calculator;
    private TextView textView, textViewMemory;
    private AppTheme actualTheme;
    private ThemeStorage storage;

    private final int[] numberButtonIds = new int[]{R.id.key_0, R.id.key_1, R.id.key_2, R.id.key_3,
            R.id.key_4, R.id.key_5, R.id.key_6, R.id.key_7, R.id.key_8, R.id.key_9};

    private final int[] numberOperationIds = new int[]{R.id.key_addition, R.id.key_subtraction,
            R.id.key_multiplication, R.id.key_division};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        storage = new ThemeStorage(this);
        actualTheme = storage.getTheme();
        if(actualTheme == null){
            actualTheme = AppTheme.WINTER;
        }
        setTheme(storage.getTheme().getResource());

        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.expressionString);
        textViewMemory = findViewById(R.id.textMemory);

        if (savedInstanceState == null) {
            calculator = new Calculator();
        } else {
            calculator = (Calculator) savedInstanceState.getSerializable(OBJECT_CALCULATOR);
            textView.setText(calculator.getExpressionString());
        }

        if (calculator.getMemoryValue() == 0) {
            textViewMemory.setVisibility(View.INVISIBLE);
        } else {
            textViewMemory.setVisibility(View.VISIBLE);
        }
        //region Установка_Listener
        CalcButtonListener calcButtonListener = new CalcButtonListener(calculator);

        setClickListenerOnButton(R.id.key_MC, calcButtonListener);
        setClickListenerOnButton(R.id.key_MR, calcButtonListener);
        setClickListenerOnButton(R.id.key_MAsc, calcButtonListener);
        setClickListenerOnButton(R.id.key_MDesc, calcButtonListener);

        setClickListenerOnButton(R.id.key_bracketStart, calcButtonListener);
        setClickListenerOnButton(R.id.key_bracketEnd, calcButtonListener);

        setClickListenerOnButton(R.id.key_CE, calcButtonListener);
        setClickListenerOnButton(R.id.key_C, calcButtonListener);
        setClickListenerOnButton(R.id.key_delete, calcButtonListener);

        for (int i = 0; i < numberButtonIds.length; i++) {
            int index = i;
            findViewById(numberButtonIds[i]).setOnClickListener(v -> {
                calculator.addNumber(String.valueOf(index));
                textView.setText(calculator.getExpressionString());
            });
        }

        for (int numberOperationId : numberOperationIds) {
            findViewById(numberOperationId).setOnClickListener(v -> {
                Button btn = (Button) v;
                calculator.addOperations(btn.getText().toString());
                textView.setText(calculator.getExpressionString());
            });
        }

        setClickListenerOnButton(R.id.key_equally, calcButtonListener);
        //endregion
        ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == Activity.RESULT_OK){
                    if(result.getData() != null){
                        actualTheme = (AppTheme) result.getData().getSerializableExtra(SettingsActivity.KEY_RESULT);
                        storage.setTheme(actualTheme);
                        recreate();
                    }
                }
            }
        });

        ActivityResultLauncher<AppTheme> launcher2 = registerForActivityResult(new LoginResultContract(), new ActivityResultCallback<AppTheme>() {
                    @Override
                    public void onActivityResult(AppTheme result) {
                        if (result != null) {
                            actualTheme = result;
                            storage.setTheme(actualTheme);
                            recreate();
                        }
                    }
                }
        );

        findViewById(R.id.btnSetting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                intent.putExtra(SettingsActivity.KEY_THEME_TO_DISPLAY, actualTheme);
                launcher.launch(intent);
                //launcher2.launch(actualTheme);
            }
        });
    }

    public static class LoginResultContract extends ActivityResultContract<AppTheme, AppTheme> {

        @NonNull
        @Override
        public Intent createIntent(@NonNull Context context, AppTheme input) {
            Intent intent = new Intent(context, SettingsActivity.class);
            intent.putExtra(SettingsActivity.KEY_THEME_TO_DISPLAY, input);
            return intent;
        }

        @Override
        public AppTheme parseResult(int resultCode, @Nullable Intent intent) {

            if (resultCode == Activity.RESULT_OK && intent != null) {
                return (AppTheme) intent.getSerializableExtra(SettingsActivity.KEY_RESULT);
            }
            return null;
        }
    }

    private void setClickListenerOnButton(int idView, CalcButtonListener calcButtonListener) {
        if (findViewById(idView) != null)
            findViewById(idView).setOnClickListener(calcButtonListener);
    }

    class CalcButtonListener implements View.OnClickListener {

        private Calculator calculator;

        public CalcButtonListener(Calculator calculator) {
            this.calculator = calculator;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.key_bracketStart: {
                    calculator.addBracketStart();
                    break;
                }
                case R.id.key_bracketEnd: {
                    calculator.addBracketEnd();
                    break;
                }
                //region memory
                case R.id.key_MC: {
                    textViewMemory.setVisibility(View.INVISIBLE);
                    calculator.skipMemory();
                    break;
                }
                case R.id.key_MR: {
                    calculator.setValueFromMemory();
                    break;
                }
                case R.id.key_MAsc: {
                    calculator.putInMemory(1);
                    textViewMemory.setVisibility(View.VISIBLE);
                    break;
                }
                case R.id.key_MDesc: {
                    calculator.putInMemory(-1);
                    textViewMemory.setVisibility(View.VISIBLE);
                    break;
                }
                //endregion
                case R.id.key_C: {
                    calculator.processEndRegion();
                    break;

                }
                case R.id.key_CE: {
                    calculator.clearLastOperation();
                    break;
                }
                case R.id.key_delete: {
                    calculator.clearInput();
                    break;
                }
                case R.id.key_equally: {
                    calculator.evaluate();
                    break;
                }
                default: {
                    break;
                }
            }
            textView.setText(calculator.getExpressionString());
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(OBJECT_CALCULATOR, calculator);
    }
}