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

public class AreaFragment extends Fragment {
    private Spinner spUnit;
    private EditText txtValue;
    private TextView txtMm2, txtCm2, txtM2, txtHa, txtKm2, txtInch2;
    private Button btConvert;
    private ArrayList<String> listUnitName;
    double r1, r2, r3, r4, r5, r6;


    public AreaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_area, container, false);
        FloatingActionButton btReset = view.findViewById(R.id.btReset);
        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtValue.getText().clear();
                txtMm2.setText("");
                txtCm2.setText("");
                txtM2.setText("");
                txtKm2.setText("");
                txtHa.setText("");
                txtInch2.setText("");
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
        txtMm2 = view.findViewById(R.id.txtMm2);
        txtCm2 = view.findViewById(R.id.txtCm2);
        txtM2 = view.findViewById(R.id.txtM2);
        txtHa = view.findViewById(R.id.txtHa);
        txtKm2 = view.findViewById(R.id.txtKm2);
        txtInch2 =  view.findViewById(R.id.txtInch2);
        listUnitName = new ArrayList<String>(Arrays.asList("mm²", "cm²", "m²", "ha", "km²", "inch²"));
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
        String unit =  spUnit.getSelectedItem().toString();
        if (unit == "mm²"){
            r1 = (temp * 1); //mm²
            r2 = (temp * 0.01); //cm²
            r3 = (temp * 0.000001); //m²
            r4 = (temp * 0.0000000001); //ha
            r5 = (temp * 0.000000000001); //km²
            r6 = (temp * 0.00155); //inch²
        } else if (unit == "cm²"){
            r1 = (temp * 100); //mm²
            r2 = (temp * 1); //cm²
            r3 = (temp * 0.0001); //m²
            r4 = (temp * 0.00000001); //ha
            r5 = (temp * 0.0000000001); //km²
            r6 = (temp * 0.00155); //inch²
        }if (unit == "m²"){
            r1 = (temp * 1000000); //mm²
            r2 = (temp * 1000); //cm²
            r3 = (temp * 1); //m²
            r4 = (temp * 0.0001); //ha
            r5 = (temp * 0.000001); //km²
            r6 = (temp * 1550.003); //inch²
        } else if (unit == "ha") {
            r1 = (temp * 10000 * 10000 ); //mm²
            r2 = (temp * 10000000); //cm²
            r3 = (temp * 10000); //m²
            r4 = (temp * 1); //ha
            r5 = (temp * 0.01); //km²
            r6 = (temp * 15500031); //inch²
        } else if (unit == "km²") {
            r1 = (temp * 1000000 * 1000000 ); //mm²
            r2 = (temp * 100000 * 100000 ); //cm²
            r3 = (temp * 1000000); //m²
            r4 = (temp * 100); //ha
            r5 = (temp * 1); //km²
            r6 = (temp * 1550003100); //inch²
        } else if (unit == "inch²") {
            r1 = (temp * 645); //mm²
            r2 = (temp * 6.45 ); //cm²
            r3 = (temp * 0.000645); //m²
            r4 = (temp * 0.0000000645); //ha
            r5 = (temp * 0.000000000645); //km²
            r6 = (temp * 1); //inch²
        }
        txtMm2.setText(String.valueOf(r1)+" mm²");
        txtCm2.setText(String.valueOf(r2)+" cm²");
        txtM2.setText(String.valueOf(r3)+" m²");
        txtHa.setText(String.valueOf(r4)+" ha");
        txtKm2.setText(String.valueOf(r5)+" km²");
        txtInch2.setText(String.valueOf(r6)+" inch²");
    }

    private void FillData() {
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, listUnitName);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spUnit.setAdapter(arrayAdapter);
    }
}
