package org.cn.kaito.auth.Controller.Request;

import lombok.Data;

@Data
public class EditUserRequest {
    private String userID;
    private String username;
    private int roleID;
    private boolean delete;
}
