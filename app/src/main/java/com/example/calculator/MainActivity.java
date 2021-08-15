package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.SyncStateContract;
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

    private final int codeChangeTheme = 99;
    private static final String NameSharedPreference = "SETTINGS";
    private static final String idAppTheme = "ID_THEME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAppTheme();
        setContentView(R.layout.activity_main);
        initView();
    }

    private void setAppTheme() {
            SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
            setTheme(sharedPref.getInt(idAppTheme, R.style.themeDay));
    }

    private void initView() {
        calculationText = findViewById(R.id.calculation_text);
        resultCalculationView = findViewById(R.id.resultCalculation);
        calculator = new Calculator(calculationText, resultCalculationView);
        setNumberButtonListeners();
        findViewById(R.id.settings_button).setOnClickListener(view -> {
            Intent intentForSettingsActivity = new Intent(this, Settings.class);
            startActivityForResult(intentForSettingsActivity, codeChangeTheme);
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_SAVE, calculator);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calculator = (Calculator) savedInstanceState.getParcelable(KEY_SAVE);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode != codeChangeTheme) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }

        if (resultCode == RESULT_OK) {
            assert data != null;
            recreate();
        }
    }
}