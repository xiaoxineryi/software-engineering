package org.cn.kaito.auth.Exception;

import com.kaito.game.Utils.StatusEnum;

public class CustomerException extends BaseException{

    public CustomerException(StatusEnum status) {
        super(status);
    }
}
