package com.application.tak.takapplication.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.application.tak.takapplication.R;
import com.application.tak.takapplication.data_list.TaskList;

import java.lang.reflect.Member;
import java.util.ArrayList;

/**
 * Created by Aleksandra on 13.07.2017.
 */
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MemberViewHolder> {

    private List<TaskList> members;
    private Context context;

    public RVAdapter(List<TaskList> members, Context context) {
        this.members = members;
        this.context = context;
    }

    public class MemberViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView name_tv;
        private TextView phone_tv;
        private TextView time_tv;
        private TextView date_tv;
        private TextView title_tv;
        private TextView place_tv;


        public MemberViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv);
            name_tv = (TextView) itemView.findViewById(R.id.executor_name);
            phone_tv = (TextView) itemView.findViewById(R.id.executor_phone);
            title_tv = (TextView) itemView.findViewById(R.id.task_topic);
            place_tv = (TextView) itemView.findViewById(R.id.client_adress);
            time_tv = (TextView) itemView.findViewById(R.id.task_time);
            date_tv = (TextView) itemView.findViewById(R.id.task_to_do);
        }
    }

    @Override
    public void onBindViewHolder(MemberViewHolder memberViewHolder, int i) {
        memberViewHolder.name_tv.setText(members.get(i).getExecutorName());
        memberViewHolder.phone_tv.setText(members.get(i).getExecutorPhone());
        memberViewHolder.place_tv.setText(members.get(i).getTaskPlace());
        memberViewHolder.title_tv.setText(members.get(i).getTitle());
        memberViewHolder.date_tv.setText(members.get(i).getData());
        memberViewHolder.time_tv.setText(members.get(i).getTime());
    }

    @Override
    public MemberViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.act_client_cardview_task_todo, viewGroup, false);
        MemberViewHolder memberViewHolder = new MemberViewHolder(view);
        return memberViewHolder;
    }

    @Override
    public int getItemCount() {
        return members.size();
    }
}