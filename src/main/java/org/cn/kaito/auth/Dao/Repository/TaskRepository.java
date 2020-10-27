package org.cn.kaito.auth.Dao.Repository;

import org.cn.kaito.auth.Dao.Entity.SubTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<SubTaskEntity,String> {

    Optional<SubTaskEntity> findAllByExecutorAndAndProjectID(String uid,String pid);

    @Query(value = "select p.projectName from ProjectEntity p join SubTaskEntity s " +
            "on p.projectID = s.projectID join EntrustEntity en on en.subTask = s.taskID " +
            "where en.entrustWorker = ?1 and p.projectID = ?2")
    Optional<String> findEntrustWorkByEntrustorandProjectID (String uid,String pid);

    List<SubTaskEntity> findAllByProjectIDOrderByTaskPosition(String projectID);

    List<SubTaskEntity> findAllByProjectIDAndStatusNotContainsOrderByTaskPosition(String projectID,String status);
}
