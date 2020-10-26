package org.cn.kaito.auth.Service;

import org.cn.kaito.auth.Controller.Request.ChangePasswordRequest;
import org.cn.kaito.auth.Controller.Request.UserLoginRequest;
import org.cn.kaito.auth.Controller.Response.GetUserByIDResponse;
import org.cn.kaito.auth.Controller.Response.GetUserListResponse;
import org.cn.kaito.auth.Controller.Response.NoticeResponse;
import org.cn.kaito.auth.Controller.Response.UserLoginResponse;
import org.cn.kaito.auth.Exception.CustomerException;

public interface UserService {
    UserLoginResponse login(UserLoginRequest userLoginRequest) throws CustomerException;

    String getUserIDByToken(String token);

    void changePassword(String userID,ChangePasswordRequest changePasswordRequest) throws CustomerException;

    GetUserListResponse getFriendList(String type) throws CustomerException;

    NoticeResponse getNotices(String uid,int page);

    GetUserByIDResponse getUserByID(String uid) throws CustomerException;
}
