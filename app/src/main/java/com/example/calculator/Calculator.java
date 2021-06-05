package com.example.calculator;

import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

import MathOperations.*;

public class Calculator implements Serializable {

    private String expressionString;
    private MathGB mgb;
    private Integer countStartBracket;
    private Float memoryValue = 0f;

    public Calculator() {
        mgb = new MathGB();
        expressionString = "";
        countStartBracket = 0;
    }

    public void addNumber(String operation) {
        expressionString += operation;
    }

    public void addOperations(String operations) {
        char lastChar = getLastChar();
        if (mgb.charIsNumber(lastChar) || mgb.charIsBracketEnd(lastChar))
            expressionString = expressionString + operations;
    }

    public void addBracketStart() {
        char lastChar = getLastChar();
        if (mgb.charIsBracketStart(lastChar) || mgb.charIsFunction(lastChar))
            countStartBracket++;
        addNumber("(");
    }

    public void addBracketEnd() {
        if (countStartBracket > 0) {
            addNumber(")");
            countStartBracket--;
        }
    }

    public void skipMemory() {
        memoryValue = 0f;
    }

    public void setValueFromMemory() {
        expressionString = Float.toString(memoryValue);
    }

    public void clearInput() {
        char[] expCharArr = expressionString.toCharArray();
        if (expCharArr.length > 0) {
            expressionString = String.copyValueOf(expCharArr, 0, expCharArr.length - 1);
            if (mgb.charIsBracketStart(getLastChar())) countStartBracket--;
        }
    }

    public void clearLastOperation() {
        char[] expCharArr = expressionString.toCharArray();
        int indexOperation;
        for (indexOperation = expCharArr.length - 1; indexOperation >= 0; indexOperation--) {
            char currentChar = expCharArr[indexOperation];
            if (!mgb.charIsNumber(currentChar)) {
                expressionString = String.copyValueOf(expCharArr, 0, indexOperation + 1);
                break;
            }
            if (indexOperation == 0) expressionString = ")";
        }
    }

    public void evaluate() {
        mgb.setExpression(expressionString);
        Float result = mgb.evaluate();
        expressionString = Float.toString(result);
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

    private char getLastChar(){
        char[] expCharArr = expressionString.toCharArray();
        char lastChar = expCharArr[expCharArr.length - 1];
        return lastChar;
    }

    private Float getLastNumberFloat(){
        Float lastNumber = 0f;
        String lastNumberString = getLastNumberString();
        lastNumber = Float.parseFloat(lastNumberString);
        return lastNumber;
    }

    public void putInMemory(int koef){
        memoryValue = memoryValue + getLastNumberFloat() * koef;
    }

    public void processEndRegion() {
        expressionString = ")";
        countStartBracket = 0;
    }

    public Float getMemoryValue() {
        return memoryValue;
    }

    public String getExpressionString() {
        return expressionString;
    }

}

