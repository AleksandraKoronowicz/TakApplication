package com.application.tak.takapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringDef;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

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


    public actClientAddNewTask() {}

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

                if(!tv_data.getText().toString().contains("Wybierz datę")) {
                    Animation slideup = AnimationUtils.loadAnimation(ctx, R.anim.slide_up);
                    tp.setVisibility(View.VISIBLE);
                    updateTime();
                    tv.setVisibility(View.INVISIBLE);
                    done.setAnimation(slideup);
                    done.show();
                    question.setAnimation(slideup);
                    question.setVisibility(View.VISIBLE);
                }
                else
                {
                    ShowMessage();
                }

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

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                selectedItem = parent.getItemAtPosition(position).toString();
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }});

        done.setOnClickListener(new View.OnClickListener(){@Override public void onClick(View v) {
            insertTask();
            ClearData();
            Toast.makeText(ctx, "Zadanie zostało utworzone", Toast.LENGTH_LONG).show();

        }});


        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                view.setIs24HourView(true);

                time.set(Calendar.HOUR, hourOfDay);
                time.set(Calendar.MINUTE, minute);
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
        //    new TimePickerDialog(this.getActivity(),t,  time.get(Calendar.HOUR),  time.get(Calendar.MINUTE),true).show();


    }

    private void updateDate()
    {
        new DatePickerDialog(this.getActivity(), d, dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH), dateTime.get(Calendar.DAY_OF_MONTH)).show();

    }
    private void insertTask()
    {
        Task t = new Task();
        User u = new User();

//String timeFromTimePicker = dateTime.get(Calendar.DAY_OF_MONTH) + "/" + dateTime.get(Calendar.MONTH)+ "/" + dateTime.get(Calendar.YEAR) + " " + time.get(Calendar.HOUR) + ":" +time.get(Calendar.MINUTE);
        SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Calendar calendar = Calendar.getInstance();
        calendar.set(dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH), dateTime.get(Calendar.DAY_OF_MONTH),time.get(Calendar.HOUR_OF_DAY),time.get(Calendar.MINUTE));


        Calendar cal = Calendar.getInstance();
        Calendar timeto = calendar;
        timeto.add(calendar.HOUR,1);


        t.set_TimeFrom(calendar.getTime());
        t.set_TimeTo(timeto.getTime());
        t.set_CategoryId( FindCategoryId());
        t.set_CreationTime(cal.getTime());
        t.set_CreatorId(2);//u.get_Id());
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

    public Integer FindCategoryId()
    {
        Integer idCategory = 1;
        for(Category c: categoriesList)
        {
            if (c.get_CategoryName() == selectedItem)
            {
                idCategory = c.get_Id();
            }

        }
        return idCategory;
    }

    public void ShowMessage()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ctx);
        alertDialog.setTitle("Brak wybranego terminu wykonania zadania");
        alertDialog.setMessage("Wybierz datę zadania");
        alertDialog.setIcon(R.drawable.calendar64);
        alertDialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        updateDate();
                    }
                });
    /*alertDialog.setNegativeButton("Anuluj",
            new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog,
                                    int which) {
                    dialog.cancel();
                }
            });*/
        alertDialog.show();
    }
}
