package org.cn.kaito.auth.Security;


import org.cn.kaito.auth.Dao.Entity.UserEntity;
import org.cn.kaito.auth.Dao.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityTokenUtil {

    @Autowired
    UserRepository userRepository;


    public Optional<String> getUserIDByToken(String token)  {
        Optional<UserEntity> userEntity = userRepository.getUserEntityByToken(token);
        return Optional.of(userEntity.get().getUserID());
    }

    public boolean validateToken(String userID, String token) {
        Optional<UserEntity> userEntity = userRepository.getUserEntityByToken(token);
        if (userEntity.isPresent()){
            return userID.equals(userEntity.get().getUserID());
        }else {
            return false;
        }
    }
}
