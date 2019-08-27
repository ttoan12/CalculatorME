package com.ltdd.calculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

public class SpeedFragment extends Fragment {
    private Spinner spUnit;
    private EditText txtValue;
    private TextView txtCmps, txtMps, txtMphr, txtKmpHr, txtMileperHr, txtFtpS;
    private Button btConvert;
    private ArrayList<String> listUnitName;
    double r1, r2, r3, r4, r5, r6;

    public SpeedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_speed, container, false);
        FloatingActionButton btReset = view.findViewById(R.id.btReset);
        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtValue.getText().clear();
                txtCmps.setText("");
                txtMps.setText("");
                txtMphr.setText("");
                txtKmpHr.setText("");
                txtMileperHr.setText("");
                txtFtpS.setText("");
                Snackbar.make(view, "Clear all your results", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Mapping(view);
        FillData();
        CreateBtnFunction();

        return view;
    }

    private void Mapping(@NotNull View view) {
        spUnit = view.findViewById(R.id.spUnit);
        txtValue = view.findViewById(R.id.txtValue);
        txtCmps = view.findViewById(R.id.txtCmps);
        txtMps = view.findViewById(R.id.txtMps);
        txtMphr = view.findViewById(R.id.txtMphr);
        txtKmpHr = view.findViewById(R.id.txtKmpHr);
        txtMileperHr = view.findViewById(R.id.txtMileperHr);
        txtFtpS = view.findViewById(R.id.txtFtpS);
        listUnitName = new ArrayList<String>(Arrays.asList("m/s", "m/h", "km/s", "km/h", "mph", "c"));
        btConvert = view.findViewById(R.id.btConvert);
    }

    private void CreateBtnFunction() {
        btConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Convert();
            }
        });
    }
    private void Convert() {
        double temp = Double.parseDouble(txtValue.getText().toString());
        String unit = spUnit.getSelectedItem().toString();
        if (unit == "m/s") {
            r1 = (temp * 1); //inches
            r2 = (temp * 0.083); //feet
            r3 = (temp * 25.4); //mm
            r4 = (temp * 2.54); //cm
            r5 = (temp * 0.0254); //m
            r6 = (temp * 0.0000254); //km
        } else if (unit == "m/h") {
            r1 = (temp * 12); //inches
            r2 = (temp * 1); //feet
            r3 = (temp * 304.8); //mm
            r4 = (temp * 30.48); //cm
            r5 = (temp * 0.3048); //m
            r6 = (temp * 0.0003048); //km
        }
        if (unit == "km/s") {
            r1 = (temp * 0.03937); //inches
            r2 = (temp * 0.00328); //feet
            r3 = (temp * 1); //mm
            r4 = (temp * 0.1); //cm
            r5 = (temp * 0.001); //m
            r6 = (temp * 0.000001); //km
        } else if (unit == "km/h") {
            r1 = (temp * 0.3937); //inches
            r2 = (temp * 0.0328); //feet
            r3 = (temp * 10); //mm
            r4 = (temp * 1); //cm
            r5 = (temp * 0.01); //m
            r6 = (temp * 0.00001); //km
        } else if (unit == "mph") {
            r1 = (temp * 39.37); //inches
            r2 = (temp * 3.28); //feet
            r3 = (temp * 10000); //mm
            r4 = (temp * 100); //cm
            r5 = (temp * 1); //m
            r6 = (temp * 0.001); //km
        } else if (unit == "c") {
            r1 = (temp * 39.37); //inches
            r2 = (temp * 3.28); //feet
            r3 = (temp * 1000000); //mm
            r4 = (temp * 100000); //cm
            r5 = (temp * 1000); //m
            r6 = (temp * 1); //km

            txtCmps.setText(String.valueOf(r1) + " cm/s");
            txtMps.setText(String.valueOf(r2) + " m/s");
            txtMphr.setText(String.valueOf(r3) + " m/h");
            txtKmpHr.setText(String.valueOf(r4) + " km/h");
            txtMileperHr.setText(String.valueOf(r5) + " mph");
            txtFtpS.setText(String.valueOf(r6) + " ft/s");
        }
    }
    private void FillData() {
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, listUnitName);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spUnit.setAdapter(arrayAdapter);
    }
}