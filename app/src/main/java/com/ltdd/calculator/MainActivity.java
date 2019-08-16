package com.ltdd.calculator;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, add, minus, mul, div,equal, clear, per, del, dot;
    TextView txt1, txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CalculatorFragment()).commit();
            setTitle(getString(R.string.menu_calculator));
            navigationView.setCheckedItem(R.id.nav_calculator);
        }

        b1 = findViewById(R.id.btnNum1);
        b2 = findViewById(R.id.btnNum2);
        b3 = findViewById(R.id.btnNum3);
        b4 = findViewById(R.id.btnNum4);
        b5 = findViewById(R.id.btnNum5);
        b6 = findViewById(R.id.btnNum6);
        b7 = findViewById(R.id.btnNum7);
        b8 = findViewById(R.id.btnNum8);
        b9 = findViewById(R.id.btnNum9);
        b0 = findViewById(R.id.btnNum0);
        add = findViewById(R.id.btnPlus);
        minus = findViewById(R.id.btnMinus);
        mul = findViewById(R.id.btnMultiply);
        div = findViewById(R.id.btnDivide);
        equal = findViewById(R.id.btnEqual);
        per = findViewById(R.id.btnPercent);
        del = findViewById(R.id.btnBackspace);
        clear = findViewById(R.id.btnClear);
        dot = findViewById(R.id.btnDot);

        txt1 = findViewById(R.id.txtHistory);
        txt2 = findViewById(R.id.txtResult);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_calculator) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CalculatorFragment()).commit();
            setTitle(getString(R.string.menu_calculator));
        } else if (id == R.id.nav_converter) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ConverterFragment()).commit();
            setTitle(getString(R.string.menu_converter));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
