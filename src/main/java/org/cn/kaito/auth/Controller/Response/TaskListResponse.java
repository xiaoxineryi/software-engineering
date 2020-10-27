package org.cn.kaito.auth.Controller.Response;

import lombok.Data;
import org.cn.kaito.auth.DTO.EntrustTaskDTO;
import org.cn.kaito.auth.DTO.OwnerDTO;
import org.cn.kaito.auth.DTO.SelfTaskDTO;

import java.util.List;

@Data
public class TaskListResponse {
    List<SelfTaskDTO> selfTasks;
    List<EntrustTaskDTO> entrustTasks;
}
