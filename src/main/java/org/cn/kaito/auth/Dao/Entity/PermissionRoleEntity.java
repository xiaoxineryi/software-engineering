package org.cn.kaito.auth.Dao.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.access.PermissionEvaluator;

import javax.persistence.*;

@Data
@Entity

@Table(name = "PermissionRole")
public class PermissionRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    private int roleID;

    private int permissionID;
}
