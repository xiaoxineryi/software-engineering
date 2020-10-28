package org.cn.kaito.auth.DTO;

import lombok.Data;

@Data
public class WorkDetailDTO {
    private String taskID;
    private Integer typeID;
    private String type;
    private String workStatus;
    private OwnerDTO owner;
    private OwnerDTO executor;
}
