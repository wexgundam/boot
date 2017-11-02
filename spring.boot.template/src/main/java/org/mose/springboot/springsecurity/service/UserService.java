package org.mose.springboot.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: 靳磊
 * @Date: 2017/7/21:20
 */
@Component
public class UserService {
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    MethodSecurityMetadataSource methodSecurityMetadataSource;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String preAuthorizeMethod() {
        return "This method is protected by Spring Security Annotation.";
    }

    public String javaConfiguredMethod() {
        return "This method is protected by Spring Security Java Configuration.";
    }

    @PostFilter("((filterObject.contains('2') or filterObject.contains('4')) and hasRole('ROLE_USER')) or hasRole('ROLE_ADMIN')")
    public List<String> postFilter() {
        List<String> list = new ArrayList<>();
        for (int index = 0; index < 10; index++) {
            list.add("Item" + index);
        }
        return list;
    }
}
