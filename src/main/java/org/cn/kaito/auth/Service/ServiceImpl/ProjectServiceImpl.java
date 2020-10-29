package org.cn.kaito.auth.Service.ServiceImpl;

import org.cn.kaito.auth.Controller.Request.CreateProjectRequest;
import org.cn.kaito.auth.Controller.Request.EditProjectRequest;
import org.cn.kaito.auth.Controller.Response.RandomTasksResponse;
import org.cn.kaito.auth.DTO.*;
import org.cn.kaito.auth.Dao.Entity.EntrustEntity;
import org.cn.kaito.auth.Dao.Entity.ProjectEntity;
import org.cn.kaito.auth.Dao.Entity.SubTaskEntity;
import org.cn.kaito.auth.Dao.Entity.UserEntity;
import org.cn.kaito.auth.Dao.Repository.*;
import org.cn.kaito.auth.Exception.CustomerException;
import org.cn.kaito.auth.Schedule.CronSchedulerJob;
import org.cn.kaito.auth.Service.*;
import org.cn.kaito.auth.Utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

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

    @Autowired
    CronSchedulerJob cronSchedulerService;

    @Override
    public RandomTasksResponse getRandomTasks() throws CustomerException {
        Integer random = new Random().nextInt(5)+1;
        RandomTasksResponse randomTasksResponse  = new RandomTasksResponse();
        for (int i = 0; i < random;i++){
            SimpleTaskDTO simpleTaskDTO = new SimpleTaskDTO();
            long cnt = typeRepository.count();
            Integer typeRandom = new Random().nextInt((int)cnt)+1;
            String type = typeRepository.getTypeByID(typeRandom);

            List<UserDTO> users = userService.getUserList(typeRandom);
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

        ProjectEntity projectEntity = createNewProject(uid,createProjectRequest.getName());
        ProjectEntity project = projectRepository.save(projectEntity);
        SimpleTaskDTO task = saveTasks(project.getProjectID(),createProjectRequest.getTasks());
        sessionService.sendMessage(task.getOwner().getId(),"有新的任务进入进行状态");
        logService.saveLog(uid,project.getProjectID(),"创建项目");
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
        if (userDTO.getTypeID() == AuthEnum.ADMIN.getID()){
            projects = projectRepository.getAll(pageable);
        }else {
            projects = projectRepository.getDirectByUserID(uid,pageable);
            List<SimpleProjectDTO> projectsEntrust = projectRepository.getEntrustByUserID(uid,pageable);
            projects.addAll(projectsEntrust);
        }
        return projects;
    }

    @Override
    @Transactional
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
        List<SubTaskEntity> works = taskRepository.findAllByProjectIDAndStatusNotContainsOrderByTaskPosition(project.getProjectID(), WorkStatus.DONE.getName());
        int index  = 0 ;
        for (IDTaskDTO idTaskDTO:editTasks){
            if (idTaskDTO.getTaskID()== null){
                continue;
            }else{
                SubTaskEntity task = taskRepository.findSubTaskEntityByTaskID(idTaskDTO.getTaskID())
                        .orElseThrow(()->new CustomerException(StatusEnum.TASK_NOT_FOUND));
                if (task.getStatus().equals(WorkStatus.DONE.getName())){
                    index +=1;
                }
            }
        }
        for (SubTaskEntity workUnDone : works ){
            taskRepository.delete(workUnDone);
        }

        int length = editTasks.size();
        for (;index<length;index ++){
            String projectID = project.getProjectID();
            IDTaskDTO idTaskDTO = editTasks.get(index);
            int typeID = idTaskDTO.getTypeID();
            SubTaskEntity subTaskEntity = new SubTaskEntity();
            subTaskEntity.setStatus(WorkStatus.STOP.getName());
            subTaskEntity.setTaskID(projectID+"_"+index);
            subTaskEntity.setTypeID(typeID);
            subTaskEntity.setExecutor(idTaskDTO.getOwner().getId());
            subTaskEntity.setProjectID(projectID);
            subTaskEntity.setTaskPosition(index);

            taskRepository.save(subTaskEntity);
        }

        for (IDTaskDTO idTaskDTO:editTasks){

            noticeService.saveChangedNotice(idTaskDTO.getOwner().getId(), project.getProjectID(), project.getProjectName());
            sessionService.sendMessage(idTaskDTO.getOwner().getId(),"任务被重新编辑");
        }
    }

    @Override
    @Transactional
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

    @Override
    @Transactional
    public void restart(String uid, String projectID) throws CustomerException {
        ProjectEntity projectEntity = projectRepository.findById(projectID)
                .orElseThrow(()->new CustomerException(StatusEnum.DONT_HAVE_PROJECT));
        if (projectEntity.getStatus().equals(ProjectStatus.DONE.getName())){
            throw new CustomerException(StatusEnum.PROJECT_HAS_BEEN_DONE);
        }else if(!projectEntity.getStatus().equals(ProjectStatus.STOP.getName())){
            throw new CustomerException(StatusEnum.PROJECT_MUST_BE_STOPPED);
        }
        projectEntity.setStatus(ProjectStatus.DOING.getName());
        projectRepository.save(projectEntity);
        List<SubTaskEntity> tasks = taskRepository.findAllByProjectIDOrderByTaskPosition(projectID);
        List<String> users = new ArrayList<>();
        boolean isFirst = true;
        for (SubTaskEntity task:tasks){
            if (task.getStatus().equals(WorkStatus.DONE.getName())){
                // 如果是已经完成，那么就只给一个通知即可
                users.add(task.getExecutor());
            }else if (!task.getStatus().equals(WorkStatus.STOP.getName())){
                //如果不在完成或中止状态，就抛出异常
                throw new CustomerException(StatusEnum.PROJECT_MUST_BE_STOPPED);
            }else{
                //如果在中止状态，如果是第一个的话，那么就设置为进行中，其他的为等待。
                users.add(task.getExecutor());
                if (isFirst){
                    task.setStatus(WorkStatus.DOING.getName());
                    taskRepository.save(task);
                    isFirst = false;
                }else {
                    task.setStatus(WorkStatus.WAIT.getName());
                    taskRepository.save(task);
                }
            }
            noticeService.saveRestartNotice(task.getExecutor(),
                    projectID,projectEntity.getProjectName(),task.getTaskID());
        }
        System.out.println(users);
        logService.saveLog(uid,projectID,"重启");
        for (String user:users){
            sessionService.sendMessage(user,"项目被重启");
        }
    }

    @Override
    public ProjectDetailDTO getDeatil(String uid, String pid) throws CustomerException {
        ProjectEntity projectEntity = projectRepository.findById(pid)
                                .orElseThrow(()->new CustomerException(StatusEnum.DONT_HAVE_PROJECT));
        ProjectDetailDTO projectDetailDTO = new ProjectDetailDTO();
        projectDetailDTO.setId(pid);
        projectDetailDTO.setPath(FileUtil.getFileSrc(projectEntity.getProjectName()));
        projectDetailDTO.setStatus(projectEntity.getStatus());
        projectDetailDTO.setName(projectEntity.getProjectName());
        projectDetailDTO.setCreateTime(projectEntity.getCreateDate());
        projectDetailDTO.setFinishTime(projectDetailDTO.getFinishTime());
        Integer tempIndex = 0;
        boolean flag = false;
        UserDTO user= userService.getUserByID(uid);
        if(user.getTypeID()==AuthEnum.ADMIN.getID()){
            flag = true;
        }
        List<SubTaskEntity> tasks = taskRepository.findAllByProjectIDOrderByTaskPosition(pid);
        for (SubTaskEntity task : tasks){
            UserDTO userDTO = userService.getUserByID(task.getExecutor());
            if (task.getExecutor().equals(uid)){
                flag = true;
            }

            WorkDetailDTO workDetailDTO = new WorkDetailDTO();
            workDetailDTO.setTaskID(task.getTaskID());
            workDetailDTO.setTypeID(task.getTypeID());
            workDetailDTO.setType(typeRepository.getTypeByID(task.getTypeID()));
            workDetailDTO.setWorkStatus(task.getStatus());
            OwnerDTO owner = new OwnerDTO();
            owner.setId(task.getExecutor());
            owner.setName(userDTO.getUsername());
            workDetailDTO.setOwner(owner);
            if (task.getStatus().equals(WorkStatus.DONE.getName())){
                tempIndex += 1;
                Optional<EntrustEntity> entrustEntity = entrustRepository.findEntrustEntityBySubTask(task.getTaskID());
                if (entrustEntity.isEmpty()){
                    workDetailDTO.setExecutor(owner);
                }else if (entrustEntity.get().getStatus().equals(WorkStatus.DONE.getName())){
                    EntrustEntity en = entrustEntity.get();
                    OwnerDTO executor = new OwnerDTO();
                    executor.setId(en.getEntrustWorker());
                    executor.setName(userService.getUserByID(en.getEntrustWorker()).getUsername());
                    workDetailDTO.setExecutor(executor);
                }
            }
            projectDetailDTO.getSubtasks().add(workDetailDTO);
        }
        projectDetailDTO.setTempIndex(tempIndex);
        if (!flag){
            throw new CustomerException(StatusEnum.DONT_HAVE_PERMISSION_HAVE_PROJECT_DETAIL);
        }
        return projectDetailDTO;
    }

    @Override
    public boolean validate(String uid, String fileName) throws CustomerException {
        ProjectEntity projectEntity = projectRepository.findProjectEntityByProjectName(fileName)
                                .orElseThrow(()->new CustomerException(StatusEnum.DONT_HAVE_PROJECT));
        Optional<SubTaskEntity> task= taskRepository.findAllByExecutorAndAndProjectID(uid,projectEntity.getProjectID());
        if (task.isEmpty()) {
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void delete(String uid, String projectID) throws CustomerException {
        //终止项目
        ProjectEntity projectEntity = projectRepository.findById(projectID)
                .orElseThrow(()->new CustomerException(StatusEnum.DONT_HAVE_PROJECT));
        if (projectEntity.getStatus().equals(ProjectStatus.DONE.getName())){
            throw new CustomerException(StatusEnum.PROJECT_HAS_BEEN_DONE);
        }else if(projectEntity.getStatus().equals(ProjectStatus.STOP.getName())){
            throw new CustomerException(StatusEnum.PROJECY_HAS_BEEN_STOPED);
        }
        projectEntity.setStatus(ProjectStatus.END.getName());
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
                    entrustEntity.setStatus(WorkStatus.END.getName());
                    noticeService.saveStopEntrustNotice(entrustEntity.getEntrustWorker(),projectID,
                            projectEntity.getProjectName(),task.getTaskID());

                    //还要加上定时任务的取消

                    entrustRepository.save(entrustEntity);
                    task.setStatus(WorkStatus.END.getName());
                    users.add(task.getExecutor());
                    taskRepository.save(task);
                }else {
                    users.add(task.getExecutor());
                    task.setStatus(WorkStatus.END.getName());
                    taskRepository.save(task);
                }
            }else {
                users.add(task.getExecutor());
            }
            noticeService.saveEndNotice(task.getExecutor(),projectID,projectEntity.getProjectName(),task.getTaskID());
        }
        System.out.println(users);
        logService.saveLog(uid,projectID,"终止项目");

        for (String user:users){
            sessionService.sendMessage(user,"项目被终止");
        }
    }

    private ProjectEntity createNewProject(String uid,String projectName){
        //
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setCreator(uid);
        projectEntity.setProjectName(projectName);
        projectEntity.setProjectID(UUID.randomUUID().toString().substring(10,25));
        projectEntity.setStatus(ProjectStatus.DOING.getName());
        projectEntity.setCreateDate(new Date());
        projectEntity.setPath(FileUtil.getFileSrc(projectName));
        return projectEntity;

    }
//    private void saveTasks(int pid,)

}
