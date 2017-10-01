package com.application.tak.takapplication.adapters;


import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.ColorRes;
import android.support.design.widget.FloatingActionButton;
import android.support.transition.Visibility;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import com.application.tak.takapplication.R;
import com.application.tak.takapplication.data_list.AllTaskListStudent;

import java.util.List;

/**
 * Created by Aleksandra on 13.07.2017.
 */

public class RVAdapterAllTaskStudent extends RecyclerView.Adapter<RVAdapterAllTaskStudent.MemberViewHolder> {

    private List<AllTaskListStudent> members;
    private Context context;
    private boolean isShow = false;

    public RVAdapterAllTaskStudent(List<AllTaskListStudent> members, Context context) {
        this.members = members;
        this.context = context;
    }

    public class MemberViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private CardView cardView_fade;
        private TextView name_tv;
        private TextView time_tv;
        private TextView date_tv;
        private TextView title_tv;
        private TextView place_tv;
        private FloatingActionButton phone;
        private TextView question;
        private Button google_map;
private String task_place;

        public MemberViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv_student_alltask);
            name_tv = (TextView) itemView.findViewById(R.id.student_name);
            title_tv = (TextView) itemView.findViewById(R.id.student_task_topic);
            time_tv = (TextView) itemView.findViewById(R.id.student_task_time);
            date_tv = (TextView) itemView.findViewById(R.id.student_date_task);
            phone = (FloatingActionButton) itemView.findViewById(R.id.fabPhone);
            question =  (TextView) itemView.findViewById(R.id.student_question);
            google_map = (Button) itemView.findViewById(R.id.btnAdresMap);


        }
    }

    @Override
    public void onBindViewHolder(final MemberViewHolder memberViewHolder, final int i) {
        memberViewHolder.name_tv.setText(members.get(i).getclientName());
        memberViewHolder.title_tv.setText(members.get(i).getTitle());
        memberViewHolder.date_tv.setText(members.get(i).getData());
        memberViewHolder.time_tv.setText(members.get(i).getTime());

        memberViewHolder.phone.hide();
        memberViewHolder.question.setVisibility(View.INVISIBLE);
        memberViewHolder.cardView.setOnClickListener(new View.OnClickListener() {

                                                         @Override
                                                         public void onClick(View v) {

                                                             CardView fadeOutCard = (CardView) v.findViewById(R.id.cv_student_alltask);
                                                             TextView data = (TextView) v.findViewById(R.id.student_date_task);
                                                             TextView topic = (TextView) v.findViewById(R.id.student_task_topic);
                                                             TextView time = (TextView) v.findViewById(R.id.student_task_time);
                                                             TextView name = (TextView) v.findViewById(R.id.student_name);
                                                            Button google_map = (Button) v.findViewById(R.id.btnAdresMap);

                                                             TextView question = (TextView) v.findViewById(R.id.student_question);
                                                             fadeOutCard.setCardBackgroundColor(Color.rgb(212,212,212));

                                                             data.setAlpha(0.3f);
                                                             topic.setAlpha(0.0f);
                                                             time.setAlpha(0.3f);
                                                             name.setAlpha(0.3f);

                                                             Animation slideUp = AnimationUtils.loadAnimation(context, R.anim.slide_in);
                                                             Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);


                                                             if (!isShow) {

                                                                 FloatingActionButton ph = (FloatingActionButton) v.findViewById(R.id.fabPhone);
                                                                 if (ph.getVisibility() != View.VISIBLE) {

                                                                     ph.startAnimation(slideUp);
                                                                     ph.setVisibility(View.VISIBLE);
                                                                     question.startAnimation(slideUp);
                                                                     question.setVisibility(View.VISIBLE);
                                                                     google_map.setEnabled(false);

                                                                 } else {
                                                                     ph.startAnimation(slideDown);
                                                                      ph.setVisibility(View.INVISIBLE);

                                                                     question.startAnimation(slideDown);
                                                                     question.setVisibility(View.INVISIBLE);

                                                                     data.setAlpha(1f);
                                                                     topic.setAlpha(1f);
                                                                     time.setAlpha(1f);
                                                                     name.setAlpha(1f);
                                                                     google_map.setEnabled(true);

                                                                     fadeOutCard.setCardBackgroundColor(Color.WHITE);
                                                                 }

                                                             }
                                                         }
                                                     }
        );

        memberViewHolder.google_map.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

Integer id = memberViewHolder.google_map.getId();
String place = members.get(i).getTaskPlace();

                  String map = "http://maps.google.co.in/maps?q=" + place; //"Katowice ul Sowińskiego 9";
                  Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
                 context.startActivity(intent);
            }
        });

    }

    @Override
    public MemberViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.act_student_cv_alltask, viewGroup, false);
        MemberViewHolder memberViewHolder = new MemberViewHolder(view);
        return memberViewHolder;
    }

    @Override
    public int getItemCount() {
        return members.size();
    }
}