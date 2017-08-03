package org.mose.springboot.springsecurity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: 基于Spring Security的Http Session timeout设置方式
 *
 * 继承SavedRequestAwareAuthenticationSuccessHandler，认证成功后可以重定向到认证前用户请求的地址
 *
 * @Author: 靳磊
 * @Date: 2017/8/2 11:33
 */
@Component
@ConfigurationProperties("custom.http.session")
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    /**
     * session失效时间，默认30分钟
     */
    private int timeout = 60 * 30;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        super.onAuthenticationSuccess(request, response, authentication);
        //设置session的失效时间
        request.getSession().setMaxInactiveInterval(timeout);
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
