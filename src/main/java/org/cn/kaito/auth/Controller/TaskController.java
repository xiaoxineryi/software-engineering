package org.cn.kaito.auth.Controller;

import org.cn.kaito.auth.Controller.Request.DelegateRequest;
import org.cn.kaito.auth.Controller.Response.TaskListResponse;
import org.cn.kaito.auth.DTO.EntrustTaskDTO;
import org.cn.kaito.auth.DTO.SelfTaskDTO;
import org.cn.kaito.auth.Exception.CustomerException;
import org.cn.kaito.auth.Service.TaskService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

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

    @PostMapping("/undo")
    public void undo(@RequestParam(name = "taskID") String taskID) throws IOException, CustomerException {
        taskService.undo(getUid(),taskID);
    }

    @GetMapping("/list")
    public TaskListResponse taskList() throws CustomerException {
        List<SelfTaskDTO> selfTaskDTOS = taskService.getSelfTasks(getUid());
        List<EntrustTaskDTO> entrustTaskDTOS = taskService.getEntrustTasks(getUid());
        TaskListResponse taskListResponse = new TaskListResponse();
        taskListResponse.setEntrustTasks(entrustTaskDTOS);
        taskListResponse.setSelfTasks(selfTaskDTOS);
        return taskListResponse;
    }

    @PostMapping("/delegate")
    public void delegate(@RequestBody DelegateRequest delegateRequest) throws CustomerException {
        taskService.delegate(getUid(),delegateRequest);
    }

    @GetMapping("/delegateDelete")
    public void delegateDelete(@RequestParam(name = "taskID") String taskID ) throws CustomerException, SchedulerException {
        taskService.delegateDelete(taskID);
    }
}
