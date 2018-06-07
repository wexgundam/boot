/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:boot
 * Module Name:TODO:Module
 */
package org.mose.system.service;

import org.mose.system.modal.Authority;
import org.mose.system.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * what:    spring security用户认证、授权服务. <br/>
 * <p>
 * 符合spring security的要求，实现了要求的接口和服务
 *
 * @author 靳磊 created on 2017/12/20
 */
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    /**
     * spring security用户权限的前缀
     */
    public static final String SPRING_SECURITY_GRANTED_AUTHORITY_PREFIX = "ROLE_";
    /**
     * 用户服务
     */
    @Autowired
    UserService userService;

    @Override
    @CacheEvict(value = "sidebarCache")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.queryUserWithAuthoritiesByUsername(username);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (user.getAuthorities() != null && !user.getAuthorities().isEmpty()) {
            for (Authority authority : user.getAuthorities()) {
                //给自定义权限增加spring security要求的前缀
                grantedAuthorities.add(new SimpleGrantedAuthority(SPRING_SECURITY_GRANTED_AUTHORITY_PREFIX + authority.getName()));
            }
        }
        org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(), user.isCredentialsNonExpired(), user.isAccountNonLocked(), grantedAuthorities);
        return userDetails;
    }
}
