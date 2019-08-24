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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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
            setTitle(getString(R.string.menu_cal_standard));
        }else if (id == R.id.nav_scientific) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ConverterFragment()).commit();
            setTitle(getString(R.string.menu_cal_scientific));
        }else if (id == R.id.nav_dev) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ConverterFragment()).commit();
            setTitle(getString(R.string.menu_cal_programmer));
        }else if (id == R.id.nav_currency) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ConverterFragment()).commit();
            setTitle(getString(R.string.menu_cv_currency));
        }else if (id == R.id.nav_temp) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TemperatureFragment()).commit();
            setTitle(getString(R.string.menu_cv_temp));
        }else if (id == R.id.nav_length) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LengthFragment()).commit();
            setTitle(getString(R.string.menu_cv_length));
        }else if (id == R.id.nav_data) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DataFragment()).commit();
            setTitle(getString(R.string.menu_cv_data));
        }else if (id == R.id.nav_speed) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SpeedFragment()).commit();
            setTitle(getString(R.string.menu_cv_speed));
        }else if (id == R.id.nav_time) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TimeFragment()).commit();
            setTitle(getString(R.string.menu_cv_time));
        }else if (id == R.id.nav_area) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AreaFragment()).commit();
            setTitle(getString(R.string.menu_cv_area));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
