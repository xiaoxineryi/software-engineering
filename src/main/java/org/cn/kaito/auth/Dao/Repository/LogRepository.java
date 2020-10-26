package org.cn.kaito.auth.Dao.Repository;


import org.cn.kaito.auth.Dao.Entity.NoticeEntity;
import org.cn.kaito.auth.Dao.Entity.OperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogRepository extends JpaRepository<OperationEntity,Integer> {
    List<OperationEntity> findAllByProjectID(String projectID);
}
