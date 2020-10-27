package org.cn.kaito.auth.Dao.Repository;


import org.cn.kaito.auth.Dao.Entity.NoticeEntity;
import org.cn.kaito.auth.Dao.Entity.OperationEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LogRepository extends JpaRepository<OperationEntity,Integer> {
    List<OperationEntity> findAllByProjectID(String projectID);


}
