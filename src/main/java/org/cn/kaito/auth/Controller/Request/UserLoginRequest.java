package org.cn.kaito.auth.Controller.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserLoginRequest {
    private String username;
    private String password;
}
