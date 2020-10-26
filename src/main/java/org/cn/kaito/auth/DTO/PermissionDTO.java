package org.cn.kaito.auth.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class PermissionDTO {
    private int id;
    private String permission_name;
    private String permission_target;

    public PermissionDTO(int id, String permission_name, String permission_target) {
        this.id = id;
        this.permission_name = permission_name;
        this.permission_target = permission_target;
    }
}
