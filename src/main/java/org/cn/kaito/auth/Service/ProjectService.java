package org.cn.kaito.auth.Service;


import org.cn.kaito.auth.Controller.Request.CreateProjectRequest;
import org.cn.kaito.auth.Controller.Request.EditProjectRequest;
import org.cn.kaito.auth.Controller.Response.RandomTasksResponse;
import org.cn.kaito.auth.DTO.ProjectDetailDTO;
import org.cn.kaito.auth.DTO.SimpleProjectDTO;
import org.cn.kaito.auth.Exception.CustomerException;

import java.io.IOException;
import java.util.List;

public interface ProjectService {
    RandomTasksResponse getRandomTasks() throws CustomerException;

    void createProject(String uid,CreateProjectRequest createProjectRequest) throws CustomerException, IOException;

    List<SimpleProjectDTO> getSimpleProjects(String uid, int page) throws CustomerException;

    void editProject(String uid, EditProjectRequest editProjectRequest) throws CustomerException;

    void stopProject(String uid,String projectID) throws CustomerException;

    void restart(String uid, String projectID) throws CustomerException;

    ProjectDetailDTO getDeatil(String uid, String pid) throws CustomerException;
}
