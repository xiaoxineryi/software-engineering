package org.cn.kaito.auth.Controller.Request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private int roleID;
}
