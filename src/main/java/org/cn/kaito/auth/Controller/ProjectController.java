package org.cn.kaito.auth.Controller;

import org.cn.kaito.auth.Controller.Request.CreateProjectRequest;
import org.cn.kaito.auth.Controller.Response.LogResponse;
import org.cn.kaito.auth.Controller.Response.RandomTasksResponse;
import org.cn.kaito.auth.Controller.Response.SimpleProjectResponse;
import org.cn.kaito.auth.DTO.LogDTO;
import org.cn.kaito.auth.DTO.SimpleProjectDTO;
import org.cn.kaito.auth.Exception.CustomerException;
import org.cn.kaito.auth.Service.LogService;
import org.cn.kaito.auth.Service.ProjectService;
import org.cn.kaito.auth.Service.WorkExecuteService;
import org.cn.kaito.auth.Utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/project")
@PreAuthorize(value = "hasPermission('project','read')")
public class ProjectController extends BaseController{
//    @Autowired
//    LogUtil logUtil;

    @Autowired
    LogService logService;

    @Autowired
    WorkExecuteService workExecuteService;

    @Autowired
    ProjectService projectService;

    @GetMapping("/random")
    @PreAuthorize(value = "hasPermission('project','edit')")
    public RandomTasksResponse getRandomTasks() throws CustomerException {
        return projectService.getRandomTasks();
    }

    @PostMapping("/create")
    @PreAuthorize(value = "hasPermission('project','create')")
    public void createProject(@RequestBody CreateProjectRequest createProjectRequest){
        projectService.createProject(getUid(),createProjectRequest);
    }

    @GetMapping("/list")
    public SimpleProjectResponse getSimpleProjects(@RequestParam(name = "page") int page) throws CustomerException {
        List<SimpleProjectDTO> projectDTOS =  projectService.getSimpleProjects(getUid(),page);
        SimpleProjectResponse simpleProjectResponse = new SimpleProjectResponse();
        simpleProjectResponse.setProjects(projectDTOS);
        return simpleProjectResponse;
    }

    @GetMapping("/log")
    public LogResponse getLogs(@RequestParam(name = "projectID") String projectID,
                               @RequestParam(name = "page") int page) throws CustomerException {
        List<LogDTO> logs = logService.getLogs(getUid(),projectID,page);
        LogResponse logResponse = new LogResponse();
        logResponse.setActions(logs);
        return logResponse;
    }


//    @GetMapping("/read")
//    public void readProject() throws IOException {
//        System.out.println("nice");
//        logUtil.INFO("A","hello");
//        workExecuteService.save("A");
//    }
}
