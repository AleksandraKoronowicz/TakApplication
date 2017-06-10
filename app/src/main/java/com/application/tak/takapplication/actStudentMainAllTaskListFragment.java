package com.application.tak.takapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Aleksandra on 07.05.2017.
 */
public class actStudentMainAllTaskListFragment extends Fragment {

@Nullable
@Override
public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.act_student_main_task_list, container, false);
        return v;
        }
        }
