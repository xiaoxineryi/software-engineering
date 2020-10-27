package org.cn.kaito.auth.Service.ServiceImpl;

import org.cn.kaito.auth.Controller.Request.CreateProjectRequest;
import org.cn.kaito.auth.Controller.Response.RandomTasksResponse;
import org.cn.kaito.auth.DTO.OwnerDTO;
import org.cn.kaito.auth.DTO.SimpleProjectDTO;
import org.cn.kaito.auth.DTO.SimpleTaskDTO;
import org.cn.kaito.auth.DTO.UserDTO;
import org.cn.kaito.auth.Dao.Entity.ProjectEntity;
import org.cn.kaito.auth.Dao.Entity.SubTaskEntity;
import org.cn.kaito.auth.Dao.Repository.ProjectRepository;
import org.cn.kaito.auth.Dao.Repository.TaskRepository;
import org.cn.kaito.auth.Dao.Repository.TypeRepository;
import org.cn.kaito.auth.Exception.CustomerException;
import org.cn.kaito.auth.Service.ProjectService;
import org.cn.kaito.auth.Service.UserService;
import org.cn.kaito.auth.Utils.ProjectStatus;
import org.cn.kaito.auth.Utils.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {


    @Autowired
    UserService userService;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    TypeRepository typeRepository;

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
    public void createProject(String uid,CreateProjectRequest createProjectRequest) throws CustomerException {
        /**
         * TODO:
         * 创建项目需要创建项目，然后将子任务变为等待状态，第一个任务变为进行中状态，通知对应的任务的用户进行操作，然后记录一次操作为保存项目，同时新建项目目录与文件。
         */
        ProjectEntity projectEntity = createNewProject(uid,createProjectRequest.getName());
        ProjectEntity project = projectRepository.save(projectEntity);
        SimpleTaskDTO task = saveTasks(project.getProjectID(),createProjectRequest.getTasks());

    }

    private SimpleTaskDTO saveTasks(String projetcID,List<SimpleTaskDTO> tasks) throws CustomerException {
        /**
         * 如果有子任务序列，那么就返回第一个任务。
         */
        if (tasks.size()==0){
            throw new CustomerException(StatusEnum.CANT_SAVE_EMPTY_TASKS);
        }
        for (SimpleTaskDTO task : tasks){
            int index = tasks.indexOf(task);

            SubTaskEntity subTaskEntity = new SubTaskEntity();
            subTaskEntity.setExecutor(task.getOwner().getId());
            subTaskEntity.setProjectID(projetcID);
            subTaskEntity.setIndex(index);
            subTaskEntity.setTypeID(task.getTypeID());
            subTaskEntity.setTaskID(projetcID+"_"+String.valueOf(index+1));
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
