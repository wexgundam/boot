package org.mose.boot.common.controller;

import org.mose.boot.common.service.SidebarService;
import org.mose.boot.system.service.ScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description: 视图控制器，组织生成视图公用页面组件
 *
 * @Author: 靳磊
 * @Date: 2017/8/18 14:46
 */
@Controller
@RequestMapping("/common")
public class ViewController {
    /**
     * 模块服务，用于生成侧边菜单
     */
    @Autowired
    private ScenarioService scenarioService;
    /**
     * 侧边菜单服务，生成侧边菜单Html
     */
    @Autowired
    private SidebarService sidebarService;


    /**
     * 附加视图公共数据，并最终转发到目标视图
     *
     * @param targetViewName
     * @param activeSidebarItemUrl 激活的SidebarItem Url
     * @return
     */
    @RequestMapping("/view.htm")
    public ModelAndView decoratePage(@RequestParam String targetViewName, @RequestParam String activeSidebarItemUrl) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("sidebarHtml", sidebarService.creatHtml(scenarioService.createSidebarItems()));
        modelAndView.addObject("activeSidebarItemUrl", activeSidebarItemUrl);
        modelAndView.setViewName(targetViewName);
        return modelAndView;
    }
}
