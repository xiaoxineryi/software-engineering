package org.cn.kaito.auth.Dao.Repository;

import org.cn.kaito.auth.Dao.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer>{
    Optional<UserEntity> findUserEntityByToken(String token);

    Optional<UserEntity> getUserEntityByUserID(String userID);

}