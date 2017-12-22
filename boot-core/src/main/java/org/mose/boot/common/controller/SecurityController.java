package org.mose.boot.common.controller;

import org.mose.boot.common.service.ResourceService;
import org.mose.boot.common.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * what:    认证、授权等安全相关的控制器. <br/>
 * when:    认证、授权相关服务处理.<br/>
 *
 * @author 靳磊 created on 2017/12/22
 */
@Controller("/")
public class SecurityController {
    /**
     * 资源服务
     */
    @Autowired
    private ResourceService resourceService;
    /**
     * 视图服务
     */
    @Autowired
    private ViewService viewService;


    /**
     * 该控制器管理的主viewName，其下包含的所有view的激活侧边栏都为该ViewName
     */
    private String indexViewName = "/index";
    /**
     * 安全模块主页url
     */
    private String indexPageUrl = null;

    /**
     * what:    获取安全模块主页url
     *
     * @return
     */
    private String getIndexPageUrl() {
        indexPageUrl = indexPageUrl == null ? resourceService.getDynamicResourceServerUrl() + indexViewName + ".htm" : indexPageUrl;
        return indexPageUrl;
    }

    /**
     * what:    处理登录请求
     *
     * @return
     */
    @RequestMapping("/login.htm")
    public String loginPage() {
        return "login";
    }

    /**
     * what:    处理访问安全主页请求
     *
     * @return
     */
    @RequestMapping(value = {"/index", "/"})
    public ModelAndView indexPage() {
        ModelAndView modelAndView = viewService.forwardDecorateView(indexViewName, getIndexPageUrl());
        return modelAndView;
    }

    /**
     * what:    处理锁定当前页面请求
     *
     * @return
     */
    @RequestMapping("/lock.htm")
    public String lockPage() {
        return "/lock";
    }
}
