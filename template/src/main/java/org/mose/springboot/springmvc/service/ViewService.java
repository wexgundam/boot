package org.mose.springboot.springmvc.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 视图服务
 * <p>
 * 提供装饰视图侧边菜单、修改请求路由等功能
 *
 * @Author: 靳磊
 * @Date: 2017/9/1 9:51
 */
@Service
public class ViewService {
    /**
     * 转发到装饰页面，在装饰页面系统会附加视图公共数据，之后再转发到目标页面，通过Sitemesh完成拼装
     *
     * @param targetViewName       目标视图名
     * @param activeSidebarItemUrl 需要激活的SidebarItem Url
     * @param parameter            参数
     */
    public ModelAndView forwardDecoratePage(String targetViewName, String activeSidebarItemUrl,
                                            Map<String, Object> parameter) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addAllObjects(parameter);
        modelAndView.setViewName(createDecoratorViewName(targetViewName, activeSidebarItemUrl));
        return modelAndView;
    }

    /**
     * 转发到装饰页面，在装饰页面系统会附加视图公共数据，之后再转发到目标页面，通过Sitemesh完成拼装
     *
     * @param targetViewName 目标视图名
     * @param parameter      参数
     */
    public ModelAndView forwardDecoratePage(String targetViewName, Map<String, Object> parameter) {
        return forwardDecoratePage(targetViewName, targetViewName, parameter);
    }

    /**
     * 转发到装饰页面，在装饰页面系统会附加视图公共数据，之后再转发到目标页面，通过Sitemesh完成拼装
     *
     * @param targetViewName       目标视图名
     * @param activeSidebarItemUrl 需要激活的SidebarItem Url
     */
    public ModelAndView forwardDecoratePage(String targetViewName, String activeSidebarItemUrl) {
        return forwardDecoratePage(targetViewName, activeSidebarItemUrl, null);
    }


    /**
     * 转发到装饰页面，在装饰页面系统会附加视图公共数据，之后再转发到目标页面，通过Sitemesh完成拼装
     *
     * @param targetViewName 目标视图名和需要激活的SidebarItem Url
     */
    public ModelAndView forwardDecoratePage(String targetViewName) {
        return forwardDecoratePage(targetViewName, targetViewName);
    }


    /**
     * 获得转发视图控制器的地址
     *
     * @param targetViewName       跳转目标视图名
     * @param activeSidebarItemUrl 激活的SidebarItem Url
     * @return
     */
    private String createDecoratorViewName(String targetViewName, String activeSidebarItemUrl) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("forward:/view?targetViewName=").append(targetViewName).append("&activeSidebarItemUrl=" + activeSidebarItemUrl);
        return stringBuffer.substring(0);
    }

    /**
     * 跳转到错误页面
     *
     * @param message              失败描述信息
     * @param activeSidebarItemUrl
     */
    public ModelAndView forwardFailPage(String message, String activeSidebarItemUrl) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("message", message);
        ModelAndView modelAndView = forwardDecoratePage("/common/result/fail", activeSidebarItemUrl, parameters);
        return modelAndView;
    }

    /**
     * 跳转到成功页面
     *
     * @param message              成功描述信息
     * @param activeSidebarItemUrl 激活的侧边栏菜单链接地址
     * @param redirectUrl          重定向页面地址
     */
    public ModelAndView forwardSuccessPage(String message, String activeSidebarItemUrl, String redirectUrl) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("message", message);
        parameters.put("redirectUrl", redirectUrl);
        ModelAndView modelAndView = forwardDecoratePage("/common/result/success", activeSidebarItemUrl, parameters);
        return modelAndView;
    }

    /**
     * 跳转到异常页面
     *
     * @param message              描述信息
     * @param activeSidebarItemUrl 激活的侧边栏菜单链接地址
     * @param redirectUrl          重定向页面地址
     */
    public ModelAndView forwardExceptionPage(String message, String activeSidebarItemUrl, String redirectUrl) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("message", message);
        parameters.put("redirectUrl", redirectUrl);
        ModelAndView modelAndView = forwardDecoratePage("/common/result/exception", activeSidebarItemUrl, parameters);
        return modelAndView;
    }
}
