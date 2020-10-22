package org.cn.kaito.auth.Exception;


import lombok.Getter;
import org.cn.kaito.auth.Utils.StatusEnum;

@Getter
public class BaseException extends Exception{
    protected StatusEnum statusEnum;

    public BaseException(StatusEnum status){
        this.statusEnum = status;
    }

}
