package com.application.tak.takapplication;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.application.tak.takapplication.adapters.RVAdapter;
import com.application.tak.takapplication.data_access.Config;
import com.application.tak.takapplication.data_access.GetAllClientTasks;
import com.application.tak.takapplication.data_list.TaskList;
import com.application.tak.takapplication.data_list.TaskListNotSelected;
import com.application.tak.takapplication.data_model.Task_V;
import com.application.tak.takapplication.data_model.User;
import com.application.tak.takapplication.interfaces.OnDBRequestFinished;


import java.util.ArrayList;
import java.util.List;


/**
 * An activity representing a list of act_Client_Task_NotSelected. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
    public class actClientTaskToDo extends android.support.v4.app.Fragment{

    private RecyclerView recyclerview;
    private List<TaskList> memberList;

    User client = new User();
    Task_V task;
    GetAllClientTasks tasks;

        public actClientTaskToDo()
        {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {


            View view = inflater.inflate(R.layout.act_client_task_todo, container, false);
            memberList = new ArrayList<TaskList>();
            recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerview.setLayoutManager(layoutManager);


            User u = new User();
            u.set_Id(1);
            tasks = new GetAllClientTasks(getActivity(),u);
            tasks.setDBRequestFinishedListener(new OnDBRequestFinished() {


                @Override
                public void onDBRequestFinished() {
                    if (tasks._tasks != null) {
                        Config.ClientTasks = tasks._tasks;
                        for (Task_V task : Config.ClientTasks) {
//to dobre
                            String x = task.get_CategoryName();
                            TaskList member = new TaskList(task,"klasa:2f");

                            memberList.add(member);
                        }

                        RVAdapter adapter = new RVAdapter(memberList, getActivity());
                        recyclerview.setAdapter(adapter);
                    }
                }
            });
                    return view;
    /*        memberList = new ArrayList<TaskList>();
            TaskList member = new TaskList("Szkoła im. Fryderyka Chopina, Niechałkowice", "Sie 31", "13:00-16:00", "Magłorzata Kicha", "Klasa: 3f","504 444 213");
            memberList.add(member);
            TaskList member4 = new TaskList("Szkoła im. Fryderyka Chopina, Niechałkowice", "Sie 31", "13:00-16:00", "Aleksandra Koronowicz", "Klasa: 3f","504 444 213");
            memberList.add(member4);
*/




            }
}

