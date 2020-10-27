package org.cn.kaito.auth.Service.ServiceImpl;

import org.cn.kaito.auth.DTO.UserDTO;
import org.cn.kaito.auth.Dao.Entity.ProjectEntity;
import org.cn.kaito.auth.Dao.Entity.SubTaskEntity;
import org.cn.kaito.auth.Dao.Entity.UserEntity;
import org.cn.kaito.auth.Dao.Repository.ProjectRepository;
import org.cn.kaito.auth.Dao.Repository.TaskRepository;
import org.cn.kaito.auth.Dao.Repository.TypeRepository;
import org.cn.kaito.auth.Dao.Repository.UserRepository;
import org.cn.kaito.auth.Exception.CustomerException;
import org.cn.kaito.auth.Service.*;
import org.cn.kaito.auth.Utils.StatusEnum;
import org.cn.kaito.auth.Utils.WorkStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    LogService logService;

    @Autowired
    SessionService sessionService;

    @Autowired
    NoticeService noticeService;

    @Autowired
    WorkExecuteService workExecuteService;

    @Autowired
    TypeRepository typeRepository;

    @Override
    public void execute(String uid, String taskID) throws CustomerException, IOException {
        //执行的话 就只需要写入就好了 别的都不需要
        SubTaskEntity subTaskEntity = taskRepository.findSubTaskEntityByTaskID(taskID)
                                    .orElseThrow(()->new CustomerException(StatusEnum.CANT_FIND_USER));
        UserEntity userEntity = userRepository.getUserEntityByUserID(uid)
                                .orElseThrow(()->new CustomerException(StatusEnum.CANT_FIND_USER));
        ProjectEntity projectEntity = projectRepository.findById(subTaskEntity.getProjectID())
                                .orElseThrow(()->new CustomerException(StatusEnum.DONT_HAVE_PROJECT));
        String type = typeRepository.getTypeByID(subTaskEntity.getTypeID());
        if (userEntity.getRoleID()!=subTaskEntity.getTypeID()){
            throw new CustomerException(StatusEnum.USER_CANT_WORK);
        }else {
            if (!subTaskEntity.getStatus().equals(WorkStatus.DOING.getName())){
                throw new CustomerException(StatusEnum.WORK_CANT_BE_DONE);
            }
            workExecuteService.save(projectEntity.getProjectName(),userEntity.getUserName(),
                                    userEntity.getUserID(),type);
            subTaskEntity.setStatus(WorkStatus.SAVE.getName());
            taskRepository.save(subTaskEntity);
            logService.saveLog(uid,projectEntity.getProjectID(),"执行并保存"+type+"类任务");
            }
    }

    @Override
    public void submit(String uid, String taskID) throws CustomerException, IOException{
        SubTaskEntity subTaskEntity = taskRepository.findSubTaskEntityByTaskID(taskID)
                .orElseThrow(()->new CustomerException(StatusEnum.CANT_FIND_USER));
        UserEntity userEntity = userRepository.getUserEntityByUserID(uid)
                .orElseThrow(()->new CustomerException(StatusEnum.CANT_FIND_USER));
        ProjectEntity projectEntity = projectRepository.findById(subTaskEntity.getProjectID())
                .orElseThrow(()->new CustomerException(StatusEnum.DONT_HAVE_PROJECT));
        String type = typeRepository.getTypeByID(subTaskEntity.getTypeID());
        if (userEntity.getRoleID()!=subTaskEntity.getTypeID()){
            throw new CustomerException(StatusEnum.USER_CANT_WORK);
        }else {
            if (!subTaskEntity.getStatus().equals(WorkStatus.SAVE.getName())){
                throw new CustomerException(StatusEnum.WORK_CANT_BE_DONE);
            }

            //提交任务
            workExecuteService.commit(projectEntity.getProjectName());
            //更新任务状态
            subTaskEntity.setStatus(WorkStatus.DONE.getName());
            taskRepository.save(subTaskEntity);
            //通知下一个人继续执行
            Optional<SubTaskEntity> nextTaskOp = taskRepository.findByProjectIDAndTaskPosition(
                    projectEntity.getProjectID(),subTaskEntity.getTaskPosition()+1);
            if (nextTaskOp.isPresent()){
                SubTaskEntity nextTask = nextTaskOp.get();
                nextTask.setStatus(WorkStatus.DOING.getName());
                taskRepository.save(nextTask);
                UserDTO userDTO = userRepository.getUserDTOsByID(nextTask.getExecutor())
                        .orElseThrow(()->new CustomerException(StatusEnum.CANT_FIND_USER));
                sessionService.sendMessage(userDTO.getUserID(),"您的任务准备就绪啦");
                noticeService.saveTaskNotice(uid,projectEntity.getProjectID(),projectEntity.getProjectName());
            }
        }
    }
}
