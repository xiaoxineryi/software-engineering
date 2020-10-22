package org.cn.kaito.auth.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PermissionDTO {

    private String permissionName;
    private String permissionTarget;
}
