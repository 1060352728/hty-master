package com.likui.hty.service;

import com.likui.hty.client.UserFeignApi;
import com.likui.hty.member.bean.RoleInfo;
import com.likui.hty.member.bean.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service("userDetailsService")
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserFeignApi userFeignApi;


    @Override
    public UserDetails loadUserByUsername(String username) {

        UserInfo user = userFeignApi.findByUsername(username);

        log.info("loadByUsername:{}", user.toString());

        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                this.obtainGrantedAuthorities(user));
    }

    /**
     * 获得登录者所有角色的权限集合.
     *
     * @param user
     * @return
     */
    private Set<GrantedAuthority> obtainGrantedAuthorities(UserInfo user) {
        RoleInfo role = userFeignApi.findRoleByRoleId(user.getRole());
        Set<RoleInfo> roles = new HashSet<>();
        roles.add(role);
        log.info("user:{},roles:{}", user.getUsername(), roles);
        return roles.stream()
                .map(roleInfo -> new SimpleGrantedAuthority(roleInfo.getId().toString()))
                .collect(Collectors.toSet());
    }
}
