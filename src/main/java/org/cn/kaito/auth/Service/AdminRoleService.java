package org.cn.kaito.auth.Service;

import org.cn.kaito.auth.Controller.Request.AddRoleRequest;
import org.cn.kaito.auth.Controller.Request.EditRoleRequest;
import org.cn.kaito.auth.Controller.Request.PermissionAddRequest;
import org.cn.kaito.auth.Controller.Request.PermissionRequest;
import org.cn.kaito.auth.Controller.Response.PermissionResponse;
import org.cn.kaito.auth.Controller.Response.RolesResponse;
import org.cn.kaito.auth.Exception.CustomerException;

public interface AdminRoleService {
    void addRole(AddRoleRequest addRoleRequest);

    void editRole(EditRoleRequest editRoleRequest) throws CustomerException;

    void addPermission(PermissionAddRequest permissionAddRequest);

    void editPermission(PermissionRequest permissionRequest);

    RolesResponse getRoles();

    PermissionResponse getPermissions();

    PermissionResponse getPermissionsByRoleID(int roleID);
}
