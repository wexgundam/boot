package org.mose.springboot.springmvc.controller;

import org.mose.springboot.system.service.SidebarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2017/8/2.
 */
@Controller
public class ViewController {
    @Autowired
    private SidebarService sidebarService;

    public static void setViewDecoratorUrl(ModelAndView modelAndView, String viewName) {
        modelAndView.setViewName(getViewDecoratorUrl(viewName));
    }

    public static String getViewDecoratorUrl(String viewName) {
        return "forward:/view?viewName=" + viewName;
    }

    @RequestMapping("/view")
    public ModelAndView decorate(@RequestParam String viewName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("sidebarItems", sidebarService.getSidebarItems());
        modelAndView.addObject("viewName", viewName);
        modelAndView.addObject("sidebarHtml", sidebarService.getSidebarHtml());
        modelAndView.setViewName(viewName);
        return modelAndView;
    }
}
