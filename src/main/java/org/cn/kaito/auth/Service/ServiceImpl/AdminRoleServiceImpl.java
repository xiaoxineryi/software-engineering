package org.cn.kaito.auth.Service.ServiceImpl;

import org.cn.kaito.auth.Controller.Request.AddRoleRequest;
import org.cn.kaito.auth.Controller.Request.EditRoleRequest;
import org.cn.kaito.auth.Dao.Entity.PermissionRoleEntity;
import org.cn.kaito.auth.Dao.Entity.RoleEntity;
import org.cn.kaito.auth.Dao.Repository.RolePermissionRepository;
import org.cn.kaito.auth.Dao.Repository.RoleRepository;
import org.cn.kaito.auth.Exception.CustomerException;
import org.cn.kaito.auth.Service.AdminRoleService;
import org.cn.kaito.auth.Utils.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminRoleServiceImpl implements AdminRoleService {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RolePermissionRepository rolePermissionRepository;

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


}
