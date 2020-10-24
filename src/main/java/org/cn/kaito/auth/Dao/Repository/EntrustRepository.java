package org.cn.kaito.auth.Dao.Repository;

import org.apache.ibatis.annotations.Insert;
import org.cn.kaito.auth.Dao.Entity.EntrustEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrustRepository extends JpaRepository<EntrustEntity,Integer> {

}
