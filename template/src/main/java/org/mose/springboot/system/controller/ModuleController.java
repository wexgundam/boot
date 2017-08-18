package org.mose.springboot.system.controller;

import org.mose.springboot.springmvc.controller.ViewController;
import org.mose.springboot.system.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description: 模块控制器
 *
 * @Author: 靳磊
 * @Date: 2017/8/18 14:52
 */
@Controller
@RequestMapping("/system/module")
public class ModuleController {
    @Autowired
    private ModuleService moduleService;

    /**
     * 展示模块index视图
     *
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView indexPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("modules", moduleService.getModuleList());
        ViewController.setViewDecoratorUrl(modelAndView, "/system/module/index");
        return modelAndView;
    }
}
