package org.mose.springboot.system.controller;

import org.mose.springboot.metronic.modal.Pagination;
import org.mose.springboot.spring.ResourceConfiguration;
import org.mose.springboot.springmvc.service.ViewService;
import org.mose.springboot.system.modal.Scenario;
import org.mose.springboot.system.service.ScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

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
    @Autowired
    private ViewService viewService;

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

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("pagination", pagination.createHtml());
        parameters.put("scenarios", scenarioService.getScenarioList());

        ModelAndView modelAndView = viewService.forwardDecoratePage(indexPageUrl, parameters);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView modelAndView = viewService.forwardDecoratePage("/system/scenario/add", indexPageUrl);
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

//        ModelAndView modelAndView = viewService.forwardFailPage("新建场景操作失败，请检查提交的信息是否正确！", indexPageUrl);
        ModelAndView modelAndView = viewService.forwardSuccessPage("新建场景已保存！", indexPageUrl, indexPageUrl);
        return modelAndView;
    }
}
