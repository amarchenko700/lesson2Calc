package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String OBJECT_CALCULATOR = "OBJECT_CALCULATOR";
    private Calculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            TextView textView = findViewById(R.id.expressionString);
            TextView textViewMemory = findViewById(R.id.textMemory);
            calculator = new Calculator(textView, textViewMemory);
        }else {
            calculator = (Calculator) savedInstanceState.getSerializable(OBJECT_CALCULATOR);
            calculator.getTextView().setText(calculator.getExpressionString());
        }

        if(calculator.getMemoryValue() == 0){
            calculator.getTextViewMemory().setVisibility(View.INVISIBLE);
        }else {
            calculator.getTextViewMemory().setVisibility(View.VISIBLE);
        }

        CalcButtonListener calcButtonListener = new CalcButtonListener(calculator);

        findViewById(R.id.key_MC).setOnClickListener(calcButtonListener);
        findViewById(R.id.key_MR).setOnClickListener(calcButtonListener);
        findViewById(R.id.key_MAsc).setOnClickListener(calcButtonListener);
        findViewById(R.id.key_MDesc).setOnClickListener(calcButtonListener);

        findViewById(R.id.key_CE).setOnClickListener(calcButtonListener);
        findViewById(R.id.key_C).setOnClickListener(calcButtonListener);
        findViewById(R.id.key_delete).setOnClickListener(calcButtonListener);

        findViewById(R.id.key_0).setOnClickListener(calcButtonListener);
        findViewById(R.id.key_1).setOnClickListener(calcButtonListener);
        findViewById(R.id.key_2).setOnClickListener(calcButtonListener);
        findViewById(R.id.key_3).setOnClickListener(calcButtonListener);
        findViewById(R.id.key_4).setOnClickListener(calcButtonListener);
        findViewById(R.id.key_5).setOnClickListener(calcButtonListener);
        findViewById(R.id.key_6).setOnClickListener(calcButtonListener);
        findViewById(R.id.key_7).setOnClickListener(calcButtonListener);
        findViewById(R.id.key_8).setOnClickListener(calcButtonListener);
        findViewById(R.id.key_9).setOnClickListener(calcButtonListener);

        findViewById(R.id.key_subtraction).setOnClickListener(calcButtonListener);
        findViewById(R.id.key_addition).setOnClickListener(calcButtonListener);
        findViewById(R.id.key_division).setOnClickListener(calcButtonListener);
        findViewById(R.id.key_multiplication).setOnClickListener(calcButtonListener);

        findViewById(R.id.key_equally).setOnClickListener(calcButtonListener);

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