package org.cn.kaito.auth.Service;

import java.io.IOException;

public interface WorkExecuteService {
    void init(String basePath,String projectName) throws IOException;
    void init(String projectName) throws IOException;

    void save(String basePath,String projectName,String userName,String userID,String type);
    void save(String projectName,String userName,String userID,String type) throws IOException;

    void commit(String basePath,String projectName) throws IOException;
    void commit(String projectName) throws IOException;


    void undo(String basePath,String projectName) throws IOException;
    void undo(String projectName) throws IOException;
}
