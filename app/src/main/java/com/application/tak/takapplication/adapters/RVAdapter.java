package com.application.tak.takapplication.adapters;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.application.tak.takapplication.R;
import com.application.tak.takapplication.actClientTaskToDo;
import com.application.tak.takapplication.data_access.UpdateTask;
import com.application.tak.takapplication.data_list.TaskList;
import com.application.tak.takapplication.data_list.TaskListNotSelected;
import com.application.tak.takapplication.data_model.Task_V;

import java.lang.reflect.Member;
import java.nio.channels.FileLock;
import java.util.ArrayList;

/**
 * Created by Aleksandra on 13.07.2017.
 */
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MemberViewHolder> {

    private List<TaskList> members;
    private Context context;
    private boolean isShow = false;

    public RVAdapter(List<TaskList> members, Context context) {
        this.members = members;
        this.context = context;
    }

    public class MemberViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView name_tv;
        private TextView phone_tv;
        private TextView time_tv;
        private TextView shortdate_tv;
        private TextView class_tv;
        private TextView school_tv;
        private FloatingActionButton phone;
        private FloatingActionButton delete;


        public MemberViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv);

            name_tv = (TextView) itemView.findViewById(R.id.executor_name);
            school_tv = (TextView) itemView.findViewById(R.id.clientTaskToDo_studentSchool);
            class_tv = (TextView) itemView.findViewById(R.id.client_studentClas);
            time_tv = (TextView) itemView.findViewById(R.id.task_time);
            shortdate_tv = (TextView) itemView.findViewById(R.id.client_shotDate);
            phone = (FloatingActionButton) itemView.findViewById(R.id.fabClientPhoneTaskToDo);
            delete = (FloatingActionButton) itemView.findViewById(R.id.fabClientDeleteTaskToDo);

        }
    }

    @Override
    public void onBindViewHolder(final MemberViewHolder memberViewHolder, final int i) {


        memberViewHolder.phone.setVisibility(View.INVISIBLE);
        memberViewHolder.delete.setVisibility(View.INVISIBLE);
        memberViewHolder.name_tv.setText(members.get(i).getExecutorName());
//      memberViewHolder.phone_tv.setText(members.get(i).getExecutorPhone());
        memberViewHolder.class_tv.setText(members.get(i).getExecutorClass());
        memberViewHolder.school_tv.setText(members.get(i).getExecutorSchool());
        memberViewHolder.shortdate_tv.setText(members.get(i).getData());
        memberViewHolder.time_tv.setText(members.get(i).getTime());
        memberViewHolder.delete.hide();
        memberViewHolder.phone.hide();
        SetNormalLayout(memberViewHolder);
        memberViewHolder.cardView.setOnClickListener(new View.OnClickListener() {

                                                         @Override
                                                         public void onClick(View v) {

                                                             CardView fadeOutCard = (CardView) v.findViewById(R.id.cv);
                                                             TextView data = (TextView) v.findViewById(R.id.client_shotDate);
                                                             TextView school = (TextView) v.findViewById(R.id.clientTaskToDo_studentSchool);
                                                             TextView clas = (TextView) v.findViewById(R.id.client_studentClas);
                                                             TextView time = (TextView) v.findViewById(R.id.task_time);
                                                             TextView name = (TextView) v.findViewById(R.id.executor_name);

                                                             fadeOutCard.setCardBackgroundColor(Color.rgb(212,212,212));

                                                             data.setAlpha(0.3f);
                                                             school.setAlpha(0.0f);
                                                             time.setAlpha(0.3f);
                                                             name.setAlpha(0.2f);
                                                             clas.setAlpha(0.3f);

                                                             Animation slideUp = AnimationUtils.loadAnimation(context, R.anim.slide_in);
                                                             Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);
                                                             FloatingActionButton ph = (FloatingActionButton) v.findViewById(R.id.fabClientPhoneTaskToDo);
                                                             FloatingActionButton del = (FloatingActionButton) v.findViewById(R.id.fabClientDeleteTaskToDo);

                                                                 if (ph.getVisibility() != View.VISIBLE) {

                                                                     ph.startAnimation(slideUp);
                                                                     ph.setVisibility(View.VISIBLE);
                                                                     del.startAnimation(slideUp);
                                                                     del.setVisibility(View.VISIBLE);


                                                                 } else {
                                                                     ph.startAnimation(slideDown);
                                                                     ph.setVisibility(View.INVISIBLE);
                                                                     del.startAnimation(slideDown);
                                                                     del.setVisibility(View.INVISIBLE);

                                                                     data.setAlpha(1f);
                                                                     school.setAlpha(1f);
                                                                     time.setAlpha(1f);
                                                                     name.setAlpha(1f);
                                                                     clas.setAlpha(1f);

                                                                     fadeOutCard.setCardBackgroundColor(Color.WHITE);
                                                                 }
                                                         }
                                                     }
        );

        memberViewHolder.phone.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String phone = members.get(i).getExecutorPhone();
                int permissionCheck = ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE);
                context.startActivity(new Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:"+phone)));

            }
        });

        memberViewHolder.delete.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

                // Setting Dialog Title
                alertDialog.setTitle("Czy chcesz usunąć zadanie?");

                // Setting Dialog Message
                alertDialog.setMessage("Automatycznie drogą sms uczeń zostanie powiadomiony o wycofaniu zadania");
                alertDialog.setIcon(R.drawable.edit);

                // Setting Positive Yes Button
                alertDialog.setPositiveButton("Tak", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        SetDeleteAnimation(memberViewHolder);
                        DeleteTask(members,i);
                        onBindViewHolder(memberViewHolder,i);
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
    }

    public void DeleteTask(List<TaskList> members, int position)
    {
        Task_V task = new Task_V();

        task = members.get(position).tsk;
        task.set_StatusId(4);

        UpdateTask updateTask = new UpdateTask();
        updateTask.UpdateTask(task);
        members.remove(position);
    }

    public void SetNormalLayout(MemberViewHolder mv)
    {
        mv.shortdate_tv.setAlpha(1f);
        mv.school_tv.setAlpha(1f);
        mv.class_tv.setAlpha(1f);
        mv.name_tv.setAlpha(1f);
        mv.time_tv.setAlpha(1f);

    }
    public void SetDeleteAnimation(MemberViewHolder mv)
    {
        Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.anim_slide_out_right);
        mv.cardView.setAnimation(slideDown);
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