package org.mose.common.service;

import org.mose.util.code.ReturnCodeUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * what:    视图服务
 * <p>
 * 提供装饰视图侧边菜单、修改请求路由等功能
 *
 * @Author: 靳磊
 * @Date: 2017/9/1 9:51
 */
@Service
public class ViewService {
    /**
     * what:    转发到装饰页面，在装饰页面系统会附加视图公共数据，之后再转发到目标页面，通过Sitemesh完成拼装
     *
     * @param targetViewName       目标视图名
     * @param activeSidebarItemUrl 需要激活的SidebarItem Url
     * @param parameters           参数
     */
    public ModelAndView forwardDecorateView(String targetViewName, String activeSidebarItemUrl,
                                            Map<String, Object> parameters) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addAllObjects(parameters);
        modelAndView.setViewName(createDecoratorViewName(targetViewName, activeSidebarItemUrl));
        return modelAndView;
    }

    /**
     * what:    转发到装饰页面，在装饰页面系统会附加视图公共数据，之后再转发到目标页面，通过Sitemesh完成拼装
     *
     * @param targetViewName       目标视图名
     * @param activeSidebarItemUrl 需要激活的SidebarItem Url
     */
    public ModelAndView forwardDecorateView(String targetViewName, String activeSidebarItemUrl) {
        return forwardDecorateView(targetViewName, activeSidebarItemUrl, null);
    }

    /**
     * what:    获得转发视图控制器的地址
     *
     * @param targetViewName       跳转目标视图名
     * @param activeSidebarItemUrl 激活的SidebarItem Url
     *
     * @return
     */
    private String createDecoratorViewName(String targetViewName, String activeSidebarItemUrl) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("forward:/common/view.htm?targetViewName=").append(targetViewName).append("&activeSidebarItemUrl=" + activeSidebarItemUrl);
        return stringBuffer.substring(0);
    }

    /**
     * what:    跳转到错误页面
     *
     * @param message              失败描述信息
     * @param activeSidebarItemUrl
     */
    public ModelAndView forwardFailView(String message, String activeSidebarItemUrl) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("message", message);
        ModelAndView modelAndView = forwardDecorateView("/common/result/fail", activeSidebarItemUrl, parameters);
        return modelAndView;
    }

    /**
     * what:    跳转到错误页面
     *
     * @param returnCode           返回编码
     * @param activeSidebarItemUrl
     */
    public ModelAndView forwardFailView(int returnCode, String activeSidebarItemUrl) {
        return forwardFailView(ReturnCodeUtil.getMessage(returnCode), activeSidebarItemUrl);
    }

    /**
     * what:    跳转到成功页面
     *
     * @param message              成功描述信息
     * @param activeSidebarItemUrl 激活的侧边栏菜单链接地址
     * @param redirectUrl          重定向页面地址
     */
    public ModelAndView forwardSuccessView(String message, String activeSidebarItemUrl, String redirectUrl) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("message", message);
        parameters.put("redirectUrl", redirectUrl);
        ModelAndView modelAndView = forwardDecorateView("/common/result/success", activeSidebarItemUrl, parameters);
        return modelAndView;
    }

    /**
     * what:    跳转到成功页面
     *
     * @param returnCode           返回编码
     * @param activeSidebarItemUrl 激活的侧边栏菜单链接地址
     * @param redirectUrl          重定向页面地址
     */
    public ModelAndView forwardSuccessView(int returnCode, String activeSidebarItemUrl, String redirectUrl) {
        return forwardSuccessView(ReturnCodeUtil.getMessage(returnCode), activeSidebarItemUrl, redirectUrl);
    }

    /**
     * what:    跳转到异常页面
     *
     * @param message              描述信息
     * @param activeSidebarItemUrl 激活的侧边栏菜单链接地址
     * @param redirectUrl          重定向页面地址
     */
    public ModelAndView forwardExceptionView(String message, String activeSidebarItemUrl, String redirectUrl) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("message", message);
        parameters.put("redirectUrl", redirectUrl);
        ModelAndView modelAndView = forwardDecorateView("/common/result/500", activeSidebarItemUrl, parameters);
        return modelAndView;
    }
}
