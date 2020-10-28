package org.cn.kaito.auth.Controller;

import org.cn.kaito.auth.Controller.Request.*;
import org.cn.kaito.auth.Controller.Response.GetUserByIDResponse;
import org.cn.kaito.auth.Controller.Response.PermissionResponse;
import org.cn.kaito.auth.Controller.Response.RegisterResponse;
import org.cn.kaito.auth.Controller.Response.RolesResponse;
import org.cn.kaito.auth.DTO.UserDTO;
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

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    @PreAuthorize(value = "hasPermission('user','add')")
    public RegisterResponse addUser(@RequestBody RegisterRequest registerRequest) throws CustomerException {
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
    public void addPermission(@RequestBody PermissionAddRequest permissionAddRequest){
        adminRoleService.addPermission(permissionAddRequest);
    }

    @PostMapping("/editPermission")
    @PreAuthorize(value = "hasPermission('permission','edit')")
    public void editPermission(@RequestBody PermissionRequest permissionRequest){
        adminRoleService.editPermission(permissionRequest);
    }

    @GetMapping("/user/{uid}")
    public GetUserByIDResponse getUserById(@PathVariable(name = "uid") String uid) throws CustomerException {
        UserDTO userDTO = userService.getUserByID(uid);
        GetUserByIDResponse getUserByIDResponse = new GetUserByIDResponse();
        getUserByIDResponse.setUser(userDTO);
        return getUserByIDResponse;
    }

    @PreAuthorize("hasPermission('permission','edit')")
    @GetMapping("/listRole")
    public RolesResponse getRoleList(){
        return adminRoleService.getRoles();
    }

    @PreAuthorize("hasPermission('permission','edit')")
    @GetMapping("/listPermission")
    public PermissionResponse getPermissionList(){
        return adminRoleService.getPermissions();
    }

    @GetMapping("/{roleID}/permissions")
    public PermissionResponse getPermissionByRoleID(@PathVariable(name = "roleID") int roleID){
        return adminRoleService.getPermissionsByRoleID(roleID);
    }


}
