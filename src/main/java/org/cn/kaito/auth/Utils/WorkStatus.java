package org.cn.kaito.auth.Utils;

public enum WorkStatus {
    DOING("进行中"),
    DELEGATE("委托中"),
    DONE("已完成"),
    SAVE("保存"),
    WAIT("等待"),
    STOP("中止"),
    FAILED("委托期间内未完成"),
    TAKE("未完成任务被主动收回"),
    END("终止")
    ;
    WorkStatus(String name){
        this.name = name;
    }
    private String name;
    public String getName(){return name;}
}
