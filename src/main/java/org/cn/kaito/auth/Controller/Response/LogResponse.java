package org.cn.kaito.auth.Controller.Response;

import lombok.Data;
import org.cn.kaito.auth.DTO.LogDTO;

import java.util.ArrayList;
import java.util.List;

@Data
public class LogResponse {
    List<LogDTO> actions = new ArrayList<>();


}
