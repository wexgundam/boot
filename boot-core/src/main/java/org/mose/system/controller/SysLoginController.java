/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:cdpf_v1
 * Module Name:core
 */
package org.mose.system.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * what:    系统登录校验，及首页显示
 *
 * @author 孔垂云 created on 2017年6月13日
 */
@RequestMapping("/")
@RestController
public class SysLoginController {
    /**
     * what:    进入系统登录界面
     *
     * @param request  request
     * @param response response
     *
     * @return login
     *
     * @author 孔垂云 created on 2017年6月13日
     */
    @RequestMapping("/log")
    public ModelMap login(HttpServletRequest request, HttpServletResponse response) {

        ModelMap modelMap = new ModelMap();
        modelMap.put("name", "test");
        modelMap.put("age", 1);
        modelMap.put("time", new Date());
        return modelMap;
    }
}
