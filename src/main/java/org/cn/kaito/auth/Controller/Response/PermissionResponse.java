package org.cn.kaito.auth.Controller.Response;

import lombok.Data;
import org.cn.kaito.auth.DTO.PermissionDTO;

import java.util.List;

@Data
public class PermissionResponse {
    List<PermissionDTO> permissions;
}
