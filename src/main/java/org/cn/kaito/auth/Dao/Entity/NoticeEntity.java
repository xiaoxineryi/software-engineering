package org.cn.kaito.auth.Dao.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Data
@Entity
@Table(name = "Notice")
public class NoticeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int noticeID;

    private String message;

    private String receiver;

    private Date noticeDate;

    private boolean isRead;

}
