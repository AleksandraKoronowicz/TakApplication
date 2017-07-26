package com.application.tak.takapplication.adapters;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.application.tak.takapplication.R;
import com.application.tak.takapplication.data_list.TaskListNotSelected;

import java.util.List;

/**
 * Created by Aleksandra on 13.07.2017.
 */

public class RVAdapterTaskNotSelected extends RecyclerView.Adapter<RVAdapterTaskNotSelected.MemberViewHolder> {

    private List<TaskListNotSelected> members;
    private Context context;

    public RVAdapterTaskNotSelected(List<TaskListNotSelected> members, Context context) {
        this.members = members;
        this.context = context;
    }

    public class MemberViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView time_tv;
        private TextView date_tv;
        private TextView title_tv;
        private TextView place_tv;
        private ImageButton option;


        public MemberViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv2);
            title_tv = (TextView) itemView.findViewById(R.id.task_topic2);
            place_tv = (TextView) itemView.findViewById(R.id.client_adress2);
            time_tv = (TextView) itemView.findViewById(R.id.task_time2);
            date_tv = (TextView) itemView.findViewById(R.id.task_to_do2);
            option = (ImageButton) itemView.findViewById(R.id.option);

        }
    }

    @Override
    public void onBindViewHolder(final MemberViewHolder memberViewHolder, final int i) {
        final TaskListNotSelected item = members.get(i);
        memberViewHolder.place_tv.setText(members.get(i).getTaskPlace());
        memberViewHolder.title_tv.setText(members.get(i).getTitle());
        memberViewHolder.date_tv.setText(members.get(i).getData());
        memberViewHolder.time_tv.setText(members.get(i).getTime());
        memberViewHolder.option.setOnClickListener(new View.OnClickListener() {

            final int position = i;
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, memberViewHolder.option);
                popupMenu.inflate(R.menu.menu_act__client__task);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.task_edit:
                                Toast.makeText(context, "Edycja", Toast.LENGTH_LONG).show();
                                break;

                            case R.id.task_delete:
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

    }
        @Override
        public MemberViewHolder onCreateViewHolder (ViewGroup viewGroup,int i){
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.act_client_cardview_notselected, viewGroup, false);
            MemberViewHolder memberViewHolder = new MemberViewHolder(view);
            return memberViewHolder;
        }

        @Override
        public int getItemCount() {
            return members.size();
        }
    }
