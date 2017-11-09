package com.application.tak.takapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringDef;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;

import com.application.tak.takapplication.data_access.CreateTask;
import com.application.tak.takapplication.data_access.GetCategories;
import com.application.tak.takapplication.data_model.Category;
import com.application.tak.takapplication.data_model.Task;
import com.application.tak.takapplication.data_model.User;
import com.application.tak.takapplication.interfaces.OnDBRequestFinished;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class actClientAddNewTask extends Fragment {

    private Button btn_date;
    private Button btn_time;
    private  TimePicker tp;
    private ImageView imgtimer;
    private ImageView imgdata;
    private TextView tv;
    private TextView tv_data;
    private FloatingActionButton done;
    private TextView question;
    public String selectedItem;

    DateFormat formDatetime = DateFormat.getDateTimeInstance();
    Calendar dateTime = Calendar.getInstance();
    Calendar time = Calendar.getInstance();
    private TextView dateText;
    private TextView timeText;

    Spinner spinner;
    DateFormat dateFormat = new SimpleDateFormat("hh:mm");
    List<Category> categoriesList;
    GetCategories categories;
    ArrayAdapter<String> adapter;
    Context ctx;
    String[] awayStrings;


    public actClientAddNewTask() {
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ctx = this.getContext();
        View view = inflater.inflate(R.layout.act_client_add_new_task, container, false);

        tp = (TimePicker) view.findViewById(R.id.timePicker2);
        tp.setIs24HourView(true);

        done = (FloatingActionButton) view.findViewById(R.id.fabDone);
        done.hide();

        question = (TextView) view.findViewById(R.id.done);
        question.setVisibility(View.INVISIBLE);

        imgtimer = (ImageView) view.findViewById(R.id.timerimage);
        imgtimer.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View v)
            {
                Animation slideup = AnimationUtils.loadAnimation(ctx, R.anim.slide_up);

                tp.setVisibility(View.VISIBLE);
                updateTime();
                tv.setVisibility(View.INVISIBLE);
                done.setAnimation(slideup);
                done.show();
                question.setAnimation(slideup);
                question.setVisibility(View.VISIBLE);
            }});

        tv = (TextView) view.findViewById(R.id.tvchoosetime);
        tv.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View v)
            { tp.setVisibility(View.VISIBLE); updateTime();
                tv.setVisibility(View.INVISIBLE);}});


        imgdata = (ImageView) view.findViewById(R.id.imgdata);
        imgdata.setOnClickListener(new View.OnClickListener(){@Override public void onClick(View v) {updateDate();}});

        tv_data = (TextView) view.findViewById(R.id.tvdata);

        spinner = (Spinner) view.findViewById(R.id.spinnerCategory);

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

                    adapter = new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_item, awayStrings);
                    adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    spinner.setAdapter(adapter);}
            }});

        done.setOnClickListener(new View.OnClickListener(){@Override public void onClick(View v) {
            insertTask();
            ClearData();
            Toast.makeText(ctx, "Zadanie zostało utworzone", Toast.LENGTH_LONG).show();

        }});

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
      selectedItem = parent.getItemAtPosition(position).toString();
    }
    public void onNothingSelected(AdapterView<?> parent)
    {

    }
});
 return view;
    }

    private void updateTextButton()
    {
        String shortDate = DateFormat.getDateInstance().format(dateTime.getTime()).toString();
        tv_data.setText(shortDate);
    }
    private void updateTextTimeButton()
    {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String test = sdf.format(time.getTime());
        btn_time.setText(test);

    }

    private void updateTime()
    {
      //  new TimePickerDialog(this.getActivity(),t,  time.get(Calendar.HOUR),  time.get(Calendar.MINUTE),true).show();


    }

    private void updateDate()
    {
        new DatePickerDialog(this.getActivity(), d, dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH), dateTime.get(Calendar.DAY_OF_MONTH)).show();

    }
    private void insertTask()
    {
        Task t = new Task();
        User u = new User();

String timeTo = dateTime.getTime() + " " + time.getTime();


        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        Calendar timeto = Calendar.getInstance();
        timeto.add(dateTime.HOUR,1);

        t.set_TimeFrom(dateTime.getTime());
        t.set_TimeTo(timeto.getTime());
        String xxxx = selectedItem;
        t.set_CategoryId(1); // get category it ??
        t.set_CreationTime(cal.getTime());
        t.set_CreatorId(1);//u.get_Id());
        t.set_IsApproved(0);
        t.set_ExecutorId(null);
        t.set_StatusId(1);

        CreateTask newTask = new CreateTask();
        newTask.InsertTask(t);

    }

    public void ClearData()
    {
        tv.setVisibility(View.VISIBLE);
        tv.setText("Wybierz godzinę");
        tv_data.setText("Wybierz datę");
        tp.setVisibility(View.INVISIBLE);
        question.setVisibility(View.INVISIBLE);
        done.hide();
    }
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            dateTime.set(Calendar.YEAR, year);
            dateTime.set(Calendar.MONTH, month);
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            updateTextButton();
        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {



        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            view.setIs24HourView(true);
            view.setLayoutMode(1);

            time.set(Calendar.HOUR, hourOfDay);
            time.set(Calendar.MINUTE, minute);
            updateTextTimeButton();
        }
    };

}
