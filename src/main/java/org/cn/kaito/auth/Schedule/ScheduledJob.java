package org.cn.kaito.auth.Schedule;

import org.cn.kaito.auth.Service.EntrustService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScheduledJob implements Job {

    private int entrustID;

    public void setEntrustID(int id){entrustID=id;}

    @Autowired
    EntrustService entrustService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        entrustService.takeBackEntrust(entrustID);
    }
}