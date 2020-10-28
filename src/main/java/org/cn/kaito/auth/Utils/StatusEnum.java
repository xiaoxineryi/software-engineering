package org.cn.kaito.auth.Utils;

import lombok.Getter;

@Getter
public enum StatusEnum {
    SUCCESS(200, ""),
    SERVER_CANT_WORK(401,"服务器异常，请稍后再试"),
    USER_CANT_WORK(403,"用户无权进行该类任务工作"),
    WRONG_TOKEN_FOR_USER(401, "自动登录已过期，请重新登录"),
    ACCOUNT_HAS_BEEN_DELETED(401,"帐号已经被注销"),
    WRONG_ACCOUNT_OR_PASSWORD(402, "帐号或密码错误"),
    WORK_CANT_BE_UNDO(403,"任务必须在保存状态才可以撤销"),
    WORK_CANT_BE_SUBMIT(403,"任务必须在保存状态才可以提交"),
    DELEGATE_WORK_HAS_BEEN_DONE(401,"委托任务已经完成"),
    WORK_CANT_BE_DONE(401,"该任务不在进行中"),
    DELEGATE_NOT_FOUND(401,"委托没有找到"),
    SAME_WORK_CANT_BE_DELEGATE_TWICE(401,"同一任务不可被委派多次"),
    DONT_HAVE_PERMISSION_GETLOG(403,"没有权限获取项目日志信息"),
    CANT_SAVE_EMPTY_TASKS(401,"无法保存空的任务序列"),
    DONT_HAVE_PROJECT(404,"没有找到项目"),
    TASK_NOT_DELEGATE(403,"任务未被委托"),
    PROJECT_NOT_BEEN_STOP(403,"项目不在终止状态"),
    PROJECT_HAS_BEEN_DONE(403,"项目已经执行完毕"),
    TASK_NOT_FOUND(401,"子任务未找到"),
    PROJECT_MUST_BE_STOPPED(403,"项目必须在中止状态下才可以进行修改/中止操作"),
    PROJECY_HAS_BEEN_STOPED(401,"项目已经在中止状态"),
    USER_ISNT_ONLINE(401,"用户没有在线"),
    FORBIDDEN(403, "请登录后访问"),
    REPEAT_ACCOUNT(406, "账号已被注册"),
    CANT_FIND_USER(407, "用户不存在"),
    HAVE_HAD_FRIEND(408, "已有该用户好友");

    StatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public boolean isSuccess() {
        return code == StatusEnum.SUCCESS.code;
    }
}
