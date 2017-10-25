package com.application.tak.takapplication.adapters;


import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import com.application.tak.takapplication.R;
import com.application.tak.takapplication.data_list.MyTaskListStudent;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Aleksandra on 13.07.2017.
 */

public class RVAdapterCalendarStudent extends ArrayAdapter<MyTaskListStudent> {

    private final List<MyTaskListStudent> listcaltask;
    private Context context;
    private int itemResource;

    public RVAdapterCalendarStudent(Context context, int itemResource, List<MyTaskListStudent> caltask) {

        // 1. Initialize our adapter
        super(context, R.layout.act_student_calendar_task, caltask);
        this.listcaltask = caltask;
        this.context = context;
        this.itemResource = itemResource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 2. Have we inflated this view before?
        View itemView;
        if (convertView != null) {

            // 2a. We have so let's reuse.
            itemView = convertView;
        }
        else {

            // 2b. We have NOT so let's inflate
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(this.itemResource, parent, false);
        }


        MyTaskListStudent taskCalendar = this.listcaltask.get(position);

        if (taskCalendar != null) {

            TextView  nameCalendar = (TextView) itemView.findViewById(R.id.client_name_calendar);
            TextView  title_tv = (TextView) itemView.findViewById(R.id.task_topic_calendar);
            TextView  time_tv = (TextView) itemView.findViewById(R.id.task_time_calendar);
          //  ListView lv = (ListView) itemView.findViewById(R.id.lvCalendar);
            FloatingActionButton fab_msg = (FloatingActionButton) itemView.findViewById(R.id.fabCalendarMessage);
            fab_msg.setVisibility(View.INVISIBLE);
            FloatingActionButton fab_ph = (FloatingActionButton) itemView.findViewById(R.id.fabCalendarPhone);
            fab_ph.setVisibility(View.INVISIBLE);


            // 4. Inflate the UI widgets

            // 5. Set the UI widgets with appropriate data from the Bakery model
            nameCalendar.setText(taskCalendar.myclientName);
            title_tv.setText(taskCalendar.mytitletask);
            time_tv.setText(taskCalendar.dataTime);





        }

        return itemView;
    }
}