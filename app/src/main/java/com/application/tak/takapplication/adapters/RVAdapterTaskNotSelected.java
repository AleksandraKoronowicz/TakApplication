package com.application.tak.takapplication.adapters;


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
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
import com.application.tak.takapplication.data_access.UpdateTask;
import com.application.tak.takapplication.data_list.MyTaskListStudent;
import com.application.tak.takapplication.data_list.TaskListNotSelected;
import com.application.tak.takapplication.data_model.Task_V;
import com.application.tak.takapplication.data_model.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
        private TextView day_name_tv;
        private ImageButton option;
        private FloatingActionButton del;
        private FloatingActionButton edit;


        public MemberViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv2);
            title_tv = (TextView) itemView.findViewById(R.id.task_topic2);
            day_name_tv = (TextView) itemView.findViewById(R.id.client_nameOfaDay);
            time_tv = (TextView) itemView.findViewById(R.id.task_time2);
            date_tv = (TextView) itemView.findViewById(R.id.task_to_do2);
            option = (ImageButton) itemView.findViewById(R.id.option);
            del = (FloatingActionButton) itemView.findViewById(R.id.fabClientDelete);
            edit = (FloatingActionButton) itemView.findViewById(R.id.fabClientEdit);
        }
    }

    @Override
    public void onBindViewHolder(final MemberViewHolder memberViewHolder, final int i) {
        final TaskListNotSelected item = members.get(i);
        memberViewHolder.day_name_tv.setText(ChangeDateString(members.get(i).getData(),"EEEE"));
        memberViewHolder.title_tv.setText(members.get(i).getTitle());
        memberViewHolder.date_tv.setText(ChangeDateString(members.get(i).getData(),"dd MMMM"));
        memberViewHolder.time_tv.setText(members.get(i).getTime());
        memberViewHolder.del.hide();
        memberViewHolder.edit.hide();

        memberViewHolder.del.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ShowMessageBox(members, i);
            }
        });

   memberViewHolder.cardView.setOnClickListener(new View.OnClickListener() {

       @Override
       public void onClick(View v) {

           CardView fadeOutCard = (CardView) v.findViewById(R.id.cv2);
           TextView data = (TextView) v.findViewById(R.id.task_to_do2);
           TextView topic = (TextView) v.findViewById(R.id.task_topic2);
           TextView time = (TextView) v.findViewById(R.id.task_time2);
           TextView day_month = (TextView) v.findViewById(R.id.client_nameOfaDay);

           //  fadeOutCard.setCardBackgroundColor(Color.LTGRAY);
           fadeOutCard.setCardBackgroundColor(Color.rgb(212,212,212));

           data.setAlpha(0.09f);
           topic.setAlpha(0.09f);
           time.setAlpha(0.09f);
           day_month.setAlpha(0.09f);

           Animation slideUp = AnimationUtils.loadAnimation(context, R.anim.slide_in);
           Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);

           FloatingActionButton trasz = (FloatingActionButton) v.findViewById(R.id.fabClientDelete);
           FloatingActionButton edit = (FloatingActionButton) v.findViewById(R.id.fabClientEdit);
           if (trasz.getVisibility() != View.VISIBLE) {
               trasz.startAnimation(slideUp);
               trasz.setVisibility(View.VISIBLE);

               edit.startAnimation(slideUp);
               edit.setVisibility(View.VISIBLE);

           } else {
               trasz.startAnimation(slideDown);
               trasz.setVisibility(View.INVISIBLE);

               edit.startAnimation(slideDown);
               edit.setVisibility(View.INVISIBLE);

               data.setAlpha(1f);
               topic.setAlpha(1f);
               time.setAlpha(1f);
               day_month.setAlpha(1f);

               fadeOutCard.setCardBackgroundColor(Color.WHITE);
           }
       } });



   ///////////////////////////////////////popmenu action///////////////////////////////////////
   /*     memberViewHolder.option.setOnClickListener(new View.OnClickListener() {
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
*/
    }

    public void DeleteTask(List<TaskListNotSelected> members, int position)
    {
        Task_V task = new Task_V();

        task = members.get(position).tsk;
        task.set_StatusId(4);

        members.remove(position);

        UpdateTask updateTask = new UpdateTask();
        updateTask.UpdateTask(task);

    }

    public void ShowMessageBox(List<TaskListNotSelected> member, final int position)
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setMessage("Napewno chcesz usunąć zadanie?");
        alertDialog.setIcon(R.drawable.question);
        alertDialog.setPositiveButton("Tak",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {

                        notifyDataSetChanged();
                        DeleteTask(members, position);

                        Toast.makeText(context, "Zadanie zostało usunięte", Toast.LENGTH_LONG).show();
                    }
                });
        alertDialog.setNegativeButton("Anuluj",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog,
                                        int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }

    public String ChangeDateString(String dataToConvert, String resultFormat )
    {

        //   String strDateFormat = "dd MMMM";
        SimpleDateFormat sdf = new SimpleDateFormat(resultFormat);

        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date result = null;
        try {
            result = format.parse(dataToConvert);
            return sdf.format(result);
        } catch (ParseException e) {
            e.printStackTrace();
            return"";
        }

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
