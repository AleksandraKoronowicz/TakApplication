package com.application.tak.takapplication.data_list;

/**
 * Created by Aleksandra on 16.07.2017.
 */
public class AllTaskListStudent {

    public String title;
    public String dataTask;
    public String dataTime;
    public String clientName;
    public String taskPlace;

    public AllTaskListStudent(String title, String dataTask, String dataTime, String clientName,  String taskPlace)
    {
        this.dataTask = dataTask;
        this.title = title;
        this.dataTime = dataTime;
        this.clientName = clientName;
        this.taskPlace = taskPlace;
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
    public String getTaskPlace()
    {
        return this.taskPlace;
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