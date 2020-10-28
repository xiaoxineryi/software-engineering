package org.cn.kaito.auth.DTO;

import lombok.Data;

@Data
public class EntrustTaskDTO {
    String taskID;
    int typeID;
    String type;
    OwnerDTO consiger;
    String projectID;
    String projectName;
    String workStatus;
}
