package org.cn.kaito.auth.Dao.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PermissionRole")
public class PermissionRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    private int RoleID;

    private int PermissionID;
}
