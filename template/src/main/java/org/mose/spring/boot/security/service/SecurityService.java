package org.mose.spring.boot.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: 靳磊
 * @Date: 2017/7/21:20
 */
@Service
public class SecurityService {
    @Autowired
    private SessionRegistry sessionRegistry;

    /**
     * 获得当前在线Session总数
     *
     * @return
     */
    public int getActiveSessionCount() {
        return sessionRegistry.getAllPrincipals().size();
    }
}
