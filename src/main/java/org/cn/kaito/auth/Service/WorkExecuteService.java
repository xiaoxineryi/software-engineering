package org.cn.kaito.auth.Service;

import java.io.IOException;

public interface WorkExecuteService {
    void save(String basePath,String projectName);
    void save(String projectName) throws IOException;

    void commit(String basePath,String projectName);
    void commit(String projectName);

    void cancel(String basePath,String projectName);
    void cancel(String projectName);

}
