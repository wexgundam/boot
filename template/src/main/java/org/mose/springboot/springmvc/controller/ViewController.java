package org.mose.springboot.springmvc.controller;

import org.mose.springboot.metronic.service.SidebarService;
import org.mose.springboot.system.service.ScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description: 视图控制器，组织生成视图公用数据
 *
 * @Author: 靳磊
 * @Date: 2017/8/18 14:46
 */
@Controller
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
     * 设置给定的ModelAndView，将其viewName设为视图控制器地址，用于附加视图公共数据
     *
     * 给定的viewName是目标展示视图名称
     *
     * @param modelAndView
     * @param targetViewName
     * @param activeSidebarItemUrl 激活的SidebarItem Url
     */
    public static void decoratePage(ModelAndView modelAndView, String targetViewName, String activeSidebarItemUrl) {
        modelAndView.setViewName(getViewDecoratorUrl(targetViewName, activeSidebarItemUrl));
    }

    /**
     * 设置给定的ModelAndView，将其viewName设为视图控制器地址，用于附加视图公共数据
     *
     * 给定的viewName是目标展示视图名称
     *
     * @param modelAndView
     * @param targetViewName
     */
    public static void decoratePage(ModelAndView modelAndView, String targetViewName) {
        decoratePage(modelAndView, targetViewName, targetViewName);
    }

    /**
     * 跳转到错误页面
     *
     * @param modelAndView
     * @param message              失败描述信息
     * @param activeSidebarItemUrl
     */
    public static void decorateErrorPage(ModelAndView modelAndView, String message, String activeSidebarItemUrl) {
        modelAndView.addObject("message", message);
        decoratePage(modelAndView, "/common/result/error", activeSidebarItemUrl);
    }

    /**
     * 获得转发视图控制器的地址
     *
     * @param targetViewName       跳转目标视图名
     * @param activeSidebarItemUrl 激活的SidebarItem Url
     * @return
     */
    public static String getViewDecoratorUrl(String targetViewName, String activeSidebarItemUrl) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("forward:/view?targetViewName=").append(targetViewName).append("&activeSidebarItemUrl=" + activeSidebarItemUrl);
        return stringBuffer.substring(0);
    }

    /**
     * 附加视图公共数据，并最终转发到目标视图
     *
     * @param targetViewName
     * @param activeSidebarItemUrl 激活的SidebarItem Url
     * @return
     */
    @RequestMapping("/view")
    public ModelAndView decoratePage(@RequestParam String targetViewName, @RequestParam String activeSidebarItemUrl) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("sidebarItems", scenarioService.getScenarioTree());
        modelAndView.addObject("activeSidebarItemUrl", activeSidebarItemUrl);
        modelAndView.addObject("sidebarHtml", sidebarService.creatHtml(scenarioService.createSidebarItems()));
        modelAndView.setViewName(targetViewName);
        return modelAndView;
    }
}
