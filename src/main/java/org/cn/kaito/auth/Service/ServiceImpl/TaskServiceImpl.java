package org.cn.kaito.auth.Service.ServiceImpl;

import org.cn.kaito.auth.DTO.EntrustTaskDTO;
import org.cn.kaito.auth.DTO.OwnerDTO;
import org.cn.kaito.auth.DTO.SelfTaskDTO;
import org.cn.kaito.auth.DTO.UserDTO;
import org.cn.kaito.auth.Dao.Entity.EntrustEntity;
import org.cn.kaito.auth.Dao.Entity.ProjectEntity;
import org.cn.kaito.auth.Dao.Entity.SubTaskEntity;
import org.cn.kaito.auth.Dao.Entity.UserEntity;
import org.cn.kaito.auth.Dao.Repository.*;
import org.cn.kaito.auth.Exception.CustomerException;
import org.cn.kaito.auth.Service.*;
import org.cn.kaito.auth.Utils.StatusEnum;
import org.cn.kaito.auth.Utils.WorkStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    EntrustRepository entrustRepository;

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
            logService.saveLog(uid,projectEntity.getProjectID(),"提交"+type+"类任务");

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
                noticeService.saveTaskNotice(userDTO.getUserID(),projectEntity.getProjectID(),projectEntity.getProjectName());
            }
        }
    }

    @Override
    public void undo(String uid, String taskID) throws CustomerException, IOException {
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
                throw new CustomerException(StatusEnum.WORK_CANT_BE_UNDO);
            }
            workExecuteService.undo(projectEntity.getProjectName());
            subTaskEntity.setStatus(WorkStatus.DOING.getName());
            taskRepository.save(subTaskEntity);
            logService.saveLog(uid,projectEntity.getProjectID(),"撤销执行"+type+"类任务");
        }
    }

    @Override
    public List<SelfTaskDTO> getSelfTasks(String uid) throws CustomerException {
        List<SubTaskEntity> tasks = taskRepository.findSubTaskEntitiesByExecutor(uid);
        List<SelfTaskDTO> selfTaskDTOS = new ArrayList<>();
        for (SubTaskEntity task : tasks){
            SelfTaskDTO selfTaskDTO = new SelfTaskDTO();
            selfTaskDTO.setTaskID(task.getTaskID());
            ProjectEntity projectEntity = projectRepository.findById(task.getProjectID())
                                    .orElseThrow(()->new CustomerException(StatusEnum.DONT_HAVE_PROJECT));
            selfTaskDTO.setProjectID(projectEntity.getProjectID());
            selfTaskDTO.setProjectName(projectEntity.getProjectName());
            String status = task.getStatus();
            //如果是代理状态，就返回代理人
            if (status.equals(WorkStatus.DELEGATE.getName())){
                EntrustEntity entrustEntity = entrustRepository.findEntrustEntityBySubTask(task.getTaskID())
                                        .orElseThrow(()->new CustomerException(StatusEnum.DELEGATE_NOT_FOUND));
                UserDTO userDTO= userRepository.getUserDTOsByID(entrustEntity.getEntrustWorker())
                                        .orElseThrow(()->new CustomerException(StatusEnum.CANT_FIND_USER));
                OwnerDTO ownerDTO = new OwnerDTO();
                ownerDTO.setId(entrustEntity.getEntrustWorker());
                ownerDTO.setName(userDTO.getUsername());
                selfTaskDTO.setConsignee(ownerDTO);
            }
            selfTaskDTO.setWorkStatus(task.getStatus());
            //如果是完成状态，就查一下有没有委托，有的话看委托状态。
            if (status.equals(WorkStatus.DONE.getName())){
                UserDTO userDTO = null;
                Optional<EntrustEntity> entrustEntity = entrustRepository.findEntrustEntityBySubTask(task.getTaskID());
                if (entrustEntity.isPresent()){
                    EntrustEntity entrust = entrustEntity.get();
                    if (entrust.getStatus().equals(WorkStatus.DONE.getName())){
                         userDTO= userRepository.getUserDTOsByID(entrust.getEntrustWorker())
                                .orElseThrow(()->new CustomerException(StatusEnum.CANT_FIND_USER));
                    }else {
                        userDTO= userRepository.getUserDTOsByID(task.getExecutor())
                                .orElseThrow(()->new CustomerException(StatusEnum.CANT_FIND_USER));
                    }
                }else {
                   userDTO= userRepository.getUserDTOsByID(task.getExecutor())
                            .orElseThrow(()->new CustomerException(StatusEnum.CANT_FIND_USER));
                }
                OwnerDTO ownerDTO = new OwnerDTO();
                ownerDTO.setId(userDTO.getUserID());
                ownerDTO.setName(userDTO.getUsername());
                selfTaskDTO.setExecutor(ownerDTO);
            }
            selfTaskDTOS.add(selfTaskDTO);
        }
        return selfTaskDTOS;
    }

    @Override
    public List<EntrustTaskDTO> getEntrustTasks(String uid) throws CustomerException {
       List<EntrustTaskDTO> entrustTaskDTOS = new ArrayList<>();
       List<EntrustEntity> entrustEntities = entrustRepository.findAllByEntrustWorker(uid);
       for (EntrustEntity entrustEntity:entrustEntities){
           EntrustTaskDTO entrustTaskDTO = new EntrustTaskDTO();
           SubTaskEntity subTaskEntity = taskRepository.findSubTaskEntityByTaskID(entrustEntity.getSubTask())
                                .orElseThrow(()->new CustomerException(StatusEnum.TASK_NOT_DELEGATE));
           ProjectEntity projectEntity = projectRepository.findById(subTaskEntity.getProjectID())
                                .orElseThrow(()->new CustomerException(StatusEnum.DONT_HAVE_PROJECT));
           entrustTaskDTO.setProjectID(projectEntity.getProjectID());
           entrustTaskDTO.setProjectName(projectEntity.getProjectName());
           entrustTaskDTO.setTaskID(subTaskEntity.getTaskID());
           entrustTaskDTO.setWorkStatus(subTaskEntity.getStatus());
           UserDTO userDTO= userRepository.getUserDTOsByID(entrustEntity.getEntrustWorker())
                   .orElseThrow(()->new CustomerException(StatusEnum.CANT_FIND_USER));
           OwnerDTO ownerDTO = new OwnerDTO();
           ownerDTO.setId(userDTO.getUserID());
           ownerDTO.setName(userDTO.getUsername());
           entrustTaskDTO.setConsiger(ownerDTO);

           entrustTaskDTOS.add(entrustTaskDTO);
       }
       return entrustTaskDTOS;
    }
}
