package org.cn.kaito.auth.Utils;

public enum AuthEnum {
    A_WORKER(1),
    B_WORKER(2),
    C_WORKER(3),
    ADMIN(4),
    SUPER_ADMIN(5),
    BE_DELETE(6);
    private int id;
    public int getID(){return id;}
    AuthEnum(int i){this.id = i;}
}
