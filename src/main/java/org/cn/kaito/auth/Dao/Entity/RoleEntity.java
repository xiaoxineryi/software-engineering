package org.cn.kaito.auth.Dao.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "RoleD")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleID;

    private String roleName;
}
