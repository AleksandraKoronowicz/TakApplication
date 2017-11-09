package com.application.tak.takapplication.data_list;

import com.application.tak.takapplication.data_model.Task_V;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Aleksandra on 16.07.2017.
 */
public class MyTaskListStudent {


    public String mytitletask;
    public String dataTask;
    public String dataTime;
    public String myclientName;
    public String myclientPhone;
    public String mytaskPlace;
    public String road;
    public String city;
    public String homeNumber;
    public String posteCode;

    public int creatorid;
    public int categoryid;
    public int isapproved;

    public Task_V tsk;
    public int id;

    public MyTaskListStudent(Task_V sup)//(String mytitletask, String dataTask, String dataTime, String myclientName, String myclientPhone, String mytaskPlace)
    {
         tsk = sup;
this.id = tsk.get_Id();
        this.dataTask =  DateFormat.getDateInstance().format(tsk.get_TimeFrom().getTime()).toString();
        this.mytitletask = tsk.get_CategoryName(); ;
        this.dataTime = getCzas();
        this.myclientName = tsk.get_CreatorFName() + " " + tsk.get_CreatorLName();
        this.myclientPhone = tsk.get_CreatorPhoneNo();
         this.road = tsk.get_CreatorRoad();
        this.city = tsk.get_CreatorCity();
        this.homeNumber = tsk.get_CreatorRoadNo();
        this.posteCode = tsk.get_CreatorPostCode();
        this.mytaskPlace = getTaskPlace();

        this.creatorid = tsk.get_CreatorId();
        this.isapproved = tsk.get_IsApproved();
        this.categoryid = tsk.get_CategoryId();
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
        mytaskPlace = "ul. " + road + " " + homeNumber + ", " + city + " " + posteCode;
        return this.mytaskPlace;
    }

    public int getId(){return  this.id;}
    public String getmytitletask()
    {
        return this.mytitletask;
    }
    public String getData()
    {
        return this.dataTask;
    }
    public String getTime()
    {
        return this.dataTime;
    }
    public String getmyclientName()
    {
        return this.myclientName;
    }
    public String getmyclientPhonee () {return  this.myclientPhone; }
    public String getmytaskPlace()
    {
        return this.mytaskPlace;
    }


    public void setmytitletask(String mytitletask) {
        this.mytitletask = mytitletask;
    }
    public void setDate(String datetask) {
        this.dataTask = dataTask;
    }
    public void settaskTime(String dataTime) {this.dataTask = dataTime;}
    public void setmyclientName(String myclientName) {this.myclientName = myclientName;}
    public void setmyclientPhone(String myclientPhone) {this.myclientPhone = myclientPhone;}
    public void setmytaskPlace(String mytaskPlace) {this.mytaskPlace = mytaskPlace;}

}