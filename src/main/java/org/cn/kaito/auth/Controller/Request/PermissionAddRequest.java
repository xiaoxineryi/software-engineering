package org.cn.kaito.auth.Controller.Request;

import lombok.Data;

@Data
public class PermissionAddRequest {
    private String permission_target;
    private String permission_name;
}
