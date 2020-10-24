package org.cn.kaito.auth.Dao.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Data
@Entity
@Table(name = "Notice")
public class NoticeEntity {
    @Id
    private int noticeID;

    private String message;

    private String receiver;

    private Date noticeDate;

    private boolean isRead;

}
