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

public class BMIFragment extends Fragment {
    private Spinner spGender;
    private EditText txtHeight, txtWeight;
    private TextView txtIndexResult, txtResult;
    private TextView txtBMRIIndexResult, txtBRMResult;
    private Button btConvert;
    private ArrayList<String> listGender;
    double resultBMI;
    double resultBRM;

    public BMIFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bmi, container, false);
        FloatingActionButton btReset = view.findViewById(R.id.btReset);
        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtHeight.getText().clear();
                txtWeight.getText().clear();
                txtResult.setText("");
                txtIndexResult.setText("");
                Snackbar.make(view, "Clear all your results", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Mapping(view);
        CreateBtnFunction();

        return view;
    }
    private void Mapping(@NotNull View view) {
        txtHeight = view.findViewById(R.id.txtHeight);
        txtWeight = view.findViewById(R.id.txtWeight);
        txtIndexResult = view.findViewById(R.id.txtIndexResult);
        txtBMRIIndexResult = view.findViewById(R.id.txtBMRIIndexResult);
        txtResult = view.findViewById(R.id.txtResult);
        btConvert = view.findViewById(R.id.btConvert);
        listGender = new ArrayList<String>(Arrays.asList("Male", "Female"));

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
        double height = Double.parseDouble(txtHeight.getText().toString());
        double weight = Double.parseDouble(txtWeight.getText().toString());
        resultBMI = (weight/(height*height))*10000;
        resultBRM = ((9.99 * weight)+(6.25 * height)-(4.92)+5)/1000;
        if (resultBMI <= 18.5){
            txtIndexResult.setText(String.valueOf("Your BMI is: " + resultBMI));
            txtResult.setText(String.valueOf("underweight"));
            txtBMRIIndexResult.setText(String.valueOf("The number of calories you need each day: " + resultBRM));
        } else if (resultBMI > 18.5 && resultBMI < 24.9){
            txtIndexResult.setText(String.valueOf("Your BMI is: " + resultBMI));
            txtResult.setText(String.valueOf("nomal weight"));
            txtBMRIIndexResult.setText(String.valueOf("The number of calories you need each day: " + resultBRM));
        }else if (resultBMI > 25 && resultBMI < 29.9) {
            txtIndexResult.setText(String.valueOf("Your BMI is: " + resultBMI));
            txtResult.setText(String.valueOf("over weight"));
            txtBMRIIndexResult.setText(String.valueOf("The number of calories you need each day: " + resultBRM));
        }else if (resultBMI >= 30) {
            txtIndexResult.setText(String.valueOf("Your BMI is: " + resultBMI));
            txtResult.setText(String.valueOf("Obese"));
            txtBMRIIndexResult.setText(String.valueOf("The number of calories you need each day: " + resultBRM));
        }
    }

}
