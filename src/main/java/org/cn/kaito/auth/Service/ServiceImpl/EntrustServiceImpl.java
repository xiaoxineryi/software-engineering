package org.cn.kaito.auth.Service.ServiceImpl;

import org.cn.kaito.auth.Dao.Entity.EntrustEntity;
import org.cn.kaito.auth.Dao.Entity.ProjectEntity;
import org.cn.kaito.auth.Dao.Entity.SubTaskEntity;
import org.cn.kaito.auth.Dao.Repository.EntrustRepository;
import org.cn.kaito.auth.Dao.Repository.ProjectRepository;
import org.cn.kaito.auth.Dao.Repository.TaskRepository;
import org.cn.kaito.auth.Exception.CustomerException;
import org.cn.kaito.auth.Service.*;
import org.cn.kaito.auth.Utils.StatusEnum;
import org.cn.kaito.auth.Utils.WorkStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EntrustServiceImpl implements EntrustService {

    @Autowired
    EntrustRepository entrustRepository;


    @Autowired
    SessionService sessionService;

    @Autowired
    LogService logService;
    @Autowired
    NoticeService noticeService;
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    ProjectRepository projectRepository;
    @Override
    public void takeBackEntrust(int entrustID) {
        Optional<EntrustEntity> entrustEntity =  entrustRepository.findEntrustEntityByEntrustID(entrustID);
        if (entrustEntity.isPresent()){
            EntrustEntity entrust = entrustEntity.get();
            SubTaskEntity subTaskEntity = taskRepository.findSubTaskEntityByTaskID(entrust.getSubTask()).get();
            ProjectEntity projectEntity = projectRepository.findById(subTaskEntity.getProjectID()).get();
            if (!entrust.getStatus().equals(WorkStatus.DONE.getName())){
                //如果没有完成，就设置为未完成
                entrust.setStatus(WorkStatus.FAILED.getName());
                entrustRepository.save(entrust);
                sessionService.sendMessage(entrust.getEntrustWorker(),"未在规定时间内完成，任务被收回");
                logService.saveLog(entrust.getEntrustWorker(),subTaskEntity.getProjectID(),"委托被回收");
                noticeService.saveDelegateTakenNotice(entrust.getEntrustWorker(), subTaskEntity.getProjectID(),projectEntity.getProjectName(),entrust.getSubTask());
                subTaskEntity.setStatus(WorkStatus.DOING.getName());
                taskRepository.save(subTaskEntity);
            }
        }
        System.out.println("已经收回权限");

    }
}
