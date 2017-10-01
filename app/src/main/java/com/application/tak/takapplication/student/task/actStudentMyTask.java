package com.application.tak.takapplication.student.task;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.application.tak.takapplication.R;
import com.application.tak.takapplication.adapters.RVAdapter;
import com.application.tak.takapplication.adapters.RVAdapterMyTaskStudent;
import com.application.tak.takapplication.data_list.MyTaskListStudent;
import com.application.tak.takapplication.data_list.TaskList;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Aleksandra on 04.09.2017.
 */
public class actStudentMyTask extends Fragment {

    private DateFormat formDatetime = DateFormat.getDateTimeInstance();
    private RecyclerView recyclerview;
    private List<MyTaskListStudent> memberList;
    public actStudentMyTask()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.act_student_mytask, container, false);

        memberList = new ArrayList<MyTaskListStudent>();
        MyTaskListStudent member = new MyTaskListStudent("Wyrzuć śmieci", "25/08/2017", "13:00 d0 16:00", "Magłorzata Kicha", "504 444 213", "Gliwice, Ostrudźka 43");
        memberList.add(member);
        MyTaskListStudent member2 = new MyTaskListStudent("Wyrzuć śmieci", "25/08/2017", "13:00 d0 16:00", "Magłorzata Kicha", "504 444 213", "Gliwice, Ostrudźka 43");
        memberList.add(member2);
        MyTaskListStudent member3 = new MyTaskListStudent("Wyrzuć śmieci", "25/08/2017", "13:00 d0 16:00", "Magłorzata Kicha", "504 444 213", "Gliwice, Ostrudźka 43");
        memberList.add(member3);
        MyTaskListStudent member4 = new MyTaskListStudent("Wyrzuć śmieci", "25/08/2017", "13:00 d0 16:00", "Magłorzata Kicha", "504 444 213", "Gliwice, Ostrudźka 43");
        memberList.add(member4);


    //    try {
     //      updateTextLabel(view, memberList );
    //    } catch (ParseException e) {
         //   e.printStackTrace();
        //}


        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview_mytask);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(layoutManager);

        RVAdapterMyTaskStudent adapter = new RVAdapterMyTaskStudent(memberList, getActivity());
        recyclerview.setAdapter(adapter);

        return view;
    }

    private void updateTextLabel(View w, List<MyTaskListStudent> list) throws ParseException {
        TextView nameDay = (TextView) w.findViewById(R.id.timepicker);
        String strDateFormat = "EEEE";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);

        for (MyTaskListStudent m : list)
        {
            String string = m.dataTask.toString();
            DateFormat format = new SimpleDateFormat("dd/mm/yyyy");
            Date date1 = format.parse(string);

            //formatting day of week in E format like Sun, Mon etc.
            m.dataTask = sdf.format(date1).toString();
        }

    }

}
