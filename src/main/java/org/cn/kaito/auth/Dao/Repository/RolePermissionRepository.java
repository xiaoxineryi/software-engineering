package org.cn.kaito.auth.Dao.Repository;

import org.cn.kaito.auth.DTO.PermissionDTO;
import org.cn.kaito.auth.Dao.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.JoinColumn;
import java.util.List;

@Repository
public interface RolePermissionRepository extends JpaRepository<UserEntity,String> {
    @Query(value = "select new org.cn.kaito.auth.DTO.PermissionDTO(p.permissionName,p.permissionTarget) " +
            "from UserEntity ue left join RoleEntity r on(ue.roleID=r.roleID) " +
            "left join PermissionRoleEntity pr on(pr.RoleID = r.roleID) " +
            "left join PermissionEntity p  on (pr.PermissionID=p.permissionID)" +
            "where ue.userID = ?1")
    List<PermissionDTO> getPermissionByUserID(String userID);
}
