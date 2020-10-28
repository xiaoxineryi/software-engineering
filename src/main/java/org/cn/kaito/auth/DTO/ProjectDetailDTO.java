package org.cn.kaito.auth.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ProjectDetailDTO {
    String id;
    String name;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    Date createTime;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    Date finishTime;
    int tempIndex;
    String path;
    String status;
    List<WorkDetailDTO> subtasks = new ArrayList<>();
}
