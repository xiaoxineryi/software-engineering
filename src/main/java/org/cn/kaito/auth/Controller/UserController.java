package org.cn.kaito.auth.Controller;

import org.cn.kaito.auth.Controller.Request.UserLoginRequest;
import org.cn.kaito.auth.Controller.Response.UserLoginResponse;
import org.cn.kaito.auth.Dao.Entity.EntrustEntity;
import org.cn.kaito.auth.Dao.Repository.EntrustRepository;
import org.cn.kaito.auth.Service.UserService;
import org.cn.kaito.auth.Utils.DateCronUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    EntrustRepository entrustRepository;

    @PostMapping("/login")
    public UserLoginResponse login(@RequestBody UserLoginRequest userLogin){
        return null;
    }

    @GetMapping("/test")
    public void test(){
//        EntrustEntity entrustEntity = new EntrustEntity();
//        entrustEntity.setStatus("good");
//        entrustEntity.setSubTask("1");
//        entrustEntity.setEntrustWorker("kaito");
//        Timestamp timestamp = new Timestamp(new Date().getTime());
//        entrustEntity.setEntrustStartDate(timestamp);
//        entrustEntity.setEntrustEndDate(timestamp);
//        entrustRepository.save(entrustEntity);
        EntrustEntity entrustEntity = entrustRepository.getOne(2);
        System.out.println(entrustEntity.getEntrustEndDate());
        System.out.println(DateCronUtil.getCron(entrustEntity.getEntrustEndDate()));
    }
}
