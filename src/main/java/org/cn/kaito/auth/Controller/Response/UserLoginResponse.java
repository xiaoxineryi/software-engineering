package org.cn.kaito.auth.Controller.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.cn.kaito.auth.Dao.Entity.UserEntity;

@Data

public class UserLoginResponse {
    private String token;

    public UserLoginResponse(String token) {
        this.token = token;
    }

}
