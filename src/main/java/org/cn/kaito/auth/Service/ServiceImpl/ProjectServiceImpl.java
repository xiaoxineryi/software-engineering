package org.cn.kaito.auth.Service.ServiceImpl;

import org.cn.kaito.auth.Controller.Request.CreateProjectRequest;
import org.cn.kaito.auth.Controller.Request.EditProjectRequest;
import org.cn.kaito.auth.Controller.Response.RandomTasksResponse;
import org.cn.kaito.auth.DTO.*;
import org.cn.kaito.auth.Dao.Entity.EntrustEntity;
import org.cn.kaito.auth.Dao.Entity.NoticeEntity;
import org.cn.kaito.auth.Dao.Entity.ProjectEntity;
import org.cn.kaito.auth.Dao.Entity.SubTaskEntity;
import org.cn.kaito.auth.Dao.Repository.*;
import org.cn.kaito.auth.Exception.CustomerException;
import org.cn.kaito.auth.Service.*;
import org.cn.kaito.auth.Utils.ProjectStatus;
import org.cn.kaito.auth.Utils.StatusEnum;
import org.cn.kaito.auth.Utils.WorkStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProjectServiceImpl implements ProjectService {


    @Autowired
    UserService userService;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    EntrustRepository entrustRepository;

    @Autowired
    NoticeService noticeService;

    @Autowired
    NoticeRespository noticeRespository;

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    TypeRepository typeRepository;

    @Autowired
    SessionService sessionService;

    @Autowired
    LogService logService;

    @Autowired
    WorkExecuteService workExecuteService;

    @Override
    public RandomTasksResponse getRandomTasks() throws CustomerException {
        Integer random = new Random().nextInt(5)+1;
        RandomTasksResponse randomTasksResponse  = new RandomTasksResponse();
        for (int i = 0; i < random;i++){
            SimpleTaskDTO simpleTaskDTO = new SimpleTaskDTO();
            long cnt = typeRepository.count();
            Integer typeRandom = new Random().nextInt((int)cnt)+1;
            String type = typeRepository.getTypeByID(typeRandom);

            List<UserDTO> users = userService.getUserList("ROLE_WORKER"+type);
            Integer userRandom= new Random().nextInt(users.size());
            UserDTO userDTO = users.get(userRandom);
            OwnerDTO ownerDTO = new OwnerDTO();
            ownerDTO.setId(userDTO.getUserID());
            ownerDTO.setName(userDTO.getUsername());

            simpleTaskDTO.setOwner(ownerDTO);
            simpleTaskDTO.setType(type);
            simpleTaskDTO.setTypeID(typeRandom);
            randomTasksResponse.getTasks().add(simpleTaskDTO);
        }
        return randomTasksResponse;
    }

    @Override
    @Transactional
    public void createProject(String uid,CreateProjectRequest createProjectRequest) throws CustomerException, IOException {
        /**
         * TODO:
         * 创建项目需要创建项目，然后将子任务变为等待状态，第一个任务变为进行中状态，通知对应的任务的用户进行操作，然后记录一次操作为保存项目，同时新建项目目录与文件。
         */
        ProjectEntity projectEntity = createNewProject(uid,createProjectRequest.getName());
        ProjectEntity project = projectRepository.save(projectEntity);
        SimpleTaskDTO task = saveTasks(project.getProjectID(),createProjectRequest.getTasks());
        sessionService.sendMessage(task.getOwner().getId(),"有新的任务进入进行状态");
        logService.saveLog(uid,project.getProjectID(),"创建");
        noticeService.saveTaskNotice(uid,project.getProjectID(),project.getProjectName());
        workExecuteService.init(project.getProjectName());


    }


    private SimpleTaskDTO saveTasks(String projetcID,List<SimpleTaskDTO> tasks) throws CustomerException {
        /**
         * 如果有子任务序列，那么就返回第一个任务。
         */
        if (tasks.size()==0){
            throw new CustomerException(StatusEnum.CANT_SAVE_EMPTY_TASKS);
        }
        int size = tasks.size();
        int index;
        for (index = 0; index < size ; index ++){
            SimpleTaskDTO task = tasks.get(index);

            SubTaskEntity subTaskEntity = new SubTaskEntity();
            subTaskEntity.setExecutor(task.getOwner().getId());
            subTaskEntity.setProjectID(projetcID);
            subTaskEntity.setTaskPosition(index);
            subTaskEntity.setTypeID(task.getTypeID());
            String id = projetcID+'_'+ (index + 1);
            System.out.println(id);

            subTaskEntity.setTaskID(id);
            if (index == 0){
                subTaskEntity.setStatus("进行中");
            }else{
                subTaskEntity.setStatus("等待中");
            }
            taskRepository.save(subTaskEntity);
        }

        return tasks.get(0);

    }

    @Override
    public List<SimpleProjectDTO> getSimpleProjects(String uid, int page) throws CustomerException {
        UserDTO userDTO = userService.getUserByID(uid);
        Pageable pageable = PageRequest.of(page,10);
        List<SimpleProjectDTO> projects;
        if (userDTO.getType().equals("ROLE_ADMIN")){
            projects = projectRepository.getAll(pageable);
        }else {
            projects = projectRepository.getDirectByUserID(uid,pageable);
            List<SimpleProjectDTO> projectsEntrust = projectRepository.getEntrustByUserID(uid,pageable);
            projects.addAll(projectsEntrust);
        }
        return projects;
    }

    @Override
    public void editProject(String uid, EditProjectRequest editProjectRequest) throws CustomerException {
        ProjectEntity project = projectRepository.findById(editProjectRequest.getId())
                                .orElseThrow(()->new CustomerException(StatusEnum.DONT_HAVE_PROJECT));
        if (project.getStatus().equals(ProjectStatus.DONE.getName())){
            throw new CustomerException(StatusEnum.PROJECT_HAS_BEEN_DONE);
        }else if (!project.getStatus().equals(ProjectStatus.STOP.getName())){
            throw new CustomerException(StatusEnum.PROJECT_MUST_BE_STOPPED);
        }
        List<IDTaskDTO> editTasks = editProjectRequest.getTasks();
        System.out.println(editTasks);
        List<SubTaskEntity> works = taskRepository.findAllByProjectIDAndStatusNotContainsOrderByTaskPosition(project.getProjectID(), WorkStatus.WAIT.getName());
        for (SubTaskEntity workDone : works ){
            if (workDone.getTaskID().equals(editTasks.get(workDone.getTaskPosition()))){

            }
        }

    }

    @Override
//    @Transactional
    public void stopProject(String uid,String projectID) throws CustomerException {
        // 修改项目状态，修改子任务状态，通知所有在项目中的用户,添加操作记录。
        ProjectEntity projectEntity = projectRepository.findById(projectID)
                                    .orElseThrow(()->new CustomerException(StatusEnum.DONT_HAVE_PROJECT));
        if (projectEntity.getStatus().equals(ProjectStatus.DONE.getName())){
            throw new CustomerException(StatusEnum.PROJECT_HAS_BEEN_DONE);
        }else if(projectEntity.getStatus().equals(ProjectStatus.STOP.getName())){
            throw new CustomerException(StatusEnum.PROJECY_HAS_BEEN_STOPED);
        }
        projectEntity.setStatus(ProjectStatus.STOP.getName());
        projectRepository.save(projectEntity);

        List<SubTaskEntity> tasks = taskRepository.findAllByProjectIDOrderByTaskPosition(projectID);
        List<String> users = new ArrayList<>();
        for (SubTaskEntity task : tasks){
            if (!task.getStatus().equals(WorkStatus.DONE.getName())){
                if (task.getStatus().equals(WorkStatus.DELEGATE.getName())){
                    //如果是在委托中的话，就给委托的人也发消息
                    EntrustEntity entrustEntity = entrustRepository.findEntrustEntityBySubTask(task.getTaskID())
                                            .orElseThrow(()->new CustomerException(StatusEnum.TASK_NOT_DELEGATE));
                    users.add(entrustEntity.getEntrustWorker());
                    entrustEntity.setStatus(WorkStatus.STOP.getName());
                    noticeService.saveStopEntrustNotice(entrustEntity.getEntrustWorker(),projectID,
                            projectEntity.getProjectName(),task.getTaskID());

                    //还要加上定时任务的取消
                    entrustRepository.save(entrustEntity);
                    task.setStatus(WorkStatus.STOP.getName());
                    users.add(task.getExecutor());
                    taskRepository.save(task);
                }else {
                    users.add(task.getExecutor());
                    task.setStatus(WorkStatus.STOP.getName());
                    taskRepository.save(task);
                }
            }else {
                users.add(task.getExecutor());
            }
            noticeService.saveStopNotice(task.getExecutor(),projectID,projectEntity.getProjectName(),task.getTaskID());
        }
        System.out.println(users);
        logService.saveLog(uid,projectID,"中止");

        for (String user:users){
            sessionService.sendMessage(user,"项目被中止");
        }
    }

    private ProjectEntity createNewProject(String uid,String projectName){
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setCreator(uid);
        projectEntity.setProjectName(projectName);
        projectEntity.setProjectID(UUID.randomUUID().toString().substring(10,25));
        projectEntity.setStatus(ProjectStatus.DOING.getName());
        projectEntity.setCreateDate(new Date());
        projectEntity.setPath("a");
        return projectEntity;
    }
//    private void saveTasks(int pid,)

}
