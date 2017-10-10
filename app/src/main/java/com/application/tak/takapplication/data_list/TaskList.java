package com.application.tak.takapplication.data_list;

/**
 * Created by Aleksandra on 16.07.2017.
 */
public class TaskList {

    public String executorSchool;
    public String dataTask;
    public String dataTime;
    public String executorName;
    public String executorClass;
    public String executorPhone;

    public TaskList(String school, String dataTask, String dataTime, String executorName, String executorClass, String executorPhone)
    {
        this.dataTask = dataTask;
        this.executorSchool = school;
        this.dataTime = dataTime;
        this.executorName = executorName;
        this.executorClass = executorClass;
        this.executorPhone = executorPhone;
    }

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
    public void setExecutorClass(String taskPlace) {this.executorClass = executorClass;}

}