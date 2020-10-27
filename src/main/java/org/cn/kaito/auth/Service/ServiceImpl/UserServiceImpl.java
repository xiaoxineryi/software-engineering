package org.cn.kaito.auth.Service.ServiceImpl;

import org.cn.kaito.auth.Controller.Request.ChangePasswordRequest;
import org.cn.kaito.auth.Controller.Request.UserLoginRequest;
import org.cn.kaito.auth.Controller.Response.*;
import org.cn.kaito.auth.DTO.NoticeDTO;
import org.cn.kaito.auth.DTO.UserDTO;
import org.cn.kaito.auth.Dao.Entity.UserEntity;
import org.cn.kaito.auth.Dao.Repository.NoticeRespository;
import org.cn.kaito.auth.Dao.Repository.UserRepository;
import org.cn.kaito.auth.Exception.CustomerException;
import org.cn.kaito.auth.Service.UserService;
import org.cn.kaito.auth.Utils.StatusEnum;
import org.cn.kaito.auth.Utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    NoticeRespository noticeRespository;
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
        UserDTO userDTO  = userRepository.getUserDTOsByID(userEntity.getUserID())
                        .orElseThrow(()->new CustomerException(StatusEnum.CANT_FIND_USER));
        return new UserLoginResponse(userEntity.getToken(),userDTO);
    }

    @Override
    public String getUserIDByToken(String token) throws CustomerException {
        return userRepository.findUserEntityByToken(token)
                .orElseThrow(()->new CustomerException(StatusEnum.CANT_FIND_USER)).getUserID();
    }

    @Override
    public void changePassword(String userID, ChangePasswordRequest changePasswordRequest) throws CustomerException {
        UserEntity userEntity = userRepository.getUserEntityByUserIDAndUserPwd(userID,changePasswordRequest.getOld_pwd())
                .orElseThrow(()->new CustomerException(StatusEnum.WRONG_ACCOUNT_OR_PASSWORD));
        userEntity.setUserPwd(changePasswordRequest.getNew_pwd());
        userRepository.save(userEntity);
    }

    @Override
    public List<UserDTO> getUserList(String type) throws CustomerException {
        if (type.isEmpty()){
            List<UserDTO> users = userRepository.getUserDTOs();
            return users;
        }else{
            List<UserDTO> users = userRepository.getUserDTOsByType(type);
            return users;
        }

    }

    @Override
    public NoticeResponse getNotices(String uid,int page) {
        Pageable pageable = PageRequest.of(page,10);
        List<NoticeDTO> notices = noticeRespository.findNoticeDTOSByReceiver(uid,pageable);
        NoticeResponse noticeResponse = new NoticeResponse();
        noticeResponse.setNotices(notices);
        noticeRespository.updateNotice(uid);
        return noticeResponse;
    }

    @Override
    public UserDTO getUserByID(String uid) throws CustomerException {
        UserDTO userDTO  = userRepository.getUserDTOsByID(uid)
                            .orElseThrow(()->new CustomerException(StatusEnum.CANT_FIND_USER));

        return userDTO;
    }

    @Override
    public NoticeCountResponse getUnreadNotices(String uid) {
        int cnt = noticeRespository.countUnreadNotice(uid);
        NoticeCountResponse noticeCountResponse = new NoticeCountResponse();
        noticeCountResponse.setCount(cnt);
        System.out.println(cnt);
        return noticeCountResponse;
    }

}
