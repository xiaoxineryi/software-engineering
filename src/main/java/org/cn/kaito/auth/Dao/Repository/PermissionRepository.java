package org.cn.kaito.auth.Dao.Repository;

import org.cn.kaito.auth.Dao.Entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity,Integer> {
}
