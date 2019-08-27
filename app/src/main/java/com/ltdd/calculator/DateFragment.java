package com.ltdd.calculator;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;


import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFragment extends Fragment {
    private Button btnStart, btnEnd, btnCalculate;
    private TextView txtDateStart, txtDateEnd;
    private TextView txtResult;
    private TextView txtMonthDays;
    private TextView txtWeekDays;
    private TextView txtTotalDays;
    private Calendar calendarStart;
    private Calendar calendarEnd;

    public DateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_date, container, false);

        Mapping(view);
        return view;
    }
    private void Mapping(@NotNull View view) {
        txtDateStart = view.findViewById(R.id.txtDateStart);
        txtDateEnd = view.findViewById(R.id.txtDateEnd);
        txtResult = view.findViewById(R.id.txtResult);
        txtMonthDays = view.findViewById(R.id.txtMonthDays);
        txtWeekDays = view.findViewById(R.id.txtWeekDays);
        txtTotalDays = view.findViewById(R.id.txtTotalDays);
        btnCalculate = view.findViewById(R.id.btnCalculate);
        btnStart = view.findViewById(R.id.btnStart);
        btnEnd = view.findViewById(R.id.btnEnd);
        calendarStart = Calendar.getInstance();
        calendarEnd = Calendar.getInstance();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendarStart = Calendar.getInstance();
                int sYear = calendarStart.get(Calendar.YEAR);
                int sMonth = calendarStart.get(Calendar.MONTH);
                int sDay = calendarStart.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dateDialog = new DatePickerDialog(view.getContext(), datePickerListenerStart, sYear, sMonth, sDay);
                dateDialog.getDatePicker().setMaxDate(new Date().getTime());
                dateDialog.show();
            }
        });

        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendarEnd = Calendar.getInstance();
                int eYear = calendarEnd.get(Calendar.YEAR);
                int eMonth = calendarEnd.get(Calendar.MONTH);
                int eDay = calendarEnd.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dateDialog = new DatePickerDialog(view.getContext(), datePickerListenerEnd, eYear, eMonth, eDay);
                dateDialog.getDatePicker().setMaxDate(new Date().getTime());
                dateDialog.show();
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateCalculation dateCaculator=DateCalculation.calculateAge(calendarStart.get(Calendar.DAY_OF_MONTH),calendarStart.get(Calendar.MONTH)+1,calendarStart.get(Calendar.YEAR),calendarEnd.get(Calendar.DAY_OF_MONTH),calendarEnd.get(Calendar.MONTH)+1,calendarEnd.get(Calendar.YEAR));
                String age = dateCaculator.getYear() + " Years " + dateCaculator.getMonth() + " Months " + dateCaculator.getDay()+ " Days";
                int num_weeks = (int) dateCaculator.getTotalDay()/7;
                int num_months = dateCaculator.getYear()*12 + dateCaculator.getMonth();
                System.out.println(dateCaculator.getYear());
                txtResult.setText(age);
                txtTotalDays.setText(""+dateCaculator.getTotalDay()+" Days");
                txtWeekDays.setText(""+num_weeks+" Weeks " + dateCaculator.getTotalDay()%7 + " Days");
                txtMonthDays.setText(""+num_months+" Months "+dateCaculator.getDay()+" Days ");
            }
        });

    }

    private DatePickerDialog.OnDateSetListener datePickerListenerStart = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, day);
            String format = new SimpleDateFormat("dd MMM YYYY").format(c.getTime());
            txtDateStart.setText(format);
        }
    };
    private DatePickerDialog.OnDateSetListener datePickerListenerEnd = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, day);
            String format = new SimpleDateFormat("dd MMM YYYY").format(c.getTime());
            txtDateEnd.setText(format);
        }
    };


}
