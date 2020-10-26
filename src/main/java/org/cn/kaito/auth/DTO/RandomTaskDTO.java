package org.cn.kaito.auth.DTO;

import lombok.Data;
import org.springframework.data.domain.PageRequest;

@Data
public class RandomTaskDTO {
    private String type;
    OwnerDTO owner;
}
