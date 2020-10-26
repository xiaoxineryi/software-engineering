package org.cn.kaito.auth.Service;


import org.cn.kaito.auth.Controller.Request.CreateProjectRequest;
import org.cn.kaito.auth.Controller.Response.RandomTasksResponse;
import org.cn.kaito.auth.DTO.SimpleProjectDTO;
import org.cn.kaito.auth.Exception.CustomerException;

import java.util.List;

public interface ProjectService {
    RandomTasksResponse getRandomTasks() throws CustomerException;

    void createProject(String uid,CreateProjectRequest createProjectRequest);

    List<SimpleProjectDTO> getSimpleProjects(String uid, int page) throws CustomerException;
}
