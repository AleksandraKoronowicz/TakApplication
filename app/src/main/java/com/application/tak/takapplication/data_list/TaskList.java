package com.application.tak.takapplication.data_list;

import com.application.tak.takapplication.data_model.Task_V;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Aleksandra on 16.07.2017.
 */
public class TaskList {

    public Task_V tsk;
    public int id;
    public String executorSchool;
    public String dataTask;
    public String dataTime;
    public String executorName;
    public String executorClass;
    public String executorPhone;

    public TaskList(Task_V sup)//String school, String dataTask, String dataTime, String executorName, String executorClass, String executorPhone)
    {
        tsk = sup;
        this.dataTask = DateFormat.getDateInstance().format(tsk.get_TimeFrom().getTime()).toString();
        this.executorSchool = tsk.get_ExecutorSchoolName();
        this.dataTime = getCzas();
        this.executorPhone = tsk.get_ExecutorPhoneNo();
        this.executorName = tsk.get_ExecutorFName() + " " + tsk.get_ExecutorLName();
        this.executorClass = "klasa: " +  tsk.get_ExecutorClassName();

    }
    public  int getId(){return this.id;}
    public String getExecutorSchool()
    {
        return this.executorSchool;
    }
    public String getData()
    {
        return this.dataTask;
    }
    public String getTime()
    {
        return this.dataTime;
    }
    public String getExecutorName()
    {
        return this.executorName;
    }
    public String getExecutorPhone () {return  this.executorPhone; }
    public String getExecutorClass()
    {
        return this.executorClass;
    }


    public void setExecutorSchool(String title) {
        this.executorSchool = executorSchool;
    }
    public void setDate(String datetask) {
        this.dataTask = dataTask;
    }
    public void settaskTime(String dataTime) {this.dataTask = dataTime;}
    public void setExecutorName(String executorName) {this.executorName = executorName;}
    public void setExecutorPhone(String executorPhone) {this.executorPhone = executorPhone;}
    public void setExecutorClass(String executorClass) {this.executorClass = executorClass;}
    public void setId(int id) {this.id = id;}


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

        String czas =  calendar.get(Calendar.HOUR) + ":" + String.format("%02d", calendar.get(Calendar.MINUTE))   + " do " + calendar2.get(Calendar.HOUR) + ":" +  String.format("%02d", calendar2.get(Calendar.MINUTE)) ;
        return  czas;
    }

}