package org.cn.kaito.auth.Dao.Repository;

import org.cn.kaito.auth.DTO.NoticeDTO;
import org.cn.kaito.auth.Dao.Entity.NoticeEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
public interface NoticeRespository extends JpaRepository<NoticeEntity,Integer> {


    @Query("select new org.cn.kaito.auth.DTO.NoticeDTO(n.noticeID,n.message,n.noticeDate)" +
            "from NoticeEntity n where n.receiver=?1 ")
    List<NoticeDTO> findNoticeDTOSByReceiver(String receiver, Pageable pageable);


    @Query(value = "select count(n) from NoticeEntity n where n.receiver = ?1 and n.isRead = false")
    int countUnreadNotice(String uid);

    @Transactional
    @Modifying
    @Query(value = "update NoticeEntity n set  n.isRead = true where n.receiver = ?1")
    void updateNotice(String uid);


}
