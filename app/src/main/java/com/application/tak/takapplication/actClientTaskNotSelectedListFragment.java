package com.application.tak.takapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * An activity representing a list of act_Client_Task_NotSelected. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link actClientTaskNotSelectedDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class actClientTaskNotSelectedListFragment extends android.support.v4.app.Fragment {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.act_client_task_notselected_list, container, false);
        return v;
    }
}
