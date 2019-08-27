package com.ltdd.calculator;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

public class SettingActivity extends AppCompatActivity {
    SwitchCompat switchTheme;
    Button btnLang;
    private Dialog dialog;
    boolean stateTheme, stateLang;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        stateTheme = preferences.getBoolean("switchTheme", false);
        stateLang = preferences.getBoolean("switchLang", false);
        switchTheme = findViewById(R.id.switchTheme);
        btnLang = findViewById(R.id.btnLang);
        btnLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(SettingActivity.this);
                dialog.setTitle("Change language");
                dialog.setContentView(R.layout.dialoglanguage);
                dialog.show();
            }
        });

        switchTheme.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                stateTheme = !stateTheme;
                switchTheme.setChecked(stateTheme);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("switchTheme", stateTheme);
                editor.apply();
            }
        });
        btnLang.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ;
            }
        });

    }

}
