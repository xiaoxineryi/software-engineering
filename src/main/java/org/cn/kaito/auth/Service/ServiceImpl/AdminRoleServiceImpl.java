package org.cn.kaito.auth.Service.ServiceImpl;

import org.cn.kaito.auth.Controller.Request.AddRoleRequest;
import org.cn.kaito.auth.Controller.Request.EditRoleRequest;
import org.cn.kaito.auth.Controller.Request.PermissionAddRequest;
import org.cn.kaito.auth.Controller.Request.PermissionRequest;
import org.cn.kaito.auth.Controller.Response.PermissionResponse;
import org.cn.kaito.auth.Controller.Response.RolesResponse;
import org.cn.kaito.auth.DTO.PermissionDTO;
import org.cn.kaito.auth.DTO.RoleDTO;
import org.cn.kaito.auth.Dao.Entity.PermissionEntity;
import org.cn.kaito.auth.Dao.Entity.PermissionRoleEntity;
import org.cn.kaito.auth.Dao.Entity.RoleEntity;
import org.cn.kaito.auth.Dao.Repository.PermissionRepository;
import org.cn.kaito.auth.Dao.Repository.RolePermissionRepository;
import org.cn.kaito.auth.Dao.Repository.RoleRepository;
import org.cn.kaito.auth.Exception.CustomerException;
import org.cn.kaito.auth.Service.AdminRoleService;
import org.cn.kaito.auth.Utils.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;

@Service
public class AdminRoleServiceImpl implements AdminRoleService {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RolePermissionRepository rolePermissionRepository;

    @Autowired
    PermissionRepository permissionRepository;
    @Override
    public void addRole(AddRoleRequest addRoleRequest) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName(addRoleRequest.getRolename());
        RoleEntity savedRoleEntity = roleRepository.save(roleEntity);
        int roleID = savedRoleEntity.getRoleID();
        savePermissions(roleID,addRoleRequest.getPermissions());
    }
    private void savePermissions(int roleID, List<Integer> permissions){
        for (Integer permissionID:permissions){
            PermissionRoleEntity permissionRoleEntity = new PermissionRoleEntity();
            permissionRoleEntity.setPermissionID(permissionID);
            permissionRoleEntity.setRoleID(roleID);
            rolePermissionRepository.save(permissionRoleEntity);
        }
    }
    private void deleteRolePermissions(int roleID){
        rolePermissionRepository.deleteByRoleID(roleID);
    }
    @Override
    public void editRole(EditRoleRequest editRoleRequest) throws CustomerException {

        RoleEntity roleEntity = roleRepository.getByID(editRoleRequest.getRoleID())
                .orElseThrow(()->new CustomerException(StatusEnum.CANT_FIND_USER));
        roleEntity.setRoleName(editRoleRequest.getRolename());
        roleEntity.setRoleID(editRoleRequest.getRoleID());
        roleRepository.save(roleEntity);
        deleteRolePermissions(roleEntity.getRoleID());
        savePermissions(roleEntity.getRoleID(),editRoleRequest.getPermissions());

    }

    @Override
    public void addPermission(PermissionAddRequest permissionAddRequest) {
        PermissionEntity permissionEntity = new PermissionEntity();
        permissionEntity.setPermissionName(permissionAddRequest.getPermission_name());
        permissionEntity.setPermissionTarget(permissionAddRequest.getPermission_target());
        permissionRepository.save(permissionEntity);
    }

    @Override
    public void editPermission(PermissionRequest permissionRequest) {
        PermissionEntity permissionEntity = new PermissionEntity();
        permissionEntity.setPermissionName(permissionRequest.getPermission_name());
        permissionEntity.setPermissionTarget(permissionRequest.getPermission_target());
        permissionEntity.setPermissionID(permissionRequest.getPermissionID());
        permissionRepository.save(permissionEntity);
    }

    @Override
    public RolesResponse getRoles() {
        RolesResponse rolesResponse = new RolesResponse();
        List<RoleDTO> roleDTOS = roleRepository.getAll();
        rolesResponse.setRoles(roleDTOS);
        return rolesResponse;
    }

    @Override
    public PermissionResponse getPermissions() {
        PermissionResponse permissionResponse = new PermissionResponse();
        List<PermissionDTO> permissionDTOS = permissionRepository.getAll();
        permissionResponse.setPermissions(permissionDTOS);
        return permissionResponse;
    }

    @Override
    public PermissionResponse getPermissionsByRoleID(int roleID) {
        PermissionResponse permissionResponse = new PermissionResponse();
        List<PermissionDTO> permissionDTOS = permissionRepository.getByRoleID(roleID);
        permissionResponse.setPermissions(permissionDTOS);
        return permissionResponse;
    }


}
