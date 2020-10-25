package org.cn.kaito.auth.Controller.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
public class RegisterResponse {
    private String password;
}
