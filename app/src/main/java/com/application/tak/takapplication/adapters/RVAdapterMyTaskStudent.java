package com.application.tak.takapplication.adapters;


import android.Manifest;
import android.animation.LayoutTransition;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
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
import com.application.tak.takapplication.data_list.TaskList;
import com.application.tak.takapplication.data_model.Task_V;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.support.v4.app.ActivityCompat.requestPermissions;

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
        memberViewHolder.date_tv.setText(ChangeDateString(members.get(i).getData(),"EEEE"));
        memberViewHolder.time_tv.setText(members.get(i).getTime());
        memberViewHolder.dayandmonth_tv.setText( ChangeDateString(members.get(i).getData(), "dd MMMM"));
        memberViewHolder.fab_phone.setVisibility(View.INVISIBLE);
        memberViewHolder.fab_phone.hide();
        SetNormalLayout(memberViewHolder);

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
                            case R.id.mytask_delete:
                                ShowMessageBox(members, position, memberViewHolder);


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
                                                     TextView place_tv = (TextView) v.findViewById(R.id.client_adress);

                                                     //fadeOutCard.setBackgroundResource(R.color.colorFadeColor);
                                                   //  fadeOutCard.setCardBackgroundColor(Color.LTGRAY);
                                                     fadeOutCard.setCardBackgroundColor(Color.rgb(212,212,212));

                                                     data.setAlpha(0.09f);
                                                     topic.setAlpha(0.09f);
                                                     time.setAlpha(0.09f);
                                                     name.setAlpha(0.09f);
                                                     day_month.setAlpha(0.09f);
                                                     place_tv.setEnabled(false);

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
                                                             place_tv.setEnabled(true);

                                                             fadeOutCard.setCardBackgroundColor(Color.WHITE);
                                                         }
                                                 } });


       memberViewHolder.place_tv.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Mapy");
                alertDialog.setMessage("Czy chcesz sprawdzić miejsce wykonania zadania na mapie google?");
                alertDialog.setIcon(R.drawable.googlemaps);
                alertDialog.setPositiveButton("Jasne!",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog,
                                                int which) {
                                String place = members.get(i).getmytaskPlace();
                                String map = "http://maps.google.co.in/maps?q=" + place; //"Katowice ul Sowińskiego 9";
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
                                context.startActivity(intent);
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
        });

        memberViewHolder.fab_phone.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String phone = members.get(i).getmyclientPhonee();
                int permissionCheck = ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE
                );
                   context.startActivity(new Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:"+phone)));
            }
        });


    }

    public void DeleteTask(List<MyTaskListStudent> members, int position)
    {
        Task_V task = new Task_V();
        task = members.get(position).tsk;
        task.set_StatusId(1);
        task.set_ExecutorId(null);

        UpdateTask updateTask = new UpdateTask();
        updateTask.UpdateTask(task);

        members.remove(position);
    }

    public String ChangeDateString(String dataToConvert, String resultFormat )
    {
        SimpleDateFormat sdf = new SimpleDateFormat(resultFormat);

        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date result = null;
        try {
            result = format.parse(dataToConvert);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return sdf.format(result).toString();
    }

    public void SetChooseActionLayoutAnimation(MemberViewHolder mv)
    {
        Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.anim_slide_out_right);
        mv.cardView.setAnimation(slideDown);
    }
    public void ShowMessageBox(List<MyTaskListStudent> member, final int position, final MemberViewHolder mm)
    {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Jesteś pewien?");
        alertDialog.setMessage("Rezygnacja z zadania automatycznie powiadomi drogą sms o odwołaniu wykonania zadania");
        alertDialog.setIcon(R.drawable.email2);
        alertDialog.setPositiveButton("Tak",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {


                        SetChooseActionLayoutAnimation(mm);
                        notifyItemRemoved(mm.getLayoutPosition());
                        DeleteTask(members, position);

                        String phone = members.get(position).telefon;
                        String msg = String.format
                                ("Dzień dobry,\n pragnę poinformować o rezygnacji z zadania:%1$2s z dnia %2$2s o godzinie %3$2s.\n Pozdrawiam %4$2s"
                                        ,members.get(position).mytitletask
                                       ,members.get(position).getData()
                                        ,members.get(position).getCzas()
                                        ,members.get(position).studentName
                                );

                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phone));
                        intent.putExtra("sms_body", msg);
                        context.startActivity(intent);


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

    public void SetNormalLayout(MemberViewHolder mv)
    {
        mv.date_tv.setAlpha(1f);
        mv.name_tv.setAlpha(1f);
        mv.time_tv.setAlpha(1f);
        mv.place_tv.setAlpha(1f);
        mv.dayandmonth_tv.setAlpha(1f);
        mv.place_tv.setEnabled(true);
        mv.cardView.setCardBackgroundColor(Color.WHITE);
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