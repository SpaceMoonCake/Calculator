package com.example.calculator;

import android.text.Editable;
import android.widget.TextView;

import java.io.Serializable;

public class Calculator implements Serializable {
    private Editable calculationString;
    private StringBuilder definitionNumber = new StringBuilder("");
    private int numberFirst;
    private int numberSecond;
    private TextView calculationText;
    private String operation;
    private Integer resultCalculation;
    private TextView resultCalculationView;
    private final String SIGN_PLUS = "+";
    private final String SIGN_MINUS = "-";
    private final String SIGN_MULTIPLY = "*";
    private final String SIGN_DIVISION = "/";
    private final String SIGN_EQUALLY = "=";
    private String errorMassage = "Упс! Что-то пошло не так :(";

    Calculator(TextView calculationText, TextView resultCalculationView) {
        this.calculationText = calculationText;
        this.resultCalculationView = resultCalculationView;
    }

    public TextView getCalculationText(){
        return calculationText;
    }

    public TextView getResultCalculationView(){
        return resultCalculationView;
    }


    public void addSymbolInCalculationText(String symbol) {
        calculationText.append(symbol);
        definitionNumber.append(symbol);
        if (symbol.equals(SIGN_PLUS) || symbol.equals(SIGN_MINUS) || symbol.equals(SIGN_MULTIPLY)
                || symbol.equals(SIGN_DIVISION)) {
            definitionFirstNumber();
            operation = symbol;
        } else if (symbol.equals(SIGN_EQUALLY)) {
            resultCalculationView.setText("");
            definitionSecondNumber();
            try {
                calculation();
                resultCalculationView.append(resultCalculation.toString());
                calculationText.setText("");
            } catch (ArithmeticException exception){
                resultCalculationView.setText(errorMassage);
            }
        }
    }

    public void deleteLastSymbolInCalculationText() {
        calculationString = (Editable) calculationText.getText();
        int calculationStringLength = calculationString.length();
        if (calculationStringLength > 0) {
            calculationString.delete(calculationStringLength - 1, calculationStringLength);
        }
        calculationText.setText(calculationString);
    }

    private void definitionFirstNumber() {
        definitionNumber.delete(definitionNumber.length() - 1, definitionNumber.length());
        numberFirst = Integer.parseInt(definitionNumber.toString());
        definitionNumber.delete(0, definitionNumber.length());
    }

    private void definitionSecondNumber() {
        definitionNumber.delete(definitionNumber.length() - 1, definitionNumber.length());
        numberSecond = Integer.parseInt(definitionNumber.toString());
        definitionNumber.delete(0, definitionNumber.length());
    }

    private void calculation() throws ArithmeticException {
        switch (operation) {
            case (SIGN_PLUS):
                resultCalculation = numberFirst + numberSecond;
                break;
            case (SIGN_MINUS):
                resultCalculation = numberFirst - numberSecond;
                break;
            case (SIGN_MULTIPLY):
                resultCalculation = numberFirst * numberSecond;
                break;
            case (SIGN_DIVISION):
                    resultCalculation = numberFirst / numberSecond;
                break;
        }
    }


}
