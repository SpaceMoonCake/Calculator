package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class Settings extends AppCompatActivity {

    private static final String NameSharedPreference = "SETTINGS";
    private static final String keyAppTheme = "APP_THEME";
    private static final String idAppTheme = "ID_THEME";
    private static final int themeDay = 0;
    private static final int themeNight = 1;
    private final String putExtraTheme = "APP_THEME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getAppTheme(R.style.themeDay));
        setContentView(R.layout.activity_settings);
        initView();
    }

    private void initView() {
        initRadioButton(findViewById(R.id.switchThemeNight), themeNight);
        initRadioButton(findViewById(R.id.switchThemeDay), themeDay);
        findViewById(R.id.button_back_for_calculator).setOnClickListener(view -> {
            Intent intentResult = new Intent();
            SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
            intentResult.putExtra(putExtraTheme, sharedPref.getInt(idAppTheme, R.style.themeDay));
            setResult(RESULT_OK, intentResult);
            finish();
        });
    }

    private void initRadioButton(View button, int codeTheme) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAppTheme(codeTheme);
                recreate();
            }
        });
    }

    private void setAppTheme(int codeTheme) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(keyAppTheme, codeTheme);
        editor.putInt(idAppTheme,codeStyleToStyleId(codeTheme));
        editor.apply();
    }

    private int getAppTheme(int codeTheme) {
        return codeStyleToStyleId(getCodeStyle(codeTheme));
    }

    private int getCodeStyle(int codeTheme){
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        return sharedPref.getInt(keyAppTheme, codeTheme);
    }

    private int codeStyleToStyleId(int codeTheme){
        switch(codeTheme){
            case themeNight:
                return R.style.themeNight;
            default:
                return R.style.themeDay;
        }
    }

}