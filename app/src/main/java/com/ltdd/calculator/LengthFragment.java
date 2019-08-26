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

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

public class LengthFragment extends Fragment {
    private Spinner spUnit;
    private EditText txtInput;
    private TextView txtFeet, txtInches, txtCm, txtM, txtKm, txtMm;
    private Button btConvert, btReset;
    private ArrayList<String> listUnitName;
    double r1, r2, r3, r4, r5, r6;

    public LengthFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_length, container, false);

        Mapping(view);
        FillData();
        CreateBtnFunction();

        return view;
    }
    private void Mapping(@NotNull View view) {
        spUnit = view.findViewById(R.id.spAreaUnit);
        txtInput = view.findViewById(R.id.txtValue);
        txtFeet = view.findViewById(R.id.txtMps);
        txtInches = view.findViewById(R.id.txtmm2);
        txtCm = view.findViewById(R.id.txtKmpHr);
        txtM = view.findViewById(R.id.txtMileperHr);
        txtKm = view.findViewById(R.id.txtInch2);
        txtMm =  view.findViewById(R.id.txtMphr);
        listUnitName = new ArrayList<String>(Arrays.asList("feet", "ich", "mm", "cm", "m", "km"));
        btConvert = view.findViewById(R.id.btConvert);
        btReset = view.findViewById(R.id.btReset);
    }
    private void CreateBtnFunction() {
        btConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Convert();
            }
        });
        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Reset();
            }
        });
    }

    private void Convert() {
        double temp = Double.parseDouble(txtInput.getText().toString());
        String unit =  spUnit.getSelectedItem().toString();
        if (unit == "ich"){
            r1 = (temp * 1); //inches
            r2 = (temp * 0.083); //feet
            r3 = (temp * 25.4); //mm
            r4 = (temp * 2.54); //cm
            r5 = (temp * 0.0254); //m
            r6 = (temp * 0.0000254); //km
        } else if (unit == "feet"){
            r1 = (temp * 12); //inches
            r2 = (temp * 1); //feet
            r3 = (temp * 304.8); //mm
            r4 = (temp * 30.48); //cm
            r5 = (temp * 0.3048); //m
            r6 = (temp * 0.0003048); //km
        }if (unit == "mm"){
            r1 = (temp * 0.0393701); //inches
            r2 = (temp * 0.00328084); //feet
            r3 = (temp * 1); //mm
            r4 = (temp * 0.1); //cm
            r5 = (temp * 0.001); //m
            r6 = (temp * 0.000001); //km
        } else if (unit == "cm") {
            r1 = (temp * 0.393701); //inches
            r2 = (temp * 0.0328084); //feet
            r3 = (temp * 10); //mm
            r4 = (temp * 1); //cm
            r5 = (temp * 0.01); //m
            r6 = (temp * 0.00001); //km
        } else if (unit == "m") {
            r1 = (temp * 39.3700787); //inches
            r2 = (temp * 3.2808399 ); //feet
            r3 = (temp * 10000); //mm
            r4 = (temp * 100); //cm
            r5 = (temp * 1); //m
            r6 = (temp * 0.001); //km
        } else if (unit == "km") {
            r1 = (temp * 39.3701); //inches
            r2 = (temp * 3.28084); //feet
            r3 = (temp * 1000000); //mm
            r4 = (temp * 100000); //cm
            r5 = (temp * 1000); //m
            r6 = (temp * 1); //km
        }
        txtInches.setText(String.valueOf(r1)+" inches");
        txtFeet.setText(String.valueOf(r2)+" feet");
        txtMm.setText(String.valueOf(r3)+" mm");
        txtCm.setText(String.valueOf(r4)+" cm");
        txtM.setText(String.valueOf(r5)+" m");
        txtKm.setText(String.valueOf(r6)+" km");
    }

    private void FillData() {
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, listUnitName);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spUnit.setAdapter(arrayAdapter);
    }
    private void Reset() {
        txtInput.getText().clear();
        txtInches.setText("");
        txtFeet.setText("");
        txtMm.setText("");
        txtCm.setText("");
        txtM.setText("");
        txtKm.setText("");
    }
}
