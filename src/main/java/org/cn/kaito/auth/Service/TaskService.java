package org.cn.kaito.auth.Service;

import org.cn.kaito.auth.Controller.Request.DelegateRequest;
import org.cn.kaito.auth.DTO.EntrustTaskDTO;
import org.cn.kaito.auth.DTO.SelfTaskDTO;
import org.cn.kaito.auth.Exception.CustomerException;
import org.quartz.SchedulerException;

import java.io.IOException;
import java.util.List;

public interface TaskService {
    void execute(String uid, String taskID) throws CustomerException, IOException;

    void submit(String uid,String taskID) throws CustomerException, IOException;

    void undo(String uid, String taskID) throws CustomerException, IOException;

    List<SelfTaskDTO> getSelfTasks(String uid) throws CustomerException;

    List<EntrustTaskDTO> getEntrustTasks(String uid) throws CustomerException;

    void delegate(String uid,DelegateRequest delegateRequest) throws CustomerException;

    void delegateDelete(String uid,String taskID) throws CustomerException, SchedulerException;
}
