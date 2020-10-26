package org.cn.kaito.auth.Controller.Request;

import lombok.Data;
import org.cn.kaito.auth.DTO.SimpleTaskDTO;

import java.util.List;

@Data
public class CreateProjectRequest {
    private String name;
    private List<SimpleTaskDTO> tasks ;
}
