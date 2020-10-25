package org.cn.kaito.auth.Utils;

public enum UserTypeEnum {
    WORKER_A("A");
    private String type;
    UserTypeEnum(String type){this.type = type;}
    public String getType(){return type;}
}
