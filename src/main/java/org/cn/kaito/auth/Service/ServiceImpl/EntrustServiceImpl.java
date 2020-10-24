package org.cn.kaito.auth.Service.ServiceImpl;

import org.cn.kaito.auth.Service.EntrustService;
import org.springframework.stereotype.Service;

@Service
public class EntrustServiceImpl implements EntrustService {
    @Override
    public void takeBackEntrust(int entrustID) {
        System.out.println("已经收回权限");

    }
}
