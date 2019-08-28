package com.ltdd.calculator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import java.util.Set;

public class SettingActivity extends AppCompatActivity {
    SwitchCompat switchTheme;
    private Button btAbout;
    boolean stateTheme, stateLang;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.darkTheme);
        } else setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        stateTheme = preferences.getBoolean("switchTheme", false);
        switchTheme = findViewById(R.id.switchTheme);
        if ((AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)) {
            switchTheme.setChecked(true);
        }
        switchTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    restartApp();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    restartApp();
                }

            }
            public void restartApp()
            {
                Intent i = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(i);
                finish();
            }
        });
        btAbout = findViewById(R.id.btAbout);

        btAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(SettingActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_about, null);
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.setTitle("Application Info");
                dialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        });
                dialog.show();
            }
        });

    }

}
