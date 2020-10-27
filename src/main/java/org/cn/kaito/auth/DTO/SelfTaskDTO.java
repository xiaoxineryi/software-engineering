package org.cn.kaito.auth.DTO;

import lombok.Data;

@Data
public class SelfTaskDTO {
    private String taskID;
    private String projectID;
    private String projectName;
    private String workStatus;
    private OwnerDTO consignee;
    private OwnerDTO executor;
}
