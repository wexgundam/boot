package org.mose.springboot.metronic.controller;

import org.mose.springboot.sidebar.dao.AbcRepository;
import org.mose.springboot.sidebar.dao.AbcStreamRepository;
import org.mose.springboot.system.service.SidebarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/2.
 */
@Controller
public class MetronicController {
    @Autowired
    private SidebarService sidebarService;

    @RequestMapping("/metronic")
    public ModelAndView decorate(@RequestParam String viewName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("sidebarItems", sidebarService.getSidebarItems());
        modelAndView.setViewName(viewName);
        return modelAndView;
    }
}
