package org.cn.kaito.auth.Service;

public interface NoticeService {
    public void saveTaskNotice(String uid, String projectID,String projectName);

    void saveStopNotice(String user, String projectID, String projectName,String taskID);

    void saveStopEntrustNotice(String user, String projectID, String projectName,String taskID);
}
