package org.cn.kaito.auth.Dao.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userID;

    private String userName;

    private String userPwd;

    private int roleID;

    private String token;

    private Boolean isDelete;
}
