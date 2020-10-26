package org.cn.kaito.auth.DTO;

import lombok.Data;
import org.cn.kaito.auth.Dao.Entity.UserEntity;

@Data
public class UserDTO {
    private String username;
    private String userID;
    private String type;
    private int typeID;
    private boolean isdelete;
    public UserDTO(String username, String userID, String type,int typeID,boolean isdelete) {
        this.username = username;
        this.userID = userID;
        this.type = type;
        this.typeID = typeID;
        this.isdelete = isdelete;
    }
}
