package com.application.tak.takapplication.data_list;

import java.util.Date;

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

    public MyTaskListStudent(String mytitletask, String dataTask, String dataTime, String myclientName, String myclientPhone, String mytaskPlace)
    {
        this.dataTask = dataTask;
        this.mytitletask = mytitletask;
        this.dataTime = dataTime;
        this.myclientName = myclientName;
        this.myclientPhone = myclientPhone;
        this.mytaskPlace = mytaskPlace;
    }

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