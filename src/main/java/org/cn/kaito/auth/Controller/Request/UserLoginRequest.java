package org.cn.kaito.auth.Controller.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginRequest {
    private String userID;
    private String userPwd;
}
