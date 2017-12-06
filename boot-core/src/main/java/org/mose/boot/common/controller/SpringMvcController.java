package org.mose.boot.common.controller;

import org.mose.boot.common.service.ResourceService;
import org.mose.boot.common.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2017/8/2.
 */
@Controller("/")
public class SpringMvcController {
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private ViewService viewService;

    @GetMapping("/testE")
    public void testE() throws Exception {
        throw new IllegalStateException("My Exception。");
    }


    /**
     * 该控制器管理的主viewName，其下包含的所有view的激活侧边栏都为该ViewName
     */
    String indexViewName = "/index";
    String indexPageUrl = null;

    private String getIndexPageUrl() {
        indexPageUrl = indexPageUrl == null ? resourceService.getDynamicResourceServerUrl() + indexViewName + ".htm" : indexPageUrl;
        return indexPageUrl;
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = {"/index", "/"})
    public ModelAndView indexPage() {
        ModelAndView modelAndView = viewService.forwardDecorateView(indexViewName, getIndexPageUrl(), null);
        return modelAndView;
    }

    @RequestMapping("/lock")
    public String lockPage() {
        return "/lock";
    }
}
