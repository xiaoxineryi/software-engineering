package org.cn.kaito.auth.Dao.Repository;

import org.cn.kaito.auth.DTO.SimpleProjectDTO;
import org.cn.kaito.auth.Dao.Entity.ProjectEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity,String> {
    @Query(value = "select new org.cn.kaito.auth.DTO.SimpleProjectDTO(p.projectID,p.projectName" +
            ",p.status,p.createDate)" +
            "from ProjectEntity p order by p.createDate desc ")
    List<SimpleProjectDTO> getAll(Pageable pageable);

    @Query(value = "select distinct new org.cn.kaito.auth.DTO.SimpleProjectDTO(p.projectID,p.projectName" +
            ",p.status,p.createDate)" +
            "from ProjectEntity p join SubTaskEntity st on p.projectID = st.projectID " +
            "where st.executor = ?1 " +
            "order by p.createDate desc" )
    List<SimpleProjectDTO> getDirectByUserID(String uid,Pageable pageable);

    @Query(value = "select distinct new org.cn.kaito.auth.DTO.SimpleProjectDTO(p.projectID,p.projectName" +
            ",p.status,p.createDate)" +
            "from ProjectEntity p join SubTaskEntity st on p.projectID = st.projectID " +
            "join EntrustEntity en on en.subTask = st.taskID " +
            "where en.entrustWorker = ?1 " +
            "order by p.createDate desc")
    List<SimpleProjectDTO> getEntrustByUserID(String uid, Pageable pageable);





//    List<SimpleProjectDTO> getAllByUserID(Pageable pageable);
}
