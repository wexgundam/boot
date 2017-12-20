/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:boot
 * Module Name:TODO:Module
 */
package org.mose.boot.system.service;

import org.mose.boot.system.modal.Authority;
import org.mose.boot.system.modal.User;
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
 * what:    (这里用一句话描述这个类的作用). <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2017/12/20
 */
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    public static final String SPRING_SECURITY_GRANTED_AUTHORITY_PREFIX = "ROLE_";
    @Autowired
    UserService userService;

    @Override
    @CacheEvict(value = "sidebarCache")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.queryUserWithAuthoritiesByUsername(username);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (user.getAuthorities() != null && !user.getAuthorities().isEmpty()) {
            for (Authority authority : user.getAuthorities()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(SPRING_SECURITY_GRANTED_AUTHORITY_PREFIX + authority.getName()));
            }
        }
        org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(), user.isCredentialsNonExpired(), user.isAccountNonLocked(), grantedAuthorities);
        return userDetails;
    }
}
