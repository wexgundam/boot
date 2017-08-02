package org.mose.spring.boot.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: 基于Spring Security的Http Session timeout设置方式
 *
 * @Author: 靳磊
 * @Date: 2017/8/2 11:33
 */
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    /**
     * session失效时间，默认30分钟
     */
    private int sessionTimeoutInSeconds = 60 * 30;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        super.onAuthenticationSuccess(request, response, authentication);
        //设置session的失效时间
        request.getSession().setMaxInactiveInterval(sessionTimeoutInSeconds);
    }

    public int getSessionTimeoutInSeconds() {
        return sessionTimeoutInSeconds;
    }

    public void setSessionTimeoutInSeconds(int sessionTimeoutInSeconds) {
        this.sessionTimeoutInSeconds = sessionTimeoutInSeconds;
    }
}
