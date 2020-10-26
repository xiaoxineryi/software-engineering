package org.cn.kaito.auth.Controller.Response;

import lombok.Data;
import org.cn.kaito.auth.DTO.NoticeDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class NoticeResponse {
    List<NoticeDTO> notices  = new ArrayList<>();
}
