package org.cn.kaito.auth.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class SimpleProjectDTO {
    String id;
    String name;
    String status;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    Date startTime;
    String creator;
    public SimpleProjectDTO(String id, String name, String creator,String status,Date startTime) {
        this.id = id;
        this.creator = creator;
        this.name = name;
        this.status = status;
        this.startTime = startTime;
    }
}
