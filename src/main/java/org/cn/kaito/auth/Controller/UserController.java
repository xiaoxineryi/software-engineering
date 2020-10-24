package org.cn.kaito.auth.Controller;

import org.cn.kaito.auth.Controller.Request.UserLoginRequest;
import org.cn.kaito.auth.Controller.Response.UserLoginResponse;
import org.cn.kaito.auth.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/login")
    public UserLoginResponse login(@RequestBody UserLoginRequest userLogin){
        return null;
    }
}
