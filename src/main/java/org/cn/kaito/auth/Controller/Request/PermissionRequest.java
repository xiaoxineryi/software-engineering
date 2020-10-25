package org.cn.kaito.auth.Controller.Request;

import lombok.Data;

@Data
public class PermissionRequest {
    private int permissionID;
    private String permission_target;
    private String permission_name;
}
