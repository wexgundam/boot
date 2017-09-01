package org.mose.springboot.springmvc.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description: 视图服务
 *
 * 提供装饰视图侧边菜单、修改请求路由等功能
 *
 * @Author: 靳磊
 * @Date: 2017/9/1 9:51
 */
@Service
public class ViewService {

    /**
     * 设置给定的ModelAndView，将其viewName设为视图控制器地址，用于附加视图公共数据
     *
     * 给定的viewName是目标展示视图名称
     *
     * @param modelAndView
     * @param targetViewName
     * @param activeSidebarItemUrl 激活的SidebarItem Url
     */
    public void forwardDecoratePage(ModelAndView modelAndView, String targetViewName, String activeSidebarItemUrl) {
        modelAndView.setViewName(createViewDecoratorUrl(targetViewName, activeSidebarItemUrl));
    }

    /**
     * 设置给定的ModelAndView，将其viewName设为视图控制器地址，用于附加视图公共数据
     *
     * 给定的viewName是目标展示视图名称
     *
     * @param modelAndView
     * @param targetViewName
     */
    public void forwardDecoratePage(ModelAndView modelAndView, String targetViewName) {
        forwardDecoratePage(modelAndView, targetViewName, targetViewName);
    }

    /**
     * 跳转到错误页面
     *
     * @param modelAndView
     * @param message              失败描述信息
     * @param activeSidebarItemUrl
     */
    public void forwardFailPage(ModelAndView modelAndView, String message, String activeSidebarItemUrl) {
        modelAndView.addObject("message", message);
        forwardDecoratePage(modelAndView, "/common/result/fail", activeSidebarItemUrl);
    }

    /**
     * 跳转到成功页面
     *
     * @param modelAndView
     * @param message              成功描述信息
     * @param activeSidebarItemUrl 激活的侧边栏菜单链接地址
     * @param redirectUrl          重定向页面地址
     */
    public void forwardSuccessPage(ModelAndView modelAndView, String message, String activeSidebarItemUrl, String redirectUrl) {
        modelAndView.addObject("message", message);
        modelAndView.addObject("redirectUrl", redirectUrl);
        forwardDecoratePage(modelAndView, "/common/result/success", activeSidebarItemUrl);
    }


    /**
     * 获得转发视图控制器的地址
     *
     * @param targetViewName       跳转目标视图名
     * @param activeSidebarItemUrl 激活的SidebarItem Url
     * @return
     */
    public String createViewDecoratorUrl(String targetViewName, String activeSidebarItemUrl) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("forward:/view?targetViewName=").append(targetViewName).append("&activeSidebarItemUrl=" + activeSidebarItemUrl);
        return stringBuffer.substring(0);
    }
}
