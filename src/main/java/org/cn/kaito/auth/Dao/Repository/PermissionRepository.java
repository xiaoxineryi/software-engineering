package org.cn.kaito.auth.Dao.Repository;

import org.cn.kaito.auth.DTO.PermissionDTO;
import org.cn.kaito.auth.Dao.Entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity,Integer> {
    @Query(value = "select new org.cn.kaito.auth.DTO.PermissionDTO(p.permissionID,p.permissionName,p.permissionTarget) " +
            "from PermissionEntity p ")
    List<PermissionDTO> getAll();

    @Query(value = "select new org.cn.kaito.auth.DTO.PermissionDTO(p.permissionID,p.permissionName,p.permissionTarget) " +
            "from PermissionEntity p join PermissionRoleEntity  pr on p.permissionID = pr.permissionID" +
            " where pr.roleID=?1 ")
    List<PermissionDTO> getByRoleID(int roleID);
}
