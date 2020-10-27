package org.cn.kaito.auth.Controller.Request;

import lombok.Data;
import org.cn.kaito.auth.DTO.IDTaskDTO;
import org.cn.kaito.auth.DTO.SimpleTaskDTO;

import java.util.List;

@Data
public class EditProjectRequest {
    String id;
    String name;
    List<IDTaskDTO> tasks;
}
