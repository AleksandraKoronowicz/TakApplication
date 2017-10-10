package com.application.tak.takapplication;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.application.tak.takapplication.adapters.RVAdapter;
import com.application.tak.takapplication.data_list.TaskList;


import java.util.ArrayList;
import java.util.List;


/**
 * An activity representing a list of act_Client_Task_NotSelected. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link actClientTaskNotSelectedDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
    public class actClientTaskToDo extends android.support.v4.app.Fragment{

    private RecyclerView recyclerview;
    private List<TaskList> memberList;
        public actClientTaskToDo()
        {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            //  return inflater.inflate(R.layout.fragment_home, container, false);

            View view = inflater.inflate(R.layout.act_client_task_todo, container, false);
            // Inflate the layout for this fragment


          //  ListView listViewv = (ListView) view.findViewById(R.id.ListView);
           // ArrayList<TaskListToDo> tasklist = new ArrayList<TaskListToDo>();

           // tasklist.add(new  TaskListToDo("Wyrzuc smieci","2017-05-05","o 16:00 do 17:00"));
           // tasklist.add(new  TaskListToDo("Wyrzuc smieci","2017-05-05","o 16:00 do 17:00"));

       //     ArrayAdapter<String> lva = new ArrayAdapter<String>(
            //        getActivity(), android.R.layout.select_dialog_item, awayStrings);
          //  listViewv.setAdapter(lva);

            memberList = new ArrayList<TaskList>();
            TaskList member = new TaskList("Szkoła im. Fryderyka Chopina, Niechałkowice", "Sie 31", "13:00-16:00", "Magłorzata Kicha", "504 444 213","Klasa: 3f");
            memberList.add(member);
            TaskList member4 = new TaskList("Szkoła im. Fryderyka Chopina, Niechałkowice", "Sie 31", "13:00-16:00", "Aleksandra Koronowicz", "504 444 213","Klasa: 3f");
            memberList.add(member4);
            TaskList member2 = new TaskList("Wyrzuć śmieci", "25 lipca", "13:00 d0 16:00", "Magłorzata Kicha", "504 444 213","Gliwice, Ostrudźka 43");
            memberList.add(member2);
            TaskList member3 = new TaskList("Wyrzuć śmieci", "25 lipca", "13:00 d0 16:00", "Magłorzata Kicha", "504 444 213","Gliwice, Ostrudźka 43");
            memberList.add(member3);

            recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerview.setLayoutManager(layoutManager);


            RVAdapter adapter = new RVAdapter(memberList, getActivity());
            recyclerview.setAdapter(adapter);


            return view;
        }
    }
