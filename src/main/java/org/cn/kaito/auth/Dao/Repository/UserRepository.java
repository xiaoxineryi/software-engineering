package org.cn.kaito.auth.Dao.Repository;

import org.cn.kaito.auth.Controller.Response.UserLoginResponse;
import org.cn.kaito.auth.DTO.UserDTO;
import org.cn.kaito.auth.Dao.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,String>{
    Optional<UserEntity> findUserEntityByToken(String token);

    Optional<UserEntity> getUserEntityByUserID(String userID);

    Optional<UserEntity> getUserEntityByUserIDAndUserPwd(String userID,String password);
    Optional<UserEntity> getUserEntityByUserNameAndUserPwd(String userName, String password);

    @Query("select new org.cn.kaito.auth.DTO.UserDTO(u.userName,u.userID,r.roleName,u.roleID,u.isDelete)" +
            "from UserEntity u join RoleEntity r on u.roleID=r.roleID")
    List<UserDTO> getUserDTOs();

    @Query("select new org.cn.kaito.auth.DTO.UserDTO(u.userName,u.userID,r.roleName,u.roleID,u.isDelete)" +
            "from UserEntity u join RoleEntity r on u.roleID=r.roleID where r.roleName=?1")
    List<UserDTO> getUserDTOsByType(String type);

    @Query("select new org.cn.kaito.auth.DTO.UserDTO(u.userName,u.userID,r.roleName,u.roleID,u.isDelete)" +
            "from UserEntity u join RoleEntity r on u.roleID=r.roleID where u.userID=?1")
    Optional<UserDTO> getUserDTOsByID(String uid);
}