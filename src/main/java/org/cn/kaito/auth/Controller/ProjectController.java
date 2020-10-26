package org.cn.kaito.auth.Controller;

import org.aspectj.util.FileUtil;
import org.cn.kaito.auth.Controller.Response.RandomTasksResponse;
import org.cn.kaito.auth.Service.ProjectService;
import org.cn.kaito.auth.Service.WorkExecuteService;
import org.cn.kaito.auth.Utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/project")
@PreAuthorize(value = "hasPermission('project','read')")
public class ProjectController extends BaseController{
    @Autowired
    LogUtil logUtil;

    @Autowired
    WorkExecuteService workExecuteService;

    @Autowired
    ProjectService projectService;

    @GetMapping("random")
    public RandomTasksResponse getRandomTasks(){
    return null;
    }

    @GetMapping("/read")
    public void readProject() throws IOException {
        System.out.println("nice");
        logUtil.INFO("A","hello");
        workExecuteService.save("A");
    }
}
