package org.mose.boot.configuration.springmvc;

import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ErrorPageRegistrar;
import org.springframework.boot.web.servlet.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

/**
 * what:    注册错误处理响应页面地址. <br/>
 *
 * @author 靳磊 created on 2017/12/22
 */
@Component
public class CustomErrorPageRegistrar implements ErrorPageRegistrar {

    /**
     * what:    注册错误处理页面地址
     *
     * @param registry
     */
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/common/result/404.htm"));
        registry.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/common/result/500.htm"));
        registry.addErrorPages(new ErrorPage(Exception.class, "/common/result/500.htm"));
    }

}