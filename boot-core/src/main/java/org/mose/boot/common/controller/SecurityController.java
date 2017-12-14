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
 * Created by Administrator on 2017/8/2.
 */
@Controller("/")
public class SecurityController {
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private ViewService viewService;


    /**
     * 该控制器管理的主viewName，其下包含的所有view的激活侧边栏都为该ViewName
     */
    String indexViewName = "/index";
    String indexPageUrl = null;

    private String getIndexPageUrl() {
        indexPageUrl = indexPageUrl == null ? resourceService.getDynamicResourceServerUrl() + indexViewName + ".htm" : indexPageUrl;
        return indexPageUrl;
    }

    @RequestMapping("/login.htm")
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = {"/index", "/"})
    public ModelAndView indexPage() {
        ModelAndView modelAndView = viewService.forwardDecorateView(indexViewName, getIndexPageUrl());
        return modelAndView;
    }

    @RequestMapping("/lock.htm")
    public String lockPage() {
        return "/lock";
    }
}
