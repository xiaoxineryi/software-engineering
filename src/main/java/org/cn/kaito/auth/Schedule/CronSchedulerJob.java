package org.cn.kaito.auth.Schedule;

import org.cn.kaito.auth.Dao.Entity.EntrustEntity;
import org.cn.kaito.auth.Dao.Repository.EntrustRepository;
import org.cn.kaito.auth.Utils.DateCronUtil;
import org.cn.kaito.auth.Utils.EntrustEnum;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class CronSchedulerJob {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    private EntrustRepository entrustRepository;

    public void loadEntrustWork() throws SchedulerException{
        loadWork();
    }

    private void loadWork() {
        List<EntrustEntity> entrustEntityList = entrustRepository.findAll();
        Long tempDate = new Date().getTime();
        for (EntrustEntity entrustEntity:entrustEntityList){
            // 如果截止时间还没到，而且还没有被收回或完成，就加载.
            if (entrustEntity.getEntrustEndDate().getTime()>tempDate
                && hasPermissionToDo(entrustEntity.getStatus())){
                addCronWork(entrustEntity);
            }
        }
    }

    private void addCronWork(EntrustEntity entrustEntity) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobDetail jobDetail = JobBuilder.newJob(ScheduledJob.class) .withIdentity(String.valueOf(entrustEntity.getEntrustID())).build();
        System.out.println("检测到任务"+entrustEntity.getEntrustID());
        String cronTime = DateCronUtil.getCron(entrustEntity.getEntrustEndDate());

        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronTime);
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(String.valueOf(entrustEntity.getEntrustID()))
                .usingJobData("entrustID",entrustEntity.getEntrustID())
                .withSchedule(scheduleBuilder).build();
        try {
            scheduler.scheduleJob(jobDetail,cronTrigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private boolean hasPermissionToDo(String status) {
        if (status.equals(EntrustEnum.BETAKEN.getName())||status.equals(EntrustEnum.COMMIT.getName())){
            return false;
        }else{
            return true;
        }
    }
}