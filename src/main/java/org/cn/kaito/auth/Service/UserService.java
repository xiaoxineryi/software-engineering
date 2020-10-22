package org.cn.kaito.auth.Service;

import org.cn.kaito.auth.Controller.Request.UserLoginRequest;
import org.cn.kaito.auth.Controller.Response.UserLoginResponse;

import java.util.Optional;

public interface UserService {
    Optional<UserLoginResponse> login(UserLoginRequest userLoginRequest);

}
