package org.cn.kaito.auth.Controller.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class DelegateRequest {
    private String taskID;
    private String userID;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date deadline;
}
