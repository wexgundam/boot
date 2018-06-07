package org.mose.system.controller;

import org.mose.common.service.ResourceService;
import org.mose.common.service.ViewService;
import org.mose.system.modal.Scenario;
import org.mose.system.service.ScenarioService;
import org.mose.util.code.ReturnCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * what:    场景控制器
 *
 * @Author: 靳磊
 * @Date: 2017/8/18 14:52
 */
@Controller
@RequestMapping("/system/scenario")
public class ScenarioController {
    /**
     * 资源地址服务
     */
    @Autowired
    private ResourceService resourceService;
    /**
     * 场景服务
     */
    @Autowired
    private ScenarioService scenarioService;
    /**
     * 视图服务
     */
    @Autowired
    private ViewService viewService;

    /**
     * 该控制器管理的主viewName，其下包含的所有view的激活侧边栏都为该ViewName
     */
    String scenarioIndexViewName = "/system/scenario/index";
    String scenarioIndexPageUrl = null;

    /**
     * what:    获得场景主视图地址
     *
     * @return
     */
    private String getScenarioIndexPageUrl() {
        scenarioIndexPageUrl = scenarioIndexPageUrl == null ? resourceService.getDynamicResourceServerUrl() + scenarioIndexViewName + ".htm" : scenarioIndexPageUrl;
        return scenarioIndexPageUrl;
    }

    /**
     * what:    展示场景index视图
     *
     * @return
     */
    @RequestMapping("/index.htm")
    public ModelAndView scenarioIndexView() {
        ModelAndView modelAndView = viewService.forwardDecorateView(scenarioIndexViewName, getScenarioIndexPageUrl());
        modelAndView.addObject("scenarios", scenarioService.queryAllScenariosList());
        return modelAndView;
    }

    /**
     * what:    请求新增页面
     *
     * @return
     */
    @RequestMapping(value = "/add.htm", method = RequestMethod.GET)
    public ModelAndView addScenarioView() {
        ModelAndView modelAndView = viewService.forwardDecorateView("/system/scenario/add", getScenarioIndexPageUrl());
        String scenarioZTreeJson = scenarioService.createScenarioZTreeJson();
        modelAndView.addObject("scenarioZTreeJson", scenarioZTreeJson);
        return modelAndView;
    }

    /**
     * what:    执行新增操作
     *
     * @param scenario
     *
     * @return
     */
    @RequestMapping(value = "/add.htm", method = RequestMethod.POST)
    public ModelAndView addScenario(Scenario scenario) {
        int returnCode = scenarioService.addScenario(scenario);
        if (ReturnCodeUtil.isFail(returnCode)) {
            return viewService.forwardFailView(returnCode, getScenarioIndexPageUrl());
        } else {
            return viewService.forwardSuccessView(returnCode, getScenarioIndexPageUrl(), getScenarioIndexPageUrl());
        }
    }

    /**
     * what:    请求更新界面
     *
     * @param id
     *
     * @return
     */
    @RequestMapping(value = "/update.htm", method = RequestMethod.GET)
    public ModelAndView updateScenarioView(int id) {
        Scenario scenario = scenarioService.queryScenario(id);
        String scenarioZTreeJson = scenarioService.createScenarioZTreeJson();

        ModelAndView modelAndView = viewService.forwardDecorateView("/system/scenario/update", getScenarioIndexPageUrl());
        modelAndView.addObject("scenario", scenario);
        modelAndView.addObject("scenarioZTreeJson", scenarioZTreeJson);
        return modelAndView;
    }

    /**
     * what:    请求更新操作
     *
     * @param scenario
     *
     * @return
     */
    @RequestMapping(value = "/update.htm", method = RequestMethod.POST)
    public ModelAndView updateScenario(Scenario scenario) {
        int returnCode = scenarioService.updateScenario(scenario);
        if (ReturnCodeUtil.isFail(returnCode)) {
            return viewService.forwardFailView(returnCode, getScenarioIndexPageUrl());
        } else {
            return viewService.forwardSuccessView(returnCode, getScenarioIndexPageUrl(), getScenarioIndexPageUrl());
        }
    }

    /**
     * what:    执行删除操作
     *
     * @param id
     *
     * @return
     */
    @RequestMapping(value = "/delete.htm")
    public ModelAndView deleteScenario(int id) {
        int returnCode = scenarioService.deleteScenario(id);
        if (ReturnCodeUtil.isFail(returnCode)) {
            return viewService.forwardFailView(returnCode, getScenarioIndexPageUrl());
        } else {
            return viewService.forwardSuccessView(returnCode, getScenarioIndexPageUrl(), getScenarioIndexPageUrl());
        }
    }
}
