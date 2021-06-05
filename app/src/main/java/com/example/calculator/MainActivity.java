package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String OBJECT_CALCULATOR = "OBJECT_CALCULATOR";
    private Calculator calculator;
    private TextView textView, textViewMemory;

    private final int[] numberButtonIds = new int[]{R.id.key_0, R.id.key_1, R.id.key_2, R.id.key_3,
            R.id.key_4, R.id.key_5, R.id.key_6, R.id.key_7, R.id.key_8, R.id.key_9};

    private final int[] numberOperationIds = new int[]{R.id.key_addition, R.id.key_subtraction,
            R.id.key_multiplication, R.id.key_division};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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