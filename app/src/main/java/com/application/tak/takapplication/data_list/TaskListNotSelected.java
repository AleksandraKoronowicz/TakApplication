package com.application.tak.takapplication.data_list;

import android.support.design.widget.FloatingActionButton;
import android.widget.ImageButton;
import com.application.tak.takapplication.data_model.Task_V;

import java.text.DateFormat;

/**
 * Created by Aleksandra on 16.07.2017.
 */
public class TaskListNotSelected //extends Task_V

{
    public Task_V tsk;
    public int id;
    public String title;
    public String dataTask;
    public String dataTime;
    public String taskPlace;
    public String road;
    public String city;
    public String homeNumber;
    public String posteCode;

    public TaskListNotSelected(Task_V sup)
    {
        tsk = sup;
        //this.set_CategoryId(sup.get_CategoryId());

        this.dataTask = this.getData();
        this.title = tsk.get_CategoryName();
        this.dataTime=   DateFormat.getDateInstance().format(tsk.get_TimeFrom().getTime()).toString() + " do " + DateFormat.getDateInstance().format(tsk.get_TimeTo().getTime()).toString();
        this.road = tsk.get_CreatorRoad();
        this.city = tsk.get_CreatorCity();
        this.homeNumber = tsk.get_CreatorRoadNo();
        this.posteCode = tsk.get_CreatorPostCode();
    }

    public  int getId(){return this.id;}
    public String getTitle()
    {
        return this.title;
    }
    public String getData()
    {
        return this.dataTask;
    }
    public String getTime()
    {
        return this.dataTime;
    }
    public String getTaskPlace()
    {
        taskPlace = "ul. " + road + " " + homeNumber + ", " + city + " " + posteCode;
        return this.taskPlace;
    }


    public void setId(int id) {this.id = id;}
    public void settitle(String title) {
        this.title = title;
    }
    public void setDate(String datetask) {
        this.dataTask = dataTask;
    }
    public void settaskTime(String dataTime) {this.dataTask = dataTime;}
    public void setTaskPlace(String taskPlace) {this.taskPlace = taskPlace;}

}
