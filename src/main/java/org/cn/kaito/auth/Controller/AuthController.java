package org.cn.kaito.auth.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {

    @PreAuthorize(value = "hasPermission('user','edit')")
    @GetMapping("/superAdmin")
    public void AuthSuperAdmin(){

    }

    @PreAuthorize("hasPermission('project','edit')")
    @GetMapping("admin")
    public void AuthAdmin(){

    }

}
