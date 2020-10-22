package org.cn.kaito.auth.Controller.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginResponse {
    private String userID;
    private String userName;
    private String token;

}
