package org.cn.kaito.auth.DTO;

import lombok.Data;
import org.cn.kaito.auth.Dao.Entity.NoticeEntity;

import java.util.Date;

@Data
public class NoticeDTO {
    private int id;
    private String msg;
    private Date time;

    public NoticeDTO(int id, String msg, Date time) {
        this.id = id;
        this.msg = msg;
        this.time = time;
    }
}
