package org.mose.springboot.springmvc.controller;

import org.mose.springboot.springmvc.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class SpringMvcController {
    @Autowired
    private ViewService viewService;

    /**
     * 利用MappingJackson2HttpMessageConverter实现Json转换
     *
     * @return
     */
    @RequestMapping("/testJson")
    @ResponseBody
    public Map<String, Object> testJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("action", "test json");
        return map;
    }

    @RequestMapping("/exception")
    public void testException() throws Exception {
        throw new Exception("Controller exception.");
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = {"/index", "/"})
    public String indexPage() {
        return "index";
    }

    @RequestMapping("/test")
    public ModelAndView tablePage(@RequestParam(required = false) String color) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("color", color);
        ModelAndView modelAndView = viewService.forwardDecoratePage("test", parameters);
        return modelAndView;
    }
}
