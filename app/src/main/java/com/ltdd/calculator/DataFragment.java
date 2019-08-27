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

public class DataFragment extends Fragment {

    private Spinner spDataFrom, spDataTo;
    private EditText txtInput;
    private TextView txtResult;
    private Button btConvert;
    private ArrayList<String> listUnit;
    private ArrayList<Double> listValue;
    double result;

    public DataFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data, container, false);
        FloatingActionButton btReset = view.findViewById(R.id.btReset);
        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtInput.getText().clear();
                txtResult.setText("");
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
        spDataFrom = view.findViewById(R.id.spDataFrom);
        spDataTo = view.findViewById(R.id.spDataTo);
        txtInput = view.findViewById(R.id.txtInput);
        txtResult = view.findViewById(R.id.txtResult);
        listUnit = new ArrayList<String>(Arrays.asList("Bits","Bytes(B)", "Kilobytes(KB)", "Megabytes(MB)", "Gigabytes(GB)", "Terabytes(TB)"));
        listValue = new ArrayList<Double>(Arrays.asList(1.0, 0.125, 1.25E-04, 1.25E-07, 1.25E-10, 1.25E-13));
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
    private boolean CheckSpinner() {

        return !spDataFrom.getSelectedItem().toString().equals("") && !spDataTo.getSelectedItem().toString().equals("");
    }

    private void Convert() {
        if (!CheckSpinner()) return;
        String dataFrom = spDataFrom.getSelectedItem().toString();
        String dataTo = spDataTo.getSelectedItem().toString();

        int indexFrom = listUnit.indexOf(dataFrom);
        int indexTo = listUnit.indexOf(dataTo);

        Double rateFrom = listValue.get(indexFrom);
        Double rateTo = listValue.get(indexTo);

        Double currentData = Double.parseDouble(txtInput.getText().toString());
        Double baseData = currentData / rateFrom;
        Double convertedData = baseData * rateTo;

        txtResult.setText(Double.toString(convertedData));
    }

    private void FillData() {
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, listUnit);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDataFrom.setAdapter(arrayAdapter);
        spDataTo.setAdapter(arrayAdapter);

    }


}
