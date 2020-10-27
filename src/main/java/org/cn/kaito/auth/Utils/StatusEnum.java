package org.cn.kaito.auth.Utils;

import lombok.Getter;

@Getter
public enum StatusEnum {
    SUCCESS(200, ""),
    WRONG_TOKEN_FOR_USER(401, "自动登录已过期，请重新登录"),
    ACCOUNT_HAS_BEEN_DELETED(401,"帐号已经被注销"),
    WRONG_ACCOUNT_OR_PASSWORD(402, "帐号或密码错误"),
    DONT_HAVE_PERMISSION_GETLOG(403,"没有权限获取项目日志信息"),
    CANT_SAVE_EMPTY_TASKS(401,"无法保存空的任务序列"),
    USER_ISNT_ONLINE(401,"用户没有在线"),
    FORBIDDEN(403, "请登录后访问"),
    CANT_FIND_GAME(404, "您输入的游戏编号不存在"),
    CANT_FIND_ROOM(405, "您输入的房间号不存在"),
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
