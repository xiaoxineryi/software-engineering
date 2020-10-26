package org.cn.kaito.auth.Controller.Response;

import lombok.Data;
import org.cn.kaito.auth.DTO.RoleDTO;

import java.util.List;

@Data
public class RolesResponse {
    List<RoleDTO> roles;
}
