package org.cn.kaito.auth.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.cn.kaito.auth.Dao.Entity.NoticeEntity;

import java.util.Date;

@Data
public class NoticeDTO {
    private int id;
    private String msg;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date time;

    public NoticeDTO(int id, String msg, Date time) {
        this.id = id;
        this.msg = msg;
        this.time = time;
    }
}
