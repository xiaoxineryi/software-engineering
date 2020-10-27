package org.cn.kaito.auth.DTO;

import lombok.Data;

@Data
public class IDTaskDTO {
    private Integer taskID;
    private Integer typeID;
    OwnerDTO owner;
}
