package org.mose.boot.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: 错误页面控制器
 *
 * @Author: 靳磊
 * @Date: 2017/8/30 8:53
 */
@Controller
@RequestMapping("/common/result")
public class ResultPageController {
    @RequestMapping("/404.htm")
    public String errorPage404(HttpServletRequest request, HttpServletResponse response) {
        return "/common/result/404";
    }

    @RequestMapping("/500.htm")
    public String errorPage500(HttpServletRequest request, HttpServletResponse response) {
        return "/common/result/500";
    }

    @RequestMapping(value = "/denied.htm")
    public String accessDenied(HttpServletRequest request) {
        return "/common/result/denied";
    }
}
