package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.switchmaterial.SwitchMaterial;

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
    private int NIGHT_THEME = 0;
    private static final String NameSharedPreference = "SETTINGS_THEME";
    private static final String appTheme = "APP_THEME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getAppTheme());
        setContentView(R.layout.activity_main);
        calculationText = findViewById(R.id.calculation_text);
        resultCalculationView = findViewById(R.id.resultCalculation);
        calculator = new Calculator(calculationText, resultCalculationView);
        setNumberButtonListeners();
        initSwitchThemeButton();
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
        for (int i = 0; i < numberButtonIds.length; i++) {
            findViewById(numberButtonIds[i]).setOnClickListener(v -> {
                Button button = (Button) v;
                if (button.equals(findViewById(R.id.button_sign_delete))) {
                    calculator.deleteLastSymbolInCalculationText();
                } else {
                    calculator.addSymbolInCalculationText(button.getText().toString());
                }
            });
        }
    }

    private void initSwitchThemeButton() {
        findViewById(R.id.switchTheme).setOnClickListener(view -> {
            NIGHT_THEME = 1;
            setAppTheme(NIGHT_THEME);
            recreate();
        });
    }

    private int getAppTheme() {
        if (NIGHT_THEME == 1) {
            return R.style.themeNight;
        } else {
            return R.style.themeDay;
        }
    }

    private void setAppTheme(int theme){
            SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(appTheme, theme);
            editor.apply();
    }
}