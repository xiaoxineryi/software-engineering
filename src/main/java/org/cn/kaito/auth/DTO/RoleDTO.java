package org.cn.kaito.auth.DTO;

import lombok.Data;

@Data
public class RoleDTO {
    private int id;
    private String name;

    public RoleDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
