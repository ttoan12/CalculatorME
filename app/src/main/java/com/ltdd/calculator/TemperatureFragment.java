package com.ltdd.calculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

public class TemperatureFragment extends Fragment {

    private Button btCtoF, btFtoC;
    private TextView txtResult;
    private EditText txtInput;
    double result;

    public TemperatureFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_temperature, container, false);

        Mapping(view);
        CreateBtnFunction();

        return view;
    }

    private void Mapping(@NotNull View view) {
        btCtoF = view.findViewById(R.id.btConvert);
        btFtoC = view.findViewById(R.id.btFtoC);
        txtInput = view.findViewById(R.id.txtLengthValue);
        txtResult = view.findViewById(R.id.txtResult);
    }

    private void CreateBtnFunction() {
        btFtoC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConvertFtoC();
            }
        });
        btCtoF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConvertCtoF();
            }
        });
    }

    private void ConvertCtoF() {
        double temp = Double.parseDouble(txtInput.getText().toString());
        result = (temp * 1.8)/32;
        txtResult.setText(String.valueOf(result));
    }

    private void ConvertFtoC() {
        double temp = Double.parseDouble(txtInput.getText().toString());
        result = (temp - 32)/1.8;
        txtResult.setText(String.valueOf(result));
    }
}
