package org.cn.kaito.auth.Utils;

public enum  ProjectStatus {
    DOING("正在进行"),
    DONE("已完成"),
    STOP("中止状态")
    ;
    ProjectStatus(String name){
        this.name = name;
    }
    private String name;
    public String getName(){return name;}
}
