package org.cn.kaito.auth.DTO;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String userID;
    private String type;

    public UserDTO(String username, String userID, String type) {
        this.username = username;
        this.userID = userID;
        this.type = type;
    }
}
