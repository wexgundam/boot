package org.mose.springboot.system.controller;

import org.mose.springboot.springmvc.controller.ViewController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2017/8/15.
 */
@Controller
@RequestMapping("/system/module")
public class ModuleController {
    @RequestMapping("/index")
    public ModelAndView indexPage() {
        ModelAndView modelAndView = new ModelAndView();
        ViewController.setViewDecoratorUrl(modelAndView, "/system/module/index");
        return modelAndView;
    }
}
