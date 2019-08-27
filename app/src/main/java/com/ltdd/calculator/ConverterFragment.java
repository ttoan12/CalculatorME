package com.ltdd.calculator;

import android.R.layout;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ConverterFragment extends Fragment {

    private Spinner spnFrom, spnTo;
    private EditText txtFrom;
    private TextView txtTo;
    private Button btnConvert, btnCopy;
    private ArrayList<String> listCurrencyName;
    private ArrayList<Double> listCurrencyRate;
    private String apiUrl;

    public ConverterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_converter, container, false);
        Mapping(view);
        new GetData().execute();
        FillData();
        CreateBtnFunction();

        return view;
    }

    private void Mapping(@NotNull View view) {
        spnFrom = view.findViewById(R.id.spCurrencyFrom);
        spnTo = view.findViewById(R.id.spCurrencyTo);
        txtFrom = view.findViewById(R.id.txtFrom);
        txtTo = view.findViewById(R.id.txtTo);
        btnConvert = view.findViewById(R.id.btnConvert);
        btnCopy = view.findViewById(R.id.btnCopy);
        apiUrl = "https://api.exchangerate-api.com/v4/latest/USD";
        listCurrencyName = new ArrayList<String>(Arrays.asList("USD", "AUD", "CAD", "CNY", "EUR", "GBP", "JPY", "KRW", "RUB", "VND"));
        listCurrencyRate = new ArrayList<Double>(Arrays.asList(1.0, 1.47515, 1.329804, 7.040277, 0.901312, 0.823721, 106.247418, 1209.264968, 66.203863, 23308.77193));
    }



    private void CreateBtnFunction() {
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Convert();
            }
        });

        btnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CopyResult();
            }
        });
    }

    private void Convert() {
        if (!CheckSpinner()) return;

        String currencyFrom = spnFrom.getSelectedItem().toString();
        String currencyTo = spnTo.getSelectedItem().toString();

        int indexFrom = listCurrencyName.indexOf(currencyFrom);
        int indexTo = listCurrencyName.indexOf(currencyTo);

        Double rateFrom = listCurrencyRate.get(indexFrom);
        Double rateTo = listCurrencyRate.get(indexTo);

        Double currentCurrency = Double.parseDouble(txtFrom.getText().toString());
        Double baseCurrency = currentCurrency / rateFrom;
        Double convertedCurrency = baseCurrency * rateTo;

        txtTo.setText(Double.toString(convertedCurrency));
        btnCopy.setEnabled(true);
    }

    private void CopyResult() {
        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Result", txtTo.getText().toString());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(getContext(), "Result Copied!", Toast.LENGTH_SHORT).show();
    }

    private boolean CheckSpinner() {
        return !spnFrom.getSelectedItem().toString().equals("") && !spnTo.getSelectedItem().toString().equals("");
    }

    private void FillData() {
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), layout.simple_spinner_item, listCurrencyName);
        arrayAdapter.setDropDownViewResource(layout.simple_spinner_dropdown_item);
        spnFrom.setAdapter(arrayAdapter);
        spnTo.setAdapter(arrayAdapter);
    }

    private class GetData extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] objects) {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(apiUrl)
                    .build();

            Response response = null;

            try {
                response = client.newCall(request).execute();
                return response.body().string();

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Đã xảy ra lỗi khi cập nhật tỷ giá mới nhất.", Toast.LENGTH_SHORT).show();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {

            try {
                JSONObject jsonObject = new JSONObject(o.toString());
                JSONObject rates = jsonObject.getJSONObject("rates");

                for (int i = 0; i < listCurrencyName.size(); i++) {
                    String name = listCurrencyName.get(i);
                    Double rate = rates.getDouble(name);
                    listCurrencyRate.set(i, rate);
                }

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Đã xảy ra lỗi khi cập nhật tỷ giá mới nhất.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
