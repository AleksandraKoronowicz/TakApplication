package com.application.tak.takapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.application.tak.takapplication.data_access.GetCategories;
import com.application.tak.takapplication.data_model.Category;
import com.application.tak.takapplication.interfaces.OnDBRequestFinished;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * A placeholder fragment containing a simple view.
 */
public class actClientTaskAddFragment extends Fragment {


    DateFormat formDatetime = DateFormat.getDateTimeInstance();
    Calendar dateTime = Calendar.getInstance();
    Calendar time = Calendar.getInstance();
    private ImageButton btn_date;
    private ImageButton btn_time;
    private TextView dateText;
    private TextView timeText;
    Spinner spinner;
    DateFormat dateFormat = new SimpleDateFormat("hh:mm");
    List<Category> categoriesList;
    GetCategories categories;
    ArrayAdapter<String> adapter;
    Context ctx;
    String[] awayStrings;
    public actClientTaskAddFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

ctx = this.getContext();

        View view = inflater.inflate(R.layout.act_client_task_new2, container, false);
         spinner = (Spinner) view.findViewById(R.id.spinner2);
        // Inflate the layout for this fragment
        categories = new GetCategories(this.getContext());
        categories.setDBRequestFinishedListener(new OnDBRequestFinished() {
            @Override
            public void onDBRequestFinished() {
                categoriesList = categories._categories;
                if(categories._categories != null)
                {
                 awayStrings = new String[categoriesList.size()];
                int i= 0;
                for(Category c: categoriesList)
                {
awayStrings[i] = c.get_CategoryName();
        i++;

                }


                /*String[] awayStrings = {
                        "Wyrzuć śmieci",
                        "Zrób zakupy",
                        "Umyj okna",
                        "Wyprowadź psa",
                        "Prace w ogrodzie"

                };*/


                adapter = new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_item, awayStrings);
                adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spinner.setAdapter(adapter);}
            }});


        //ArrayAdapter<String>



        timeText = (TextView) view.findViewById(R.id.timepicker);
        dateText = (TextView) view.findViewById(R.id.datepicker);
        btn_time = (ImageButton) view.findViewById(R.id.btnImageClock);
        btn_time.setOnClickListener(new View.OnClickListener(){@Override public void onClick(View v) {updateTime();}});
        btn_date = (ImageButton) view.findViewById(R.id.imageButton2);
        btn_date.setOnClickListener(new View.OnClickListener()
        {@Override
            public void onClick(View v) {
                updateDate();
            }
        });

        return view;

    }
    private void updateTextLabel()
    {
        String shortDate = DateFormat.getDateInstance().format(dateTime.getTime()).toString();
        dateText.setText(shortDate);
    }
    private void updateTextTimeLabel()
    {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String test = sdf.format(time.getTime());
        timeText.setText(test);

    }

    private void updateTime()
    {
        new TimePickerDialog(this.getActivity(),t,  time.get(Calendar.HOUR),  time.get(Calendar.MINUTE),true).show();
    }

    private void updateDate()
    {
        new DatePickerDialog(this.getActivity(), d, dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH), dateTime.get(Calendar.DAY_OF_MONTH)).show();

    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            dateTime.set(Calendar.YEAR, year);
            dateTime.set(Calendar.MONTH, month);
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            updateTextLabel();
        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            time.set(Calendar.HOUR, hourOfDay);
            time.set(Calendar.MINUTE, minute);

            updateTextTimeLabel();
        }
    };
}
