package org.cn.kaito.auth.Service;

public interface NoticeService {
    public void saveTaskNotice(String uid, String projectID,String projectName);

    void saveStopNotice(String user, String projectID, String projectName,String taskID);

    void saveStopEntrustNotice(String user, String projectID, String projectName,String taskID);

    void saveRestartNotice(String executor, String projectID, String projectName, String taskID);

    void saveDelegateNotice(String executor,String projectID,String projectName,String taskID);

    void saveDelegateTakenNotice(String executor,String proejctID,String projectName,String taskID);

    void saveChangedNotice(String uid,String projectID,String projectName);
}
