package com.application.tak.takapplication.student.task;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.application.tak.takapplication.R;
import com.application.tak.takapplication.actStudentMainAllTaskListActivity;
import com.application.tak.takapplication.adapters.RVAdapter;
import com.application.tak.takapplication.adapters.RVAdapterAllTaskStudent;
import com.application.tak.takapplication.data_list.AllTaskListStudent;
import com.application.tak.takapplication.data_list.TaskList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksandra on 04.09.2017.
 */
public class actStudentAllTask extends Fragment {


    private RecyclerView recyclerview;
    private List<AllTaskListStudent> memberList;
    private FloatingActionButton fab;
    public actStudentAllTask()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //  return inflater.inflate(R.layout.fragment_home, container, false);

        View view = inflater.inflate(R.layout.act_student_alltask, container, false);
        // Inflate the layout for this fragment


        //  ListView listViewv = (ListView) view.findViewById(R.id.ListView);
        // ArrayList<TaskListToDo> tasklist = new ArrayList<TaskListToDo>();

        // tasklist.add(new  TaskListToDo("Wyrzuc smieci","2017-05-05","o 16:00 do 17:00"));
        // tasklist.add(new  TaskListToDo("Wyrzuc smieci","2017-05-05","o 16:00 do 17:00"));

        //     ArrayAdapter<String> lva = new ArrayAdapter<String>(
        //        getActivity(), android.R.layout.select_dialog_item, awayStrings);
        //  listViewv.setAdapter(lva);

        memberList = new ArrayList<AllTaskListStudent>();
        AllTaskListStudent member = new AllTaskListStudent("Wyrzuć śmieci", "25 lipca", "13:00 d0 16:00", "Magłorzata Kicha", "Gliwice, Ostrudźka 43");
        memberList.add(member);
        AllTaskListStudent member2 = new AllTaskListStudent("Wyrzuć śmieci", "25 lipca", "13:00 d0 16:00", "Magłorzata Kicha", "Gliwice, Ostrudźka 43");
        memberList.add(member2);
        AllTaskListStudent member3 = new AllTaskListStudent("Wyrzuć śmieci", "25 lipca", "13:00 d0 16:00", "Magłorzata Kicha", "Gliwice, Ostrudźka 43");
        memberList.add(member3);

        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview_alltask);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(layoutManager);


        RVAdapterAllTaskStudent adapter = new RVAdapterAllTaskStudent(memberList, getActivity());
        recyclerview.setAdapter(adapter);




        return view;
    }

   // public void OnClickCardView (View v)
  //  {
   //     fab = (FloatingActionButton) v.findViewById(R.id.floatingActBtnYesAllTask);
   //     fab.hide();
   // }

}
