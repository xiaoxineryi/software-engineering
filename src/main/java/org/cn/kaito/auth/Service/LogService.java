package org.cn.kaito.auth.Service;

import org.cn.kaito.auth.DTO.LogDTO;
import org.cn.kaito.auth.Exception.CustomerException;

import java.util.List;

public interface LogService {
    List<LogDTO> getLogs(String uid, String projectID, int page) throws CustomerException;
}
