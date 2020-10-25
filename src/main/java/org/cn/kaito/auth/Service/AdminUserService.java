package org.cn.kaito.auth.Service;

import org.cn.kaito.auth.Controller.Request.AddRoleRequest;
import org.cn.kaito.auth.Controller.Request.EditUserRequest;
import org.cn.kaito.auth.Controller.Request.RegisterRequest;
import org.cn.kaito.auth.Controller.Response.RegisterResponse;
import org.cn.kaito.auth.Exception.CustomerException;

public interface AdminUserService {
    RegisterResponse addUser(RegisterRequest registerRequest);

    void editUser(EditUserRequest editUserRequest) throws CustomerException;


}
