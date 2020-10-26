package org.cn.kaito.auth.DTO;

import lombok.Data;
import org.springframework.data.domain.PageRequest;

@Data
public class SimpleTaskDTO {
    private Integer typeID;
    private String type;
    OwnerDTO owner;
}
