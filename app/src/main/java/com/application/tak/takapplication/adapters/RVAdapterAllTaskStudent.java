package com.application.tak.takapplication.adapters;


import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.transition.Visibility;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
        private TextView name_tv;
        private TextView time_tv;
        private TextView date_tv;
        private TextView title_tv;
        private TextView place_tv;
        private FloatingActionButton btnyes;


        public MemberViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv_student_alltask);
            name_tv = (TextView) itemView.findViewById(R.id.student_name);
            title_tv = (TextView) itemView.findViewById(R.id.student_task_topic);
            place_tv = (TextView) itemView.findViewById(R.id.student_client_adress);
            time_tv = (TextView) itemView.findViewById(R.id.student_task_time);
            date_tv = (TextView) itemView.findViewById(R.id.student_date_task);
            btnyes = (FloatingActionButton) itemView.findViewById(R.id.floatingActBtnYesAllTask);
        }
    }

    @Override
    public void onBindViewHolder(MemberViewHolder memberViewHolder, int i) {
        memberViewHolder.name_tv.setText(members.get(i).getclientName());
        memberViewHolder.place_tv.setText(members.get(i).getTaskPlace());
        memberViewHolder.title_tv.setText(members.get(i).getTitle());
        memberViewHolder.date_tv.setText(members.get(i).getData());
        memberViewHolder.time_tv.setText(members.get(i).getTime());
        memberViewHolder.btnyes.hide();
        memberViewHolder.cardView.setOnClickListener(new View.OnClickListener() {

                                                       @Override
                                                       public void onClick(View v) {

                                                           if (!isShow) {

                                                               FloatingActionButton fab=  (FloatingActionButton) v.findViewById(R.id.floatingActBtnYesAllTask);

                                                               if (fab.getVisibility() != View.VISIBLE)
                                                               {
                                                                   fab.show();
                                                               }
                                                               else {

                                                               fab.hide();
                                                               }
                                                               }


                                                          //     MemberViewHolder memberViewHolder = new MemberViewHolder(v);
                                                              // ;
                                                             //  memberViewHolder.btnyes.show();
                                                              // isShow = true;
                                                           }
                                                       }


        );
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