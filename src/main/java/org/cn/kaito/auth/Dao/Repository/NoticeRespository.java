package org.cn.kaito.auth.Dao.Repository;

import org.cn.kaito.auth.DTO.NoticeDTO;
import org.cn.kaito.auth.Dao.Entity.NoticeEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;


public interface NoticeRespository extends JpaRepository<NoticeEntity,Integer> {


    @Query("select new org.cn.kaito.auth.DTO.NoticeDTO(n.noticeID,n.message,n.noticeDate)" +
            "from NoticeEntity n where n.receiver=?1 ")
    List<NoticeDTO> findNoticeDTOSByReceiver(String receiver, Pageable pageable);
}
