package org.cn.kaito.auth.Dao.Entity;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    private String userID;

    private String userName;

    private String userPwd;

    private int roleID;

    private String token;


    private Boolean isDelete = false;
}
