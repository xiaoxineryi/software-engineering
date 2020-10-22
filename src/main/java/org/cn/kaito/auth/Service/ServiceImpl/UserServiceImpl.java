package org.cn.kaito.auth.Service.ServiceImpl;

import org.cn.kaito.auth.Controller.Request.UserLoginRequest;
import org.cn.kaito.auth.Controller.Response.UserLoginResponse;
import org.cn.kaito.auth.Service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public Optional<UserLoginResponse> login(UserLoginRequest userLoginRequest) {
        return Optional.empty();
    }
}
