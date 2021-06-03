package com.example.calculator;

import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

import MathOperations.*;

public class Calculator implements Serializable {

    private String expressionString;

    private TextView textView, textViewMemory;
    private MathGB mgb;

    private Float memoryValue = 0f;

    public Calculator(TextView tv, TextView tvm) {
        this.textView = tv;
        this.textViewMemory = tvm;
        mgb = new MathGB();
        expressionString = "";
    }

    public void inputExpression(View v) {
CharSequence ch = textView.getText();
        switch (v.getId()) {
            //region numbers_Handlers
            case R.id.key_0: {
                expressionString = expressionString + "0";
                break;
            }
            case R.id.key_1: {
                expressionString = expressionString + "1";
                break;
            }
            case R.id.key_2: {
                expressionString = expressionString + "2";
                break;
            }
            case R.id.key_3: {
                expressionString = expressionString + "3";
                break;
            }
            case R.id.key_4: {
                expressionString = expressionString + "4";
                break;
            }
            case R.id.key_5: {
                expressionString = expressionString + "5";
                break;
            }
            case R.id.key_6: {
                expressionString = expressionString + "6";
                break;
            }
            case R.id.key_7: {
                expressionString = expressionString + "7";
                break;
            }
            case R.id.key_8: {
                expressionString = expressionString + "8";
                break;
            }
            case R.id.key_9: {
                expressionString = expressionString + "9";
                break;
            }
            //endregion
            //region functions
            case R.id.key_addition: {
                addOperations("+");
                break;
            }
            case R.id.key_subtraction: {
                addOperations("-");
                break;
            }
            case R.id.key_multiplication: {
                addOperations("*");
                break;
            }
            case R.id.key_division: {
                addOperations("/");
                break;
            }
            //endregion
            //region memory
            case R.id.key_MC: {
                textViewMemory.setVisibility(View.INVISIBLE);
                memoryValue = 0f;
                break;
            }
            case R.id.key_MR: {
                expressionString = Float.toString(memoryValue);
                break;
            }
            case R.id.key_MAsc: {
                putInMemory(getLastNumberFloat(), 1);
                break;
            }
            case R.id.key_MDesc: {
                putInMemory(getLastNumberFloat(), -1);
                break;
            }
            //endregion
            case R.id.key_C: {
                expressionString = "";
                break;

            }
            case R.id.key_CE: {
                char[] expCharArr = expressionString.toCharArray();
                int indexOperation;
                for (indexOperation = expCharArr.length - 1; indexOperation >= 0; indexOperation--) {
                    char currentChar = expCharArr[indexOperation];
                    if (!mgb.charIsNumber(currentChar)) {
                        expressionString = String.copyValueOf(expCharArr, 0, indexOperation + 1);
                        break;
                    }
                    if(indexOperation == 0) expressionString = "";
                }
                break;
            }
            case R.id.key_delete: {
                char[] expCharArr = expressionString.toCharArray();
                if (expCharArr.length > 0)
                    expressionString = String.copyValueOf(expCharArr, 0, expCharArr.length - 1);
                break;
            }
            case R.id.key_equally: {
                mgb.setExpression(expressionString);
                Float result = mgb.evaluate();
                expressionString = Float.toString(result);
                break;
            }
            default: {
                break;
            }

        }

        textView.setText(expressionString);
    }

    private void addOperations(String operations) {
        char[] expCharArr = expressionString.toCharArray();
        char lastChar = expCharArr[expCharArr.length - 1];
        if (mgb.charIsNumber(lastChar) || mgb.charIsBracket(lastChar))
            expressionString = expressionString + operations;
    }

    private String getLastNumberString(){
        String lastNumber = "";
        char[] expCharArr = expressionString.toCharArray();
        int indexOperation;
        for (indexOperation = expCharArr.length - 1; indexOperation >= 0; indexOperation--) {
            char currentChar = expCharArr[indexOperation];
            if (mgb.charIsNumber(currentChar)) {
                lastNumber = currentChar + lastNumber;
            }else {
                break;
            }
        }
        return lastNumber;
    }

    private Float getLastNumberFloat(){
        Float lastNumber = 0f;
        String lastNumberString = getLastNumberString();
        lastNumber = Float.parseFloat(lastNumberString);
        return lastNumber;
    }

    private void putInMemory(Float value, int koef){
        memoryValue = memoryValue + value * koef;
        textViewMemory.setVisibility(View.VISIBLE);
    }

    public Float getMemoryValue() {
        return memoryValue;
    }

    public String getExpressionString() {
        return expressionString;
    }

    public TextView getTextView() {
        return textView;
    }

    public TextView getTextViewMemory() {
        return textViewMemory;
    }
}

