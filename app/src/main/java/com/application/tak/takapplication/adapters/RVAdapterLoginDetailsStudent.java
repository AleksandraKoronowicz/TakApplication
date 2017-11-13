package com.application.tak.takapplication.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.application.tak.takapplication.R;
import com.application.tak.takapplication.data_list.AllTaskListStudent;

import java.util.List;

/**
 * Created by Aleksandra on 13.07.2017.
 */

public class RVAdapterLoginDetailsStudent extends RecyclerView.Adapter<RVAdapterLoginDetailsStudent.MemberViewHolder> {

    private List<AllTaskListStudent> members;
    private Context context;
    private boolean isShow = false;

    public RVAdapterLoginDetailsStudent(List<AllTaskListStudent> members, Context context) {
        this.members = members;
        this.context = context;
    }

    public class MemberViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private FloatingActionButton btnyes;


        public MemberViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv_student_alltask);
            btnyes = (FloatingActionButton) itemView.findViewById(R.id.fabGoToStudentTaSK);
        }
    }

    @Override
    public void onBindViewHolder(MemberViewHolder memberViewHolder, int i) {

        memberViewHolder.btnyes.show();
        memberViewHolder.btnyes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fabGoToStudentTaSK);

          /*      startActivity(new Intent(v.getContext(), actStudentMainAllTaskListActivity.class));
                Intent i = new Intent(null, actStudentMainAllTaskListActivity.class);
                startActivity(i);
*/
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