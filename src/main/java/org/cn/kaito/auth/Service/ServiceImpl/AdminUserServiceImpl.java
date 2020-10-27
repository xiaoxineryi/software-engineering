package org.cn.kaito.auth.Service.ServiceImpl;

import org.cn.kaito.auth.Controller.Request.AddRoleRequest;
import org.cn.kaito.auth.Controller.Request.EditUserRequest;
import org.cn.kaito.auth.Controller.Request.RegisterRequest;
import org.cn.kaito.auth.Controller.Response.RegisterResponse;
import org.cn.kaito.auth.Dao.Entity.UserEntity;
import org.cn.kaito.auth.Dao.Repository.UserRepository;
import org.cn.kaito.auth.Exception.CustomerException;
import org.cn.kaito.auth.Service.AdminUserService;
import org.cn.kaito.auth.Utils.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@PropertySource(value = "classpath:application-devConfig.yml")
@ConfigurationProperties(prefix = "user-info")
public class AdminUserServiceImpl implements AdminUserService {
    @Value("${deleteID}")
    int DeleteID;

    @Autowired
    UserRepository userRepository;

    @Override
    public RegisterResponse addUser(RegisterRequest registerRequest) {
        UserEntity userEntity = new UserEntity();
        String pwd = "123456";
        userEntity.setRoleID(registerRequest.getRoleID());
        userEntity.setUserName(registerRequest.getUsername());
        userEntity.setUserPwd(pwd);
        String userRandomID = (UUID.randomUUID().toString().substring(10,20));
        // 直到随机一个不重复的用户id 才保存
        while (userRepository.getUserEntityByUserID(userRandomID).isPresent()){
            userRandomID = (UUID.randomUUID().toString().substring(10,20));
        }
        userEntity.setUserID(userRandomID);
        userRepository.save(userEntity);
        return new RegisterResponse(pwd);
    }

    @Override
    public void editUser(EditUserRequest editUserRequest) throws CustomerException {
        UserEntity userEntity = userRepository.getUserEntityByUserID(editUserRequest.getUserID())
                .orElseThrow(()->new CustomerException(StatusEnum.CANT_FIND_USER));
        userEntity.setRoleID(editUserRequest.getRoleID());
        userEntity.setUserName(editUserRequest.getUsername());
        userEntity.setIsDelete(editUserRequest.isDelete());
        if (editUserRequest.isDelete()){
            userEntity.setRoleID(DeleteID);
        }
        userRepository.save(userEntity);
    }


}
