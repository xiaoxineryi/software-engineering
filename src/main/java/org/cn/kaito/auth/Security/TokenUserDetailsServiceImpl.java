package org.cn.kaito.auth.Security;


import org.cn.kaito.auth.DTO.PermissionDTO;
import org.cn.kaito.auth.Dao.Entity.UserEntity;
import org.cn.kaito.auth.Dao.Repository.RolePermissionRepository;
import org.cn.kaito.auth.Dao.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 获取UserDetails实例
 */
@Service
public class TokenUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RolePermissionRepository rolePermissionRepository;

    @Override
    public UserDetails loadUserByUsername(String userID) throws UsernameNotFoundException{
        Optional<UserEntity> userEntity = userRepository.getUserEntityByUserID(userID);
        UserEntity user = userEntity.orElseThrow(()->new UsernameNotFoundException
                (String.format("No user found with name %s",userID)));
        List<PermissionDTO> permissionDTOS = rolePermissionRepository.getPermissionByUserID(userID);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (PermissionDTO permission:permissionDTOS){
            authorities.add(new SimpleGrantedAuthority(String.format("%s-%s",
                    permission.getPermission_target(),permission.getPermission_name())));
        }
        return new SecurityUser(userID,user.getUserPwd(),authorities);

    }
}
