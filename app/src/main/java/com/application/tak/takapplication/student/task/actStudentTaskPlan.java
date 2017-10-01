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
import com.application.tak.takapplication.adapters.RVAdapterCalendarCardStudent;
import com.application.tak.takapplication.adapters.RVAdapterCalendarStudent;
import com.application.tak.takapplication.data_list.MyTaskListStudent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by Aleksandra on 04.09.2017.
 */
public class actStudentTaskPlan extends Fragment {

    private static final String TAG = "Calendar";
    private RecyclerView recyclerview;
    private CalendarView calendar;
    private ListView listingsView;
    private List<MyTaskListStudent> memberList;
    private List<MyTaskListStudent> memberList2;
    private String date;
private  RVAdapterCalendarStudent adapter;

    public actStudentTaskPlan()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.act_student_calendar, container, false);

        memberList = new ArrayList<MyTaskListStudent>();
        MyTaskListStudent member = new MyTaskListStudent("Wyrzuć śmieci", "2017/09/10", "13:00 d0 16:00", "Magłorzata Nowak", "504 444 444", "Gliwice, Kwiatowa 43");
        memberList.add(member);
        final MyTaskListStudent member2 = new MyTaskListStudent("Umyj okna", "2017/09/10", "13:00 d0 16:00", "Magłorzata Kicha", "504 444 213", "Gliwice, Ostrudźka 43");
        memberList.add(member2);
        MyTaskListStudent member3 = new MyTaskListStudent("Wyrzuć śmieci", "2017/09/12", "13:00 d0 16:00", "Magłorzata Kicha", "504 444 213", "Gliwice, Ostrudźka 43");
        memberList.add(member3);
        MyTaskListStudent member4 = new MyTaskListStudent("Wyrzuć śmieci", "2017/09/15", "13:00 d0 16:00", "Magłorzata Kicha", "504 444 213", "Gliwice, Ostrudźka 43");
        memberList.add(member4);


        calendar = (CalendarView) view.findViewById(R.id.calendarVTask);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                if (memberList2 != null)
                {
                memberList2.clear();
                }

                month = month+1;
                date = year + "/" + month + "/" +dayOfMonth;
                Log.d(TAG,date);
                memberList2 = new ArrayList<MyTaskListStudent>();

for (MyTaskListStudent d : memberList)
{
    String aaaaa= month+"/"+dayOfMonth;

    if (d.dataTask.contains(aaaaa))
    {
        memberList2.add(d);
    }
}
                if (memberList2 == null) {

                    MyTaskListStudent member_2 = new MyTaskListStudent("BRAK ZADAN", " ", " ", " ", " ", " ");
                    memberList2.add(member_2);
                }
           //     RVAdapterCalendarStudent adapter = new RVAdapterCalendarStudent(getActivity(), R.layout.act_student_calendar_task, memberList2);
             //   listingsView.setAdapter(adapter);
                RVAdapterCalendarCardStudent adapter2 = new RVAdapterCalendarCardStudent(memberList2, getActivity());
                recyclerview.setAdapter(adapter2);
            }

       });

 /*       if (memberList2 == null)
        {
            memberList2 = new ArrayList<MyTaskListStudent>();
            MyTaskListStudent member_10 = new MyTaskListStudent("BRAK ZADAN", " ", " ", " ", " ", " ");
            memberList2.add(member_10);
            adapter = new RVAdapterCalendarStudent(getActivity(), R.layout.act_student_calendar_task, memberList2);
            listingsView = (ListView)view.findViewById(R.id.lvCalendar);
            listingsView.setAdapter(adapter);
            Toast.makeText(getActivity(), "BRAK ZADAŃ W DNIU " + date, Toast.LENGTH_LONG).show();
        }

        else if (memberList2 != null)
        {
*/

       // RVAdapterCalendarStudent    adapter = new RVAdapterCalendarStudent(getActivity(), R.layout.act_student_calendar_task, memberList);
         //   listingsView = (ListView)view.findViewById(R.id.lvCalendar);
        //    listingsView.setAdapter(adapter);

        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview_plantask);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(layoutManager);


        RVAdapterCalendarCardStudent adapter2 = new RVAdapterCalendarCardStudent(memberList, getActivity());
        recyclerview.setAdapter(adapter2);




     /*   listingsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //    parent.getItemAtPosition(position).setBackgroundResource(R.color.colorFadeColor);
///view.setBackgroundResource(R.color.colorFadeColor);
              Animation slideUp = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in);
     //           Animation slideDown = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_down);

                FloatingActionButton msg = (FloatingActionButton) view.findViewById(R.id.fabCalendarMessage);
                FloatingActionButton ph = (FloatingActionButton) view.findViewById(R.id.fabCalendarPhone);

                if (msg.getVisibility() != View.VISIBLE) {

                    TextView name = (TextView) view.findViewById(R.id.task_topic_calendar);
                    TextView topic = (TextView) view.findViewById(R.id.task_time_calendar);
                    TextView time = (TextView) view.findViewById(R.id.task_topic_calendar);

                    name.setAlpha(0.2f);
                    topic.setAlpha(0.3f);
                    time.setAlpha(0.3f);

               /*     msg.startAnimation(slideUp);
                    msg.setVisibility(View.VISIBLE);
                    msg.show();

                    ph.startAnimation(slideUp);
                    ph.setVisibility(View.VISIBLE);
                    ph.show();

                }

                else {
                    msg.startAnimation(slideDown);
                    msg.setVisibility(View.INVISIBLE);

                   // name.setAlpha(1f);
                  // topic.setAlpha(1f);
                  //  time.setAlpha(1f);

                   // parent.getChildAt(position).setBackgroundResource(R.color.cardview_light_background);
                }



            }
        });

*/


        return view;
    }
}


