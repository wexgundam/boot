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
     */
    public static void setViewDecoratorUrl(ModelAndView modelAndView, String targetViewName) {
        modelAndView.setViewName(getViewDecoratorUrl(targetViewName));
    }

    /**
     * 获得转发视图控制器的地址
     *
     * @param targetViewName
     * @return
     */
    public static String getViewDecoratorUrl(String targetViewName) {
        return "forward:/view?targetViewName=" + targetViewName;
    }

    /**
     * 附加视图公共数据，并最终转发到目标视图
     *
     * @param targetViewName
     * @return
     */
    @RequestMapping("/view")
    public ModelAndView decorate(@RequestParam String targetViewName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("sidebarItems", scenarioService.getScenarioTree());
        modelAndView.addObject("viewName", targetViewName);
        modelAndView.addObject("sidebarHtml", sidebarService.creatHtml(scenarioService.createSidebarItems()));
        modelAndView.setViewName(targetViewName);
        return modelAndView;
    }
}
