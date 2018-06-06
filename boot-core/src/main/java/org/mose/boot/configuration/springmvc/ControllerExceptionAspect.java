package org.mose.boot.configuration.springmvc;

import org.mose.common.service.ViewService;
import org.mose.util.json.JsonUtil;
import org.mose.util.string.StringUtil;
import org.mose.util.web.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * what:    监听Controller抛出的所有Exception，Controller无须配置@Exception处理方法
 *
 * @Author: 靳磊
 * @Date: 2017/6/22:20
 */
@ControllerAdvice
public class ControllerExceptionAspect {
    private static Logger exceptionLogger = LoggerFactory.getLogger("exceptionLogger");
    private static Logger securityLogger = LoggerFactory.getLogger("securityLogger");

    /**
     * 视图服务
     */
    @Autowired
    private ViewService viewService;

    /**
     * what:  处理控制器抛出的异常
     *
     * @param request
     * @param response
     * @param ex
     *
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView exception(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        exceptionLogger.error("Controller Exception ##############################", ex);

        //判断是否是Ajax请求
        boolean isAjaxRequest = StringUtil.checkAjaxRequest(request);// this.isAjaxRequest(request);
        //获取异常的详细信息
        if (isAjaxRequest) {
            String msg = "{\"flag\":false,\"msg\":" + ex.getMessage() + "}";
            WebUtil.out(response, JsonUtil.toString(msg));
            return null;
        } else {
            ModelAndView modelAndView = viewService.forwardExceptionView(ex.getMessage(), null, null);
            return modelAndView;
        }
    }

    /**
     * what:    处理访问未授权而抛出的拒绝访问异常
     *
     * @param request
     * @param response
     * @param ex
     *
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ModelAndView accessDeniedException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        securityLogger.error("Access Denied Exception ##############################", ex);
        //判断是否是Ajax请求
        boolean isAjaxRequest = StringUtil.checkAjaxRequest(request);// this.isAjaxRequest(request);
        //获取异常的详细信息
        if (isAjaxRequest) {
            String msg = "{\"flag\":false,\"msg\":" + ex.getMessage() + "}";
            WebUtil.out(response, JsonUtil.toString(msg));
            return null;
        } else {
            ModelAndView modelAndView = viewService.forwardDecorateView("/common/result/denied", null);
            return modelAndView;
        }
    }
}
