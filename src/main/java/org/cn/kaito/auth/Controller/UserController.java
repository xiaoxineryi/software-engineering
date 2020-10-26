package org.cn.kaito.auth.Controller;

import org.cn.kaito.auth.Controller.Request.ChangePasswordRequest;
import org.cn.kaito.auth.Controller.Request.UserLoginRequest;
import org.cn.kaito.auth.Controller.Response.GetUserListResponse;
import org.cn.kaito.auth.Controller.Response.NoticeCountResponse;
import org.cn.kaito.auth.Controller.Response.NoticeResponse;
import org.cn.kaito.auth.Controller.Response.UserLoginResponse;
import org.cn.kaito.auth.Dao.Entity.EntrustEntity;
import org.cn.kaito.auth.Dao.Repository.EntrustRepository;
import org.cn.kaito.auth.Exception.CustomerException;
import org.cn.kaito.auth.Service.UserService;
import org.cn.kaito.auth.Utils.DateCronUtil;
import org.cn.kaito.auth.Utils.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    UserService userService;

    @Autowired
    EntrustRepository entrustRepository;

    @PostMapping("/login")
    public UserLoginResponse login(@RequestBody UserLoginRequest userLogin) throws CustomerException {
        return userService.login(userLogin);
    }

    @PostMapping("/changePassword")
    public void changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) throws CustomerException {
        String userID = getUid();
        userService.changePassword(userID,changePasswordRequest);
    }

    @GetMapping("/list")
    public GetUserListResponse getUserList(@RequestParam(name = "type") String type) throws CustomerException {
        return userService.getFriendList(type);
    }

    @GetMapping("/notice")
    public NoticeResponse getNotices(@RequestParam(name = "page") int page){
        NoticeResponse noticeResponse = userService.getNotices(getUid(),page);
        return noticeResponse;
    }

    @GetMapping("/cntUnreadMsg")
    public NoticeCountResponse cntUnreadMsg(){
        return userService.getUnreadNotices(getUid());
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
