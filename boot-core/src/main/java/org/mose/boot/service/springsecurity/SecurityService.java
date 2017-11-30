package org.mose.boot.service.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

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
