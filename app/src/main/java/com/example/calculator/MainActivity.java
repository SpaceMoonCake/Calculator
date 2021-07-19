package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Calculator calculator;
    private TextView calculationText;
    private final String NUMBER_ONE = "1";
    private final String NUMBER_TWO = "2";
    private final String NUMBER_THREE = "3";
    private final String NUMBER_FOUR = "4";
    private final String NUMBER_FIVE = "5";
    private final String NUMBER_SIX = "6";
    private final String NUMBER_SEVEN = "7";
    private final String NUMBER_EIGHT = "8";
    private final String NUMBER_NINE = "9";
    private final String NUMBER_NULL = "0";
    private final String SIGN_PLUS = "+";
    private final String SIGN_MINUS = "-";
    private final String SIGN_MULTIPLY = "*";
    private final String SIGN_DIVISION = "/";
    private final String SIGN_EQUALLY = "=";
    private TextView resultCalculationView;
    private final static String KEY_SAVE = "Calculation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculationText = findViewById(R.id.calculation_text);
        resultCalculationView = findViewById(R.id.resultCalculation);
        calculator = new Calculator(calculationText, resultCalculationView);
        buttonListener();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_SAVE, calculator);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calculator = (Calculator) savedInstanceState.getSerializable(KEY_SAVE);
        updateData();
    }

    public void updateData(){
        calculationText.setText(calculator.getCalculationText().getText());
        resultCalculationView.setText(calculator.getResultCalculationView().getText());
    }

    public void buttonListener() {
        findViewById(R.id.button_number_null).setOnClickListener(v -> {
            calculator.addSymbolInCalculationText(NUMBER_NULL);
        });

        findViewById(R.id.button_number_one).setOnClickListener(v -> {
            calculator.addSymbolInCalculationText(NUMBER_ONE);
        });

        findViewById(R.id.button_number_two).setOnClickListener(v -> {
            calculator.addSymbolInCalculationText(NUMBER_TWO);
        });

        findViewById(R.id.button_number_three).setOnClickListener(v -> {
            calculator.addSymbolInCalculationText(NUMBER_THREE);
        });

        findViewById(R.id.button_number_four).setOnClickListener(v -> {
            calculator.addSymbolInCalculationText(NUMBER_FOUR);
        });

        findViewById(R.id.button_number_five).setOnClickListener(v -> {
            calculator.addSymbolInCalculationText(NUMBER_FIVE);
        });

        findViewById(R.id.button_number_six).setOnClickListener(v -> {
            calculator.addSymbolInCalculationText(NUMBER_SIX);
        });

        findViewById(R.id.button_number_seven).setOnClickListener(v -> {
            calculator.addSymbolInCalculationText(NUMBER_SEVEN);
        });

        findViewById(R.id.button_number_eight).setOnClickListener(v -> {
            calculator.addSymbolInCalculationText(NUMBER_EIGHT);
        });

        findViewById(R.id.button_number_nine).setOnClickListener(v -> {
            calculator.addSymbolInCalculationText(NUMBER_NINE);
        });

        findViewById(R.id.button_sign_division).setOnClickListener(v -> {
            calculator.addSymbolInCalculationText(SIGN_DIVISION);
        });

        findViewById(R.id.button_sign_multiply).setOnClickListener(v -> {
            calculator.addSymbolInCalculationText(SIGN_MULTIPLY);
        });

        findViewById(R.id.button_sign_minus).setOnClickListener(v -> {
            calculator.addSymbolInCalculationText(SIGN_MINUS);
        });

        findViewById(R.id.button_sign_plus).setOnClickListener(v -> {
            calculator.addSymbolInCalculationText(SIGN_PLUS);
        });

        findViewById(R.id.button_sign_equally).setOnClickListener(v -> {
            calculator.addSymbolInCalculationText(SIGN_EQUALLY);
        });

        findViewById(R.id.button_sign_delete).setOnClickListener(v -> {
            calculator.deleteLastSymbolInCalculationText();
        });

    }

}