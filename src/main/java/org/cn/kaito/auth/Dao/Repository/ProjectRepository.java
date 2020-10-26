package org.cn.kaito.auth.Dao.Repository;

import org.cn.kaito.auth.Dao.Entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<ProjectEntity,Integer> {
}
