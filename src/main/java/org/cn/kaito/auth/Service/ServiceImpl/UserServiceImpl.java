package org.cn.kaito.auth.Service.ServiceImpl;

import org.cn.kaito.auth.Controller.Request.ChangePasswordRequest;
import org.cn.kaito.auth.Controller.Request.UserLoginRequest;
import org.cn.kaito.auth.Controller.Response.GetUserListResponse;
import org.cn.kaito.auth.Controller.Response.UserLoginResponse;
import org.cn.kaito.auth.DTO.UserDTO;
import org.cn.kaito.auth.Dao.Entity.UserEntity;
import org.cn.kaito.auth.Dao.Repository.UserRepository;
import org.cn.kaito.auth.Exception.CustomerException;
import org.cn.kaito.auth.Service.UserService;
import org.cn.kaito.auth.Utils.StatusEnum;
import org.cn.kaito.auth.Utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserLoginResponse login(UserLoginRequest userLoginRequest) throws CustomerException {
        UserEntity userEntity = userRepository.getUserEntityByUserNameAndUserPwd(
                    userLoginRequest.getUsername(),userLoginRequest.getPassword())
                .orElseThrow(()->new CustomerException(StatusEnum.CANT_FIND_USER));
        if (userEntity.getIsDelete()){
            throw new CustomerException(StatusEnum.ACCOUNT_HAS_BEEN_DELETED);
        }
        userEntity.setToken(TokenUtil.createToken());
        userRepository.save(userEntity);
        return new UserLoginResponse(userEntity.getToken());
    }

    @Override
    public String getUserIDByToken(String token) {
        return "kaito";
    }

    @Override
    public void changePassword(String userID, ChangePasswordRequest changePasswordRequest) throws CustomerException {
        UserEntity userEntity = userRepository.getUserEntityByUserIDAndUserPwd(userID,changePasswordRequest.getOld_pwd())
                .orElseThrow(()->new CustomerException(StatusEnum.WRONG_ACCOUNT_OR_PASSWORD));
        userEntity.setUserPwd(changePasswordRequest.getNew_pwd());
        userRepository.save(userEntity);
    }

    @Override
    public GetUserListResponse getFriendList(String type) throws CustomerException {
        if (type.isEmpty()){
            List<UserDTO> users = userRepository.getUserDTOs();
            GetUserListResponse getUserListResponse = new GetUserListResponse();
            getUserListResponse.setUsers(users);
            return getUserListResponse;
        }else{
            List<UserDTO> users = userRepository.getUserDTOsByType(type);
            GetUserListResponse getUserListResponse = new GetUserListResponse();
            getUserListResponse.setUsers(users);
            return getUserListResponse;
        }

    }

}
