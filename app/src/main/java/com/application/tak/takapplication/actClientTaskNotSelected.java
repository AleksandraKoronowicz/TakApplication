package com.application.tak.takapplication;

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
import com.application.tak.takapplication.data_list.TaskListNotSelected;

import java.util.ArrayList;
import java.util.List;

import static com.application.tak.takapplication.R.layout.act_client_task_notselected;


/**
 * An activity representing a list of act_Client_Task_NotSelected. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link actClientTaskNotSelectedDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
    public class actClientTaskNotSelected extends android.support.v4.app.Fragment{

    private RecyclerView recyclerview;
    private List<TaskListNotSelected> memberList;

        public actClientTaskNotSelected()
        {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            //  return inflater.inflate(R.layout.fragment_home, container, false);

            View view = inflater.inflate(R.layout.act_client_task_notselected, container, false);
            // Inflate the layout for this fragment

            String[] awayStrings = {
                    "\nWyrzuc smieci,\n  kiedy: 2017-07-07, \n  o której godzinie: 16:00-17:00\n",
                    "\nWyrzuc smieci,\n  kiedy: 2017-07-07, \n  o której godzinie: 16:00-17:00\n",
                    "\nWyrzuc smieci,\n  kiedy: 2017-07-07, \n  o której godzinie: 16:00-17:00\n",
                    "cos2",
                    "cos",
                    "cos2",
                    "cos",
                    "cos2",
                    "cos"

            };
            memberList = new ArrayList<TaskListNotSelected>();
            TaskListNotSelected member = new TaskListNotSelected("Wyrzuć śmieci", "25 lipca", "13:00 do 16:00","Gliwice ul....");
            memberList.add(member);
            TaskListNotSelected member2 = new TaskListNotSelected("Wyrzuć śmieci", "25 lipca", "13:00 do 16:00","Gliwice ul....");
            memberList.add(member2);
            TaskListNotSelected member3 = new TaskListNotSelected("Wyrzuć śmieci", "25 lipca", "13:00 do 16:00","Gliwice ul....");
            memberList.add(member3);

            recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview2);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerview.setLayoutManager(layoutManager);


            RVAdapterTaskNotSelected adapter = new RVAdapterTaskNotSelected(memberList, getActivity());
            recyclerview.setAdapter(adapter);
          //  ListView listViewv = (ListView) view.findViewById(R.id.ListView3);

            //ArrayAdapter<String> lva = new ArrayAdapter<String>(
              //      getActivity(), android.R.layout.select_dialog_item, awayStrings);
           // listViewv.setAdapter(lva);

            return view;
        }
    }
