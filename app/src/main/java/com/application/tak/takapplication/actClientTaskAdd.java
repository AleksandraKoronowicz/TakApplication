package com.application.tak.takapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.*;
import com.application.tak.takapplication.data_model.Category;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class actClientTaskAdd extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    DateFormat formDatetime = DateFormat.getDateTimeInstance();
    Calendar dateTime = Calendar.getInstance();
    private ImageButton btn_date;
    private ImageButton btn_time;
    private TextView dateText;
    private TextView timeText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_client_task_new2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        timeText = (TextView) findViewById(R.id.timepicker);
        dateText = (TextView) findViewById(R.id.datepicker);

         btn_date = (ImageButton) findViewById(R.id.imageButton2);
        btn_date.setOnClickListener(new View.OnClickListener(){@Override public void onClick(View v) {updateDate();}});

        btn_time = (ImageButton) findViewById(R.id.btnImageClock);
        btn_time.setOnClickListener(new View.OnClickListener(){@Override public void onClick(View v) {updateTime();}});

        updateTextLabel();


    }

    private void updateTime()
    {
        new TimePickerDialog(this,t,  dateTime.get(Calendar.HOUR),  dateTime.get(Calendar.MINUTE),true).show();
    }

    private void updateDate()
    {
        new DatePickerDialog(this, d, dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH), dateTime.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateTextLabel()
    {
        dateText.setText(formDatetime.format(dateTime.getTime()));
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        }

    };
TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

    }
};
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "YOUR SELECTION IS : " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
