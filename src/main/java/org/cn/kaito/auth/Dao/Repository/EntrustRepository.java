package org.cn.kaito.auth.Dao.Repository;

import org.apache.ibatis.annotations.Insert;
import org.cn.kaito.auth.Dao.Entity.EntrustEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface EntrustRepository extends JpaRepository<EntrustEntity,Integer> {
    Optional<EntrustEntity> findEntrustEntityBySubTask(String taskID);

    Optional<EntrustEntity> findEntrustEntityByEntrustID(int id);
    List<EntrustEntity> findAllByEntrustWorker(String uid);
}
