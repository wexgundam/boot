package org.mose.springboot.system.controller;

import org.mose.springboot.metronic.modal.Pagination;
import org.mose.springboot.spring.ResourceConfiguration;
import org.mose.springboot.springmvc.controller.ViewController;
import org.mose.springboot.system.service.ScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description: 场景控制器
 *
 * @Author: 靳磊
 * @Date: 2017/8/18 14:52
 */
@Controller
@RequestMapping("/system/scenario")
public class ScenarioController {
    @Autowired
    private ResourceConfiguration resourceConfiguration;
    @Autowired
    private ScenarioService scenarioService;

    /**
     * 展示场景index视图
     *
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView indexPage(Pagination pagination) {
        pagination.setUrl(resourceConfiguration.getDynamicResourceServerUrl() + "/system/scenario/index");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pagination", pagination.createHtml());
        modelAndView.addObject("scenarios", scenarioService.getScenarioList());
        ViewController.setViewDecoratorUrl(modelAndView, "/system/scenario/index");
        return modelAndView;
    }
}
