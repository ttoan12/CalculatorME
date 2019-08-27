package com.ltdd.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

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
            setTitle(getString(R.string.menu_cal_standard) + " " + getString(R.string.menu_calculator));
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
            setTitle(getString(R.string.menu_cal_standard) + " " + getString(R.string.menu_calculator));
        } else if (id == R.id.nav_advanced_calculator) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CalculatorScientificFragment()).commit();
            setTitle(getString(R.string.menu_cal_advanced) + " " + getString(R.string.menu_calculator));
        } else if (id == R.id.nav_date_calculator) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DateFragment()).commit();
                setTitle(getString(R.string.menu_cal_date));
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intSetting = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intSetting);
            return false;
        }

        return super.onOptionsItemSelected(item);


    }
}
