package org.mose.common.controller;

import org.mose.common.service.SidebarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * what:    视图控制器. <br/>
 * when:    当页面含有公共元素被渲染时.<br/>
 * how:     发送请求和参宿到视图控制器.<br/>
 *
 * @author 靳磊 created on 2017/12/22
 */
@Controller
@RequestMapping("/common")
public class ViewController {
    /**
     * 侧边菜单服务，生成侧边菜单Html
     */
    @Autowired
    private SidebarService sidebarService;


    /**
     * what:    附加视图公共数据，并最终转发到目标视图
     *
     * @param targetViewName       最终要渲染的目标视图名
     * @param activeSidebarItemUrl 激活的SidebarItem Url
     *
     * @return
     */
    @RequestMapping("/view.htm")
    public ModelAndView decoratePage(@RequestParam String targetViewName, @RequestParam String activeSidebarItemUrl) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("sidebarHtml", sidebarService.createHtml(userDetails.getUsername()));
        modelAndView.addObject("activeSidebarItemUrl", activeSidebarItemUrl);
        modelAndView.setViewName(targetViewName);
        return modelAndView;
    }
}
