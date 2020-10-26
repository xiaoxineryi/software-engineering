package org.cn.kaito.auth.Controller.Response;

import lombok.Data;
import org.cn.kaito.auth.DTO.SimpleProjectDTO;

import java.util.List;

@Data
public class SimpleProjectResponse {
    List<SimpleProjectDTO> projects;
}
