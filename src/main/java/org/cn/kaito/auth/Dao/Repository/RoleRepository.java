package org.cn.kaito.auth.Dao.Repository;

import org.cn.kaito.auth.DTO.PermissionDTO;
import org.cn.kaito.auth.Dao.Entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,String> {

    @Query(value = "select new org.cn.kaito.auth.Dao.Entity.RoleEntity(r.roleID,r.roleName) from RoleEntity r where r.roleID=?1 ")
    Optional<RoleEntity> getByID(Integer roleID);
}
