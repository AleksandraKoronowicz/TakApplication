package com.application.tak.takapplication.data_list;

import com.application.tak.takapplication.data_model.Task_V;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Aleksandra on 16.07.2017.
 */
public class AllTaskListStudent {

    public Task_V tsk;
    public int id;
    public String title;
    public String dataTask;
    public String dataTime;
    public String clientName;
    public String taskPlace;
    public String road;
    public String city;
    public String homeNumber;
    public String posteCode;


    public AllTaskListStudent(Task_V sup)//String title, String dataTask, String dataTime, String clientName,  String taskPlace)
    {
        tsk = sup;

        this.title = tsk.get_CategoryName();
        this.dataTime = getCzas();
        this.dataTask = DateFormat.getDateInstance().format(tsk.get_TimeFrom().getTime()).toString();
        this.clientName = tsk.get_CreatorFName() + " " + tsk.get_CreatorLName();
        this.taskPlace = getTaskPlace();

        this.road = tsk.get_CreatorRoad();
        this.city = tsk.get_CreatorCity();
        this.homeNumber = tsk.get_CreatorRoadNo();
        this.posteCode = tsk.get_CreatorPostCode();
    }

    public String getCzas()
    {
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(tsk.get_TimeFrom());   // assigns calendar to given date
        calendar.get(Calendar.MINUTE); // gets hour in 24h format
        calendar.get(Calendar.HOUR_OF_DAY);        // gets hour in 12h format

        Calendar calendar2 = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar2.setTime(tsk.get_TimeTo());   // assigns calendar to given date
        calendar2.get(Calendar.MINUTE); // gets hour in 24h format
        calendar2.get(Calendar.HOUR);        // gets hour in 12h format

        String czas =  calendar.get(Calendar.HOUR) + ":" +  calendar.get(Calendar.MINUTE) + " do " + calendar2.get(Calendar.HOUR) + ":" +  calendar2.get(Calendar.MINUTE);
        return  czas;
    }

    public String getTaskPlace()
    {
        taskPlace = "ul. " + road + " " + homeNumber + ", " + city + " " + posteCode;
        return this.taskPlace;
    }


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
    public String getclientName()
    {
        return this.clientName;
    }


    public void settitle(String title) {
        this.title = title;
    }
    public void setDate(String datetask) {
        this.dataTask = dataTask;
    }
    public void settaskTime(String dataTime) {this.dataTask = dataTime;}
    public void setClientName(String clientName) {this.clientName = clientName;}
    public void setTaskPlace(String taskPlace) {this.taskPlace = taskPlace;}

}