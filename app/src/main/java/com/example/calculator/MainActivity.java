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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            TextView textView = findViewById(R.id.expressionString);
            TextView textViewMemory = findViewById(R.id.textMemory);
            calculator = new Calculator(textView, textViewMemory);
        } else {
            calculator = (Calculator) savedInstanceState.getSerializable(OBJECT_CALCULATOR);
            calculator.getTextView().setText(calculator.getExpressionString());
        }

        if (calculator.getMemoryValue() == 0) {
            calculator.getTextViewMemory().setVisibility(View.INVISIBLE);
        } else {
            calculator.getTextViewMemory().setVisibility(View.VISIBLE);
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

        setClickListenerOnButton(R.id.key_0, calcButtonListener);
        setClickListenerOnButton(R.id.key_1, calcButtonListener);
        setClickListenerOnButton(R.id.key_2, calcButtonListener);
        setClickListenerOnButton(R.id.key_3, calcButtonListener);
        setClickListenerOnButton(R.id.key_4, calcButtonListener);
        setClickListenerOnButton(R.id.key_5, calcButtonListener);
        setClickListenerOnButton(R.id.key_6, calcButtonListener);
        setClickListenerOnButton(R.id.key_7, calcButtonListener);
        setClickListenerOnButton(R.id.key_8, calcButtonListener);
        setClickListenerOnButton(R.id.key_9, calcButtonListener);

        setClickListenerOnButton(R.id.key_subtraction, calcButtonListener);
        setClickListenerOnButton(R.id.key_addition, calcButtonListener);
        setClickListenerOnButton(R.id.key_division, calcButtonListener);
        setClickListenerOnButton(R.id.key_multiplication, calcButtonListener);

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
            calculator.inputExpression(v);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(OBJECT_CALCULATOR, calculator);
    }
}