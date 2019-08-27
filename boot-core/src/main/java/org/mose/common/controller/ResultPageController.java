package org.mose.common.controller;

import org.mose.common.service.ViewService;
import org.mose.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * what:    错误页面控制器. <br/>
 * when:    其他控制器或者容器发送错误时，将错误信息提交到该控制，由该控制器服务错误页面与消息的渲染.<br/>
 * how:     向/common/result/xxx.htm发起请求.<br/>
 *
 * @author 靳磊 created on 2017/12/22
 */
@Controller
@RequestMapping("/")
public class ResultPageController {
    /**
     * what:    404错误渲染页面. <br/>
     *
     * @param request
     * @param response
     *
     * @return
     *
     * @author 靳磊 created on 2017/12/22
     */
    @RequestMapping("/common/result/404.htm")
    public String errorPage404(HttpServletRequest request, HttpServletResponse response) {
        return "/common/result/404";
    }

    /**
     * what:    500错误渲染页面
     *
     * @param request
     * @param response
     *
     * @return
     */
    @RequestMapping("/common/result/500.htm")
    public String errorPage500(HttpServletRequest request, HttpServletResponse response) {
        return "/common/result/500";
    }

    /**
     * what:    访问未授权服务被拒绝后的
     *
     * @param request
     *
     * @return
     */
    @RequestMapping(value = "/common/result/denied.htm")
    public String accessDenied(HttpServletRequest request) {
        return "/common/result/denied";
    }
}
