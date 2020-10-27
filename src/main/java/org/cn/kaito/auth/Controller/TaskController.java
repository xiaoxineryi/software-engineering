package org.cn.kaito.auth.Controller;

import org.cn.kaito.auth.Exception.CustomerException;
import org.cn.kaito.auth.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@PreAuthorize("hasPermission('project','read')")
@RequestMapping("/task")
public class TaskController extends BaseController {
    @Autowired
    TaskService taskService;

    @GetMapping("/execute")
    public void execute(@RequestParam(name = "taskID") String taskID) throws CustomerException, IOException {
        taskService.execute(getUid(),taskID);
    }

    @PostMapping("/submit")
    public void submit(@RequestParam(name = "taskID") String taskID) throws IOException, CustomerException {
        taskService.submit(getUid(),taskID);
    }
}
