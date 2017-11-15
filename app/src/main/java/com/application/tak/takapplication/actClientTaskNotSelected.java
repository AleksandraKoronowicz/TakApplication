package com.application.tak.takapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.application.tak.takapplication.adapters.RVAdapter;
import com.application.tak.takapplication.adapters.RVAdapterTaskNotSelected;
import com.application.tak.takapplication.data_access.Config;
import com.application.tak.takapplication.data_access.GetAllClientTasks;
import com.application.tak.takapplication.data_access.GetAllClientTasksByStatus;
import com.application.tak.takapplication.data_access.GetCategories;
import com.application.tak.takapplication.data_list.TaskListNotSelected;
import com.application.tak.takapplication.data_model.Category;
import com.application.tak.takapplication.data_model.Task_V;
import com.application.tak.takapplication.data_model.User;
import com.application.tak.takapplication.interfaces.OnDBRequestFinished;

import java.util.ArrayList;
import java.util.List;

import static com.application.tak.takapplication.R.layout.act_client_task_notselected;


/**
 * An activity representing a list of act_Client_Task_NotSelected. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
    public class actClientTaskNotSelected extends android.support.v4.app.Fragment {

    private RecyclerView recyclerview;
    private GetAllClientTasks clientTask;
    //private User client = new User();
    private  Task_V task;
    private  GetAllClientTasks tasks;
    private GetAllClientTasksByStatus tasksByStatus;
    private Context _ctx;
    private RVAdapterTaskNotSelected adapter;
    private Activity _activity;

    private List<TaskListNotSelected> memberList;

        public actClientTaskNotSelected()
        {
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            memberList = new ArrayList<TaskListNotSelected>();
            _ctx = getContext();
            _activity = getActivity();
            /*User u = new User();
            u.set_Id(2);*/
            tasksByStatus = new GetAllClientTasksByStatus(_ctx,Config.LoggedInClient,1);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

            View view = inflater.inflate(R.layout.act_client_task_notselected, container, false);

            recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview2);
            recyclerview.setLayoutManager(layoutManager);


            tasksByStatus.setDBRequestFinishedListener(new OnDBRequestFinished() {

                @Override
                public void onDBRequestFinished() {
if(tasksByStatus._tasks != null)
{
    Config.ClientTasks = tasksByStatus._tasks;
    for (Task_V task : Config.ClientTasks) {

            TaskListNotSelected member = new TaskListNotSelected(task);
            memberList.add(member);
    }

}
                    adapter = new RVAdapterTaskNotSelected(memberList, _activity);
                    recyclerview.setAdapter(adapter);

                }
            });


          return view;
        }
    }
