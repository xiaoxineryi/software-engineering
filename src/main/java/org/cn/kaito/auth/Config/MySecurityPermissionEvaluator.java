package org.cn.kaito.auth.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;


public class MySecurityPermissionEvaluator implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication, Object o, Object o1) {
       var auths =  authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority:auths){
            String[] s =  grantedAuthority.getAuthority().split("-");
            if (s[0].equals(o) && s[1].equals(o1) ){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return hasPermission(authentication,s,o);
    }
}
