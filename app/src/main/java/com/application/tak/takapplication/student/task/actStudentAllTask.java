package com.application.tak.takapplication.student.task;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import com.application.tak.takapplication.R;
import com.application.tak.takapplication.actStudentMainAllTaskListActivity;
import com.application.tak.takapplication.adapters.RVAdapter;
import com.application.tak.takapplication.adapters.RVAdapterAllTaskStudent;
import com.application.tak.takapplication.data_access.Config;
import com.application.tak.takapplication.data_access.GetAllStudentTasks;
import com.application.tak.takapplication.data_list.AllTaskListStudent;
import com.application.tak.takapplication.data_list.TaskList;
import com.application.tak.takapplication.data_list.TaskListNotSelected;
import com.application.tak.takapplication.data_model.Task_V;
import com.application.tak.takapplication.data_model.User;
import com.application.tak.takapplication.interfaces.OnDBRequestFinished;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksandra on 04.09.2017.
 */
public class actStudentAllTask extends Fragment {


    private RecyclerView recyclerview;
    private List<AllTaskListStudent> memberList;

    User client = new User();
    Task_V task;
   GetAllStudentTasks tasks;

    public actStudentAllTask()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.act_student_alltask, container, false);

        memberList = new ArrayList<AllTaskListStudent>();
        Button google_map = (Button) view.findViewById(R.id.btnAdresMap);

        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview_alltask);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(layoutManager);

        User u = new User();
        u.set_Id(1);
        //tasks = new GetAllClientTasks(getActivity(),u);
        tasks = new GetAllStudentTasks(getContext(),u);

        tasks.setDBRequestFinishedListener(new OnDBRequestFinished() {

                                               @Override
                                               public void onDBRequestFinished() {
                                                   if (tasks._tasks != null) {
                                                       Config.ClientTasks = tasks._tasks;
                                                       for (Task_V task : Config.ClientTasks) {
//if (task.get_StatusName() == "Pending") {
                                                           AllTaskListStudent member = new AllTaskListStudent(task);
                                                             memberList.add(member);

//}}
                                                       }
                                                   }
                                                   RVAdapterAllTaskStudent adapter = new RVAdapterAllTaskStudent(memberList, getActivity());
                                                   recyclerview.setAdapter(adapter);
                                               }

                                           });

        return view;
    }


}
