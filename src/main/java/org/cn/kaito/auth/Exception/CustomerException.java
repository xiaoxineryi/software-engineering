package org.cn.kaito.auth.Exception;


import org.cn.kaito.auth.Utils.StatusEnum;

import java.util.function.Supplier;

public class CustomerException extends BaseException  {

    public CustomerException(StatusEnum status) {
        super(status);
    }
}
