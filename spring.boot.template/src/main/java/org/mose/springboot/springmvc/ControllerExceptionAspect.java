package org.mose.springboot.springmvc;

import org.mose.springboot.springmvc.service.ViewService;
import org.mose.springboot.util.json.JsonUtil;
import org.mose.springboot.util.string.StringUtil;
import org.mose.springboot.util.web.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:监听Controller抛出的所有Exception，Controller无须配置@Exception处理方法
 *
 * @Author: 靳磊
 * @Date: 2017/6/22:20
 */
@ControllerAdvice
public class ControllerExceptionAspect {
    private static Logger logger = LoggerFactory.getLogger("controllerLog");
    @Autowired
    private ViewService viewService;

    @ExceptionHandler(Exception.class)
    public ModelAndView exception(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        logger.error(ex.getMessage());
        //判断是否是Ajax请求
        boolean isAjaxRequest = StringUtil.checkAjaxRequest(request);// this.isAjaxRequest(request);
        //获取异常的详细信息
        if (isAjaxRequest) {
            String msg = "{\"flag\":false,\"msg\":" + ex.getMessage() + "}";
            WebUtil.out(response, JsonUtil.toStr(msg));
            return null;
        } else {
            ModelAndView modelAndView = viewService.forwardExceptionView(ex.getMessage(), null, null);
            return modelAndView;
        }
    }
}
