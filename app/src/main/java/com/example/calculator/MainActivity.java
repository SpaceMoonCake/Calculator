package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.radiobutton.MaterialRadioButton;

public class MainActivity extends AppCompatActivity {

    private Calculator calculator;
    private TextView calculationText;
    private TextView resultCalculationView;
    private final static String KEY_SAVE = "Calculation";
    private final int[] numberButtonIds = new int[]{R.id.button_number_null, R.id.button_number_one,
            R.id.button_number_three, R.id.button_number_four, R.id.button_number_five,
            R.id.button_number_six, R.id.button_sign_delete, R.id.button_number_seven,
            R.id.button_number_eight, R.id.button_number_nine, R.id.button_sign_equally,
            R.id.button_sign_minus, R.id.button_sign_plus, R.id.button_sign_division,
            R.id.button_sign_multiply, R.id.button_number_two};

    private static final String NameSharedPreference = "SETTINGS_THEME";
    private static final String appTheme = "APP_THEME";

    private static final int DAY_THEME = 0;
    private static final int NIGHT_THEME = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(codeThemeToThemeID(getCodeTheme()));
        setContentView(R.layout.activity_main);
        initRadioButtons();
        calculationText = findViewById(R.id.calculation_text);
        resultCalculationView = findViewById(R.id.resultCalculation);
        calculator = new Calculator(calculationText, resultCalculationView);
        setNumberButtonListeners();
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

    public void updateData() {
        calculationText.setText(calculator.getCalculationText().getText());
        resultCalculationView.setText(calculator.getResultCalculationView().getText());
    }

    private void setNumberButtonListeners() {
        for (int numberButtonId : numberButtonIds) {
            findViewById(numberButtonId).setOnClickListener(v -> {
                Button button = (Button) v;
                if (button.equals(findViewById(R.id.button_sign_delete))) {
                    calculator.deleteLastSymbolInCalculationText();
                } else {
                    calculator.addSymbolInCalculationText(button.getText().toString());
                }
            });
        }
    }

    private int getCodeTheme() {
        SharedPreferences preferences = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        return preferences.getInt(appTheme, DAY_THEME);
    }

    private void setAppTheme(int codeTheme) {
        SharedPreferences preferences = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        preferences.edit()
                .putInt(appTheme, codeTheme)
                .apply();
    }

    private int codeThemeToThemeID(int codeTheme) {
        switch (codeTheme) {
            case DAY_THEME:
                return R.style.themeDay;
            case NIGHT_THEME:
                return R.style.themeNight;
            default:
                return R.style.Theme_Calculator;
        }
    }

    private void initRadioButtons() {
        findViewById(R.id.switchThemeNight).setOnClickListener(view -> {
            setAppTheme(NIGHT_THEME);
            recreate();
        });
        findViewById(R.id.switchThemeDay).setOnClickListener(view -> {
            setAppTheme(DAY_THEME);
            recreate();
        });
    }
}

