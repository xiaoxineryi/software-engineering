package org.cn.kaito.auth.Dao.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Permission")
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int permissionID;

    private String permissionName;

    private String permissionTarget;
}
