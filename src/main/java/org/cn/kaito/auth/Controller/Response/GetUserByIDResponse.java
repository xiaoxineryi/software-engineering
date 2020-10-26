package org.cn.kaito.auth.Controller.Response;

import lombok.Data;
import org.cn.kaito.auth.DTO.UserDTO;

@Data
public class GetUserByIDResponse {
    public UserDTO user;
}
