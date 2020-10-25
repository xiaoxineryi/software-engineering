package org.cn.kaito.auth.Controller.Request;

import lombok.Data;

import java.util.List;

@Data
public class AddRoleRequest {
    private String rolename;
    private List<Integer> permissions;
}
