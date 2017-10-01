package com.application.tak.takapplication.adapters;


import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
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
import com.application.tak.takapplication.data_list.TaskList;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Aleksandra on 13.07.2017.
 */

public class RVAdapterMyTaskStudent extends RecyclerView.Adapter<RVAdapterMyTaskStudent.MemberViewHolder> {

    private List<MyTaskListStudent> members;
    private Context context;

    public RVAdapterMyTaskStudent(List<MyTaskListStudent> members, Context context) {
        this.members = members;
        this.context = context;
    }

    public class MemberViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView name_tv;
        private TextView time_tv;
        private TextView date_tv;
        private TextView dayandmonth_tv;
        private TextView title_tv;
        private TextView place_tv;
        private Button option;
        private FloatingActionButton fab_phone;

        public MemberViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv_student_mytask);
            name_tv = (TextView) itemView.findViewById(R.id.client_name_mytask);
            title_tv = (TextView) itemView.findViewById(R.id.task_topic_mytask);
            place_tv = (TextView) itemView.findViewById(R.id.client_adress);
            time_tv = (TextView) itemView.findViewById(R.id.task_time_mytask);
            date_tv = (TextView) itemView.findViewById(R.id.day_name_mytask);
            option = (Button) itemView.findViewById(R.id.buttonMenuMyTask);
            dayandmonth_tv = (TextView) itemView.findViewById(R.id.dateMyTask);
            fab_phone = (FloatingActionButton) itemView.findViewById(R.id.fabmytaskPhone);
          }
    }

    @Override
    public void onBindViewHolder(final MemberViewHolder memberViewHolder, final int i) {
        memberViewHolder.name_tv.setText(members.get(i).getmyclientName());
        memberViewHolder.place_tv.setText(members.get(i).getmytaskPlace());
        memberViewHolder.title_tv.setText(members.get(i).getmytitletask());
        memberViewHolder.date_tv.setText(GetDayNameFromDate(members.get(i).getData()));
        memberViewHolder.time_tv.setText(members.get(i).getTime());
        memberViewHolder.dayandmonth_tv.setText( ChangeDateString(members.get(i).getData()));
        memberViewHolder.fab_phone.hide();
        memberViewHolder.option.setOnClickListener(new View.OnClickListener() {

            final int position = i;
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, memberViewHolder.option);
                popupMenu.inflate(R.menu.menu_act_student_mytask);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.mytask_makeContact:
                                Toast.makeText(context, "Wykonywanie połączenia", Toast.LENGTH_LONG).show();
                                break;

                            case R.id.mytask_delete:
                                members.remove(position);
                                notifyDataSetChanged();
                                Toast.makeText(context, "Zadanie zostało usunięte", Toast.LENGTH_LONG).show();
                        }
                        return false;
                    }

                });
                popupMenu.show();
            }
});

memberViewHolder.cardView.setOnClickListener(new View.OnClickListener() {

                                                 @Override
                                                 public void onClick(View v) {

                                                     CardView fadeOutCard = (CardView) v.findViewById(R.id.cv_student_mytask);
                                                     TextView data = (TextView) v.findViewById(R.id.day_name_mytask);
                                                     TextView topic = (TextView) v.findViewById(R.id.task_topic_mytask);
                                                     TextView time = (TextView) v.findViewById(R.id.task_time_mytask);
                                                     TextView name = (TextView) v.findViewById(R.id.client_name_mytask);
                                                     TextView day_month = (TextView) v.findViewById(R.id.dateMyTask);

                                                     //fadeOutCard.setBackgroundResource(R.color.colorFadeColor);
                                                   //  fadeOutCard.setCardBackgroundColor(Color.LTGRAY);
                                                     fadeOutCard.setCardBackgroundColor(Color.rgb(212,212,212));

                                                     data.setAlpha(0.09f);
                                                     topic.setAlpha(0.09f);
                                                     time.setAlpha(0.09f);
                                                     name.setAlpha(0.09f);
                                                     day_month.setAlpha(0.09f);

                                                     Animation slideUp = AnimationUtils.loadAnimation(context, R.anim.slide_in);
                                                     Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);


                                                         FloatingActionButton ph = (FloatingActionButton) v.findViewById(R.id.fabmytaskPhone);
                                                         if (ph.getVisibility() != View.VISIBLE) {
                                                             ph.startAnimation(slideUp);
                                                             ph.setVisibility(View.VISIBLE);

                                                         } else {
                                                             ph.startAnimation(slideDown);
                                                             ph.setVisibility(View.INVISIBLE);

                                                             data.setAlpha(1f);
                                                             topic.setAlpha(1f);
                                                             time.setAlpha(1f);
                                                             name.setAlpha(1f);
                                                             day_month.setAlpha(1f);

                                                             fadeOutCard.setCardBackgroundColor(Color.WHITE);
                                                         }
                                                 }
                                             }
);

    }

    public String ChangeDateString(String dataToConvert )
    {

        String strDateFormat = "dd MMMM";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);

        DateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        Date result = null;
        try {
            result = format.parse(dataToConvert);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return sdf.format(result).toString();
    }

    public String GetDayNameFromDate(String intput)
    {
        String strDateFormat = "EEEE";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);

        DateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        Date result = null;
        try {
            result = format.parse(intput);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return sdf.format(result).toString();
    }

    @Override
    public MemberViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.act_student_cv_mytask, viewGroup, false);
        MemberViewHolder memberViewHolder = new MemberViewHolder(view);
        return memberViewHolder;
    }

    @Override
    public int getItemCount() {
        return members.size();
    }
}