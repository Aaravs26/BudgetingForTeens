package com.example.budgetingforteens;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Calendar;

public class misc extends AppCompatActivity implements View.OnClickListener {
    SeekBar sb;
    TextView tv1;
    ImageButton add;
    Button submit, view_act, pick_date;
    EditText activity, expenditure, date_et;
    private int mYear, mMonth, mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misc);
        sb = findViewById(R.id.sb);
        tv1 = findViewById(R.id.tv1);
        add = findViewById(R.id.add);
        submit = findViewById(R.id.submit);
        view_act = findViewById(R.id.view_act);
        pick_date = findViewById(R.id.pick_date);
        activity = findViewById(R.id.activity);
        expenditure = findViewById(R.id.expenditure);
        date_et = findViewById(R.id.date_et);
        submit.setOnClickListener(this);
        view_act.setOnClickListener(this);
        add.setOnClickListener(this);
        pick_date.setOnClickListener(this);
        pick_date.setVisibility(View.VISIBLE);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                double price = 0;
                int price1 = 0;
                price = i * 1;
//                price = Integer.parseInt(price);
                price1 = (int) price;
                tv1.setText("₹" + price1);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == view_act) {
            Intent i = new Intent(misc.this, misc_activity.class);
            startActivity(i);
        }
        if (view == pick_date) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    date_et.setText(day + "-" + (month+1) + "-" + year);
                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (view == pick_date) {
            pick_date.setVisibility(View.GONE);
        }
    }
}