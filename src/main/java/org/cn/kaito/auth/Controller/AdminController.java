package org.cn.kaito.auth.Controller;

import org.cn.kaito.auth.Controller.Request.AddRoleRequest;
import org.cn.kaito.auth.Controller.Request.EditRoleRequest;
import org.cn.kaito.auth.Controller.Request.EditUserRequest;
import org.cn.kaito.auth.Controller.Request.RegisterRequest;
import org.cn.kaito.auth.Controller.Response.RegisterResponse;
import org.cn.kaito.auth.Exception.CustomerException;
import org.cn.kaito.auth.Service.AdminRoleService;
import org.cn.kaito.auth.Service.AdminUserService;
import org.cn.kaito.auth.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController {
    @Autowired
    AdminUserService adminUserService;

    @Autowired
    AdminRoleService adminRoleService;
    @PostMapping("/addUser")
    @PreAuthorize(value = "hasPermission('user','add')")
    public RegisterResponse addUser(@RequestBody RegisterRequest registerRequest){
        return adminUserService.addUser(registerRequest);
    }

    @PostMapping("/editUser")
    @PreAuthorize(value = "hasPermission('user','edit')")
    public void editUser(@RequestBody  EditUserRequest editUserRequest) throws CustomerException {
        adminUserService.editUser(editUserRequest);
    }

    @PostMapping("/addRole")
    @PreAuthorize(value = "hasPermission('role','add')")
    public void addRole(@RequestBody AddRoleRequest addRoleRequest){
        adminRoleService.addRole(addRoleRequest);
    }

    @PostMapping("/editRole")
    @PreAuthorize(value = "hasPermission('role','edit')")
    public void editRole(@RequestBody EditRoleRequest editRoleRequest) throws CustomerException {
        adminRoleService.editRole(editRoleRequest);
    }

    @PostMapping("/addPermission")
    @PreAuthorize(value = "hasPermission('permission','add')")
    public void addPermission(@RequestParam(name = "permission_name") String permissionName){
        System.out.println(permissionName);
    }

}
