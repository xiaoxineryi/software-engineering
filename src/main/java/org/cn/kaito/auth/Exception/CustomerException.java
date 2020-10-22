package org.cn.kaito.auth.Exception;


import org.cn.kaito.auth.Utils.StatusEnum;

public class CustomerException extends BaseException{

    public CustomerException(StatusEnum status) {
        super(status);
    }
}
