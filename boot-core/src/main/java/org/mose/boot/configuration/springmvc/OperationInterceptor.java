/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:boot
 * Module Name:TODO
 */
package org.mose.boot.configuration.springmvc;

import org.mose.boot.util.date.DateUtil;
import org.mose.boot.util.string.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.support.NullValue;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * what:    操作拦截器，记录操作日志. <br/>
 *
 * @author 靳磊 created on 2017/12/19
 */
public class OperationInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger("operationLogger");

    /**
     * what:    拦截请求，记录操作日志
     *
     * @param request
     * @param response
     * @param handler
     *
     * @return
     *
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication == null ? "null" : authentication.getName();
        String ip = StringUtil.getIp(request);
        String method = request.getMethod();
        String path = request.getServletPath();
        String parameters = StringUtil.getOperationParameters(request);
        int status = response.getStatus();
        //记操作日志
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
