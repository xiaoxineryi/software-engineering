package org.cn.kaito.auth.Controller.Response;

import lombok.Data;
import org.cn.kaito.auth.DTO.UserDTO;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetUserListResponse {
    List<UserDTO> users = new ArrayList<>();
}
