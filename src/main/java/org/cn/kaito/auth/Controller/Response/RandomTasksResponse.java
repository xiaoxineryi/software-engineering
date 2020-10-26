package org.cn.kaito.auth.Controller.Response;

import lombok.Data;
import org.cn.kaito.auth.DTO.SimpleTaskDTO;

import java.util.ArrayList;
import java.util.List;

@Data
public class RandomTasksResponse {
    List<SimpleTaskDTO> tasks = new ArrayList<>();
}
