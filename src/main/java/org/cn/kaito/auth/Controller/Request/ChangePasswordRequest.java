package org.cn.kaito.auth.Controller.Request;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String old_pwd;
    private String new_pwd;
}
