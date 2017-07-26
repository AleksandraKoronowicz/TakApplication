package com.application.tak.takapplication.data_list;

/**
 * Created by Aleksandra on 16.07.2017.
 */
public class TaskList {

    public String title;
    public String dataTask;
    public String dataTime;
    public String executorName;
    public String executorPhone;
    public String taskPlace;

    public TaskList(String title, String dataTask, String dataTime, String executorName, String executorPhone, String taskPlace)
    {
        this.dataTask = dataTask;
        this.title = title;
        this.dataTime = dataTime;
        this.executorName = executorName;
        this.executorPhone = executorPhone;
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
    public String getExecutorName()
    {
        return this.executorName;
    }
    public String getExecutorPhone () {return  this.executorPhone; }
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
    public void setExecutorName(String executorName) {this.executorName = executorName;}
    public void setExecutorPhone(String executorPhone) {this.executorPhone = executorPhone;}
    public void setTaskPlace(String taskPlace) {this.taskPlace = taskPlace;}

}