package org.cn.kaito.auth.Utils;

import lombok.Getter;

@Getter
public enum EntrustEnum {
    DONE("已完成"),
    COMMIT("已提交"),
    DOING("正在进行"),
    BETAKEN("委托被收回")
    ;
    EntrustEnum(String name){
        this.name = name;
    }
    private String name;
}
