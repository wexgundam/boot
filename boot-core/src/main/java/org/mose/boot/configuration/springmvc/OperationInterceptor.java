/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:boot
 * Module Name:TODO
 */
package org.mose.boot.configuration.springmvc;

import org.mose.boot.system.modal.User;
import org.mose.boot.util.date.DateUtil;
import org.mose.boot.util.string.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * what:    (这里用一句话描述这个类的作用). <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2017/12/19
 */
public class OperationInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger("operationLogger");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String ip = StringUtil.getIp(request);
        String method = request.getMethod();
        String path = request.getServletPath();
        String parameters = StringUtil.getOperationParameters(request);
        int status = response.getStatus();
        //记操作日志
        StringBuilder log = new StringBuilder();
        logger.info("Operation ##############################");
        logger.info("[      date] " + DateUtil.getSystemTime());
        logger.info("[  username] " + username);
        logger.info("[        ip] " + ip);
        logger.info("[      path] " + path);
        logger.info("[    method] " + method);
        logger.info("[parameters] " + parameters);
        logger.info("[    status] " + status);
        return super.preHandle(request, response, handler);
    }
}
