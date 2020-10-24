package org.cn.kaito.auth.Schedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScheduledJob implements Job {

    private String name;
    private String date;
    public void setName(String name) {
        this.name = name;
    }
    public void setDate(String date){
        this.date = date;
    }
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        System.out.println("CRON ----> schedule job1 is running ... + " + name + "  ---->  " + dateFormat.format(new Date()));
        System.out.println("CRON ----> schedule job1 is running ... + " + date + "  ---->  " + dateFormat.format(new Date()));

    }
}