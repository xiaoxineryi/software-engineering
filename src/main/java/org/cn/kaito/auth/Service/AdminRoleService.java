package org.cn.kaito.auth.Service;

import org.cn.kaito.auth.Controller.Request.AddRoleRequest;
import org.cn.kaito.auth.Controller.Request.EditRoleRequest;
import org.cn.kaito.auth.Exception.CustomerException;

public interface AdminRoleService {
    void addRole(AddRoleRequest addRoleRequest);

    void editRole(EditRoleRequest editRoleRequest) throws CustomerException;
}
