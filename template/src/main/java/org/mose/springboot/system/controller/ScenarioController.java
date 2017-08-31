package org.mose.springboot.system.controller;

import org.mose.springboot.metronic.modal.Pagination;
import org.mose.springboot.spring.ResourceConfiguration;
import org.mose.springboot.springmvc.controller.ViewController;
import org.mose.springboot.system.modal.Scenario;
import org.mose.springboot.system.service.ScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
     * 该控制器管理的主viewName，其下包含的所有view的激活侧边栏都为该ViewName
     */
    String indexPageUrl = "/system/scenario/index";

    /**
     * 展示场景index视图
     *
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView indexPage(Pagination pagination) {
        pagination.setUrl(resourceConfiguration.getDynamicResourceServerUrl() + indexPageUrl);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pagination", pagination.createHtml());
        modelAndView.addObject("scenarios", scenarioService.getScenarioList());
        ViewController.setViewDecoratorUrl(modelAndView, indexPageUrl);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        ViewController.setViewDecoratorUrl(modelAndView, "/system/scenario/add", indexPageUrl);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addScenario(Scenario scenario) {
//        if (flag == 0)
//            return "forward:/error.htm?resultCode=" + GlobalCode.OPERA_FAILURE;//msg=" + StringUtil.encodeUrl("资源新增失败");
//        else if (flag == 2)
//            return "forward:/error.htm?resultCode=" + GlobalCode.CODEEXIST_FAILURE;//msg=" + StringUtil.encodeUrl("资源代码已存在");
//        else
//            return "forward:/success.htm?resultCode=" + GlobalCode.SAVE_SUCCESS;//msg=" + StringUtil.encodeUrl("资源新增成功");
//        return "redirect:/system/scenario/index.htm";
        ModelAndView modelAndView = new ModelAndView();
        ViewController.setViewDecoratorUrl(modelAndView, "/error/error", indexPageUrl);
        return modelAndView;
    }
}
