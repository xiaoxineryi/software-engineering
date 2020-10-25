package org.cn.kaito.auth.Utils;

import java.util.UUID;

public class TokenUtil {
    private TokenUtil(){}
    public static String createToken(){
        return UUID.randomUUID().toString();
    }
}
