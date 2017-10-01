package com.application.tak.takapplication.adapters;


import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import com.application.tak.takapplication.R;
import com.application.tak.takapplication.data_list.AllTaskListStudent;
import com.application.tak.takapplication.data_list.MyTaskListStudent;

import java.util.List;

/**
 * Created by Aleksandra on 13.07.2017.
 */

public class RVAdapterCalendarCardStudent extends RecyclerView.Adapter<RVAdapterCalendarCardStudent.MemberViewHolder> {

    private List<MyTaskListStudent> members;
    private Context context;
    private boolean isShow = false;

    public RVAdapterCalendarCardStudent(List<MyTaskListStudent> members, Context context) {
        this.members = members;
        this.context = context;
    }

    public class MemberViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView name_tv;
        private TextView time_tv;
        private TextView title_tv;
        private FloatingActionButton phone;
        private FloatingActionButton msg;


        public MemberViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv_calendar_listTask);
            name_tv = (TextView) itemView.findViewById(R.id.client_name_calendar);
            title_tv = (TextView) itemView.findViewById(R.id.task_topic_calendar);
            time_tv = (TextView) itemView.findViewById(R.id.task_time_calendar);
            phone = (FloatingActionButton) itemView.findViewById(R.id.fabCalendarPhone);
            msg = (FloatingActionButton) itemView.findViewById(R.id.fabCalendarMessage);

        }
    }

    @Override
    public void onBindViewHolder(MemberViewHolder memberViewHolder, final int i) {
        memberViewHolder.name_tv.setText(members.get(i).getmyclientName());
        memberViewHolder.title_tv.setText(members.get(i).getmytitletask());
        memberViewHolder.time_tv.setText(members.get(i).getTime());
        memberViewHolder.phone.hide();
        memberViewHolder.msg.hide();

        memberViewHolder.cardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                CardView fadeOutCard = (CardView) v.findViewById(R.id.cv_calendar_listTask);
                TextView name = (TextView) v.findViewById(R.id.client_name_calendar);
                TextView topic = (TextView) v.findViewById(R.id.task_topic_calendar);
                TextView time = (TextView) v.findViewById(R.id.task_time_calendar);

                                                       /*      FloatingActionButton fab_msg = (FloatingActionButton) v.findViewById(R.id.fabCalendarMessage);
                                                             fab_msg.setVisibility(View.INVISIBLE);
                                                             FloatingActionButton fab_ph = (FloatingActionButton) v.findViewById(R.id.fabCalendarPhone);
                                                             fab_ph.setVisibility(View.INVISIBLE);

*/
                fadeOutCard.setBackgroundResource(R.color.colorFadeColor);

                topic.setAlpha(0.0f);
                time.setAlpha(0.3f);
                name.setAlpha(0.3f);


                Animation slideUp = AnimationUtils.loadAnimation(context, R.anim.slide_in);
                Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);


                if (!isShow) {
                    FloatingActionButton fab_msg = (FloatingActionButton) v.findViewById(R.id.fabCalendarMessage);
                    FloatingActionButton ph = (FloatingActionButton) v.findViewById(R.id.fabCalendarPhone);
                    if (ph.getVisibility() != View.VISIBLE) {

                        ph.startAnimation(slideUp);
                        ph.setVisibility(View.VISIBLE);
                        fab_msg.setAnimation(slideUp);
                        fab_msg.setVisibility(View.VISIBLE);


                    } else {
                        ph.startAnimation(slideDown);
                        ph.setVisibility(View.INVISIBLE);

                        fab_msg.startAnimation(slideDown);
                        fab_msg.setVisibility(View.INVISIBLE);

                        topic.setAlpha(1f);
                        time.setAlpha(1f);
                        name.setAlpha(1f);

                        fadeOutCard.setBackgroundResource(R.color.cardview_light_background);   //   (         R.color.cardview_light_background);
                    }

                }
            }

                                                     }


        );
    }

    @Override
    public MemberViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.act_student_calendar_task, viewGroup, false);
        MemberViewHolder memberViewHolder = new MemberViewHolder(view);
        return memberViewHolder;
    }

    @Override
    public int getItemCount() {
        return members.size();
    }
}