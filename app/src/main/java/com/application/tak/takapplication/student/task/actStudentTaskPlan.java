package com.application.tak.takapplication.student.task;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import com.application.tak.takapplication.R;
import com.application.tak.takapplication.adapters.RVAdapterAllTaskStudent;
import com.application.tak.takapplication.adapters.RVAdapterCalendarCardStudent;
import com.application.tak.takapplication.adapters.RVAdapterCalendarStudent;
import com.application.tak.takapplication.data_access.Config;
import com.application.tak.takapplication.data_access.GetAllClientTasks;
import com.application.tak.takapplication.data_access.GetAllStudentTasks;
import com.application.tak.takapplication.data_access.GetAllStudentTasksByStatus;
import com.application.tak.takapplication.data_list.AllTaskListStudent;
import com.application.tak.takapplication.data_list.MyTaskListStudent;
import com.application.tak.takapplication.data_model.Task_V;
import com.application.tak.takapplication.data_model.User;
import com.application.tak.takapplication.interfaces.OnDBRequestFinished;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;


/**
 * Created by Aleksandra on 04.09.2017.
 */
public class actStudentTaskPlan extends Fragment {

    private static final String TAG = "Calendar";
    private RecyclerView recyclerview;
    private CalendarView calendar;
    // whole data
    private List<MyTaskListStudent> memberList;
    //for calendar day
    private List<MyTaskListStudent> memberList2;
    //for current day
    private List<MyTaskListStudent> memberListCurrentDay;
    private String date;
    private RVAdapterCalendarStudent adapter;
    private String currentTime;

   private User student;
    Task_V task;
    GetAllStudentTasksByStatus tasks;

    public actStudentTaskPlan()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.act_student_calendar, container, false);
        memberList = new ArrayList<MyTaskListStudent>();
        memberListCurrentDay = new ArrayList<MyTaskListStudent>();

        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview_plantask);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(layoutManager);
      currentTime = DateFormat.getDateInstance().format(new Date()).toString();// ChangeDateString(DateFormat.getDateInstance().format(new Date()).toString(),"MM/dd"); //new SimpleDateFormat("MM/dd").format(Calendar.getInstance().getTime());
        student = new User();
        student.set_Id(1);
        tasks = new GetAllStudentTasksByStatus(getContext(),student,2);
        tasks.setDBRequestFinishedListener(new OnDBRequestFinished() {

            @Override
            public void onDBRequestFinished() {
                if (tasks._tasks != null) {
                    Config.ClientTasks = tasks._tasks;
                    for (Task_V task : Config.ClientTasks)
                    {
                        MyTaskListStudent member = new MyTaskListStudent(task);
                        memberList.add(member);

                        String taskDate = ChangeDateString(member.getData().toString(),"dd.MM.yyyy");//new SimpleDateFormat("MM/dd").format(task.get_TimeFrom()).toString();
                       if (taskDate != null) {
                           if (taskDate.equals(currentTime)) {
                               memberListCurrentDay.add(member);
                           }
                       }
                    }
                }
                RVAdapterCalendarCardStudent adapter2 = new RVAdapterCalendarCardStudent(memberListCurrentDay, getActivity());
                recyclerview.setAdapter(adapter2);
            }

        });

        calendar = (CalendarView) view.findViewById(R.id.calendarVTask);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                if (memberList2 != null)
                {
                memberList2.clear();
                }

                month = month+1;
                String calendarDate= month+"/"+dayOfMonth;
                memberList2 = new ArrayList<MyTaskListStudent>();


                for (MyTaskListStudent d : memberList) {
                    String taskDate = ChangeDateString(d.getData().toString(),"MM/d");
                    if (taskDate != null) {
                        if (taskDate.contains(calendarDate.toString())) {
                            memberList2.add(d);
                        }
                    }
                }

                if (memberList2 == null) {

                    Task_V t = new Task_V();
                    t.set_CategoryName("Brak zadań");
                    MyTaskListStudent member_2 = new MyTaskListStudent(t);
                    memberList2.add(member_2);
                }

                RVAdapterCalendarCardStudent adapter2 = new RVAdapterCalendarCardStudent(memberList2, getActivity());
                recyclerview.setAdapter(adapter2);
            }

       });


        return view;
    }

    public String ChangeDateString(String dataToConvert, String resultFormat )
    {
        SimpleDateFormat sdf = new SimpleDateFormat(resultFormat);
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        Date result = null;
        try {
            result = format.parse(dataToConvert);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return sdf.format(result).toString();
    }

}


