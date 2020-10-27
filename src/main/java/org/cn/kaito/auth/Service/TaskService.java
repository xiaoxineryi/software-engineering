package org.cn.kaito.auth.Service;

import org.cn.kaito.auth.Exception.CustomerException;

import java.io.IOException;

public interface TaskService {
    void execute(String uid, String taskID) throws CustomerException, IOException;

    void submit(String uid,String taskID) throws CustomerException, IOException;

    void undo(String uid, String taskID) throws CustomerException, IOException;
}
