package org.mose.boot.system.controller;

import org.mose.boot.common.service.ResourceService;
import org.mose.boot.common.service.ViewService;
import org.mose.boot.common.vo.Pagination;
import org.mose.boot.system.modal.Authority;
import org.mose.boot.system.service.AuthorityService;
import org.mose.boot.util.code.ReturnCodeUtil;
import org.mose.boot.util.web.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 权限控制器
 *
 * @Author: 靳磊
 * @Date: 2017/8/18 14:52
 */
@Controller
@RequestMapping("/system/authority")
public class AuthorityController {
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private ViewService viewService;

    /**
     * 该控制器管理的主viewName，其下包含的所有view的激活侧边栏都为该ViewName
     */
    String authorityIndexViewName = "/system/authority/index";
    String authorityIndexPageUrl = null;

    private String getAuthorityIndexPageUrl() {
        authorityIndexPageUrl = authorityIndexPageUrl == null ? resourceService.getDynamicResourceServerUrl() + authorityIndexViewName + ".htm" : authorityIndexPageUrl;
        return authorityIndexPageUrl;
    }

    /**
     * 展示场景index视图
     *
     * @return
     */
    @RequestMapping("/index.htm")
    public ModelAndView authorityIndexView(Pagination pagination) {
        pagination.setUrl(getAuthorityIndexPageUrl());
        pagination.setRowCount(authorityService.queryAuthorityCount());

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("pagination", pagination.createHtml());
        parameters.put("authorities", authorityService.queryManyAuthorities(pagination.getPageNumber(), pagination.getPageRowCount()));

        ModelAndView modelAndView = viewService.forwardDecorateView(authorityIndexViewName, getAuthorityIndexPageUrl(), parameters);
        return modelAndView;
    }

    /**
     * 请求新增页面
     *
     * @return
     */
    @RequestMapping(value = "/add.htm", method = RequestMethod.GET)
    public ModelAndView addAuthorityView() {
        ModelAndView modelAndView = viewService.forwardDecorateView("/system/authority/add", getAuthorityIndexPageUrl());
        return modelAndView;
    }

    /**
     * 执行新增操作
     *
     * @param authority
     *
     * @return
     */
    @RequestMapping(value = "/add.htm", method = RequestMethod.POST)
    public ModelAndView addAuthority(Authority authority) {
        int returnCode = authorityService.addAuthority(authority);
        if (ReturnCodeUtil.isFail(returnCode)) {
            return viewService.forwardFailView(returnCode, WebUtil.viewName2Url(authorityIndexViewName));
        } else {
            return viewService.forwardSuccessView(returnCode, getAuthorityIndexPageUrl(), getAuthorityIndexPageUrl());
        }
    }

    /**
     * 请求更新界面
     *
     * @param id
     *
     * @return
     */
    @RequestMapping(value = "/update.htm", method = RequestMethod.GET)
    public ModelAndView updateAuthorityView(int id) {
        Authority authority = authorityService.queryAuthority(id);

        ModelAndView modelAndView = viewService.forwardDecorateView("/system/authority/update", getAuthorityIndexPageUrl());
        modelAndView.addObject("authority", authority);
        return modelAndView;
    }

    /**
     * 请求更新操作
     *
     * @param authority
     *
     * @return
     */
    @RequestMapping(value = "/update.htm", method = RequestMethod.POST)
    public ModelAndView updateAuthority(Authority authority) {
        int returnCode = authorityService.updateAuthority(authority);
        if (ReturnCodeUtil.isFail(returnCode)) {
            return viewService.forwardFailView(returnCode, authorityIndexViewName);
        } else {
            return viewService.forwardSuccessView(returnCode, getAuthorityIndexPageUrl(), getAuthorityIndexPageUrl());
        }
    }

    /**
     * 执行删除操作
     *
     * @param id
     *
     * @return
     */
    @RequestMapping(value = "/delete.htm")
    public ModelAndView deleteAuthority(int id) {
        int returnCode = authorityService.deleteAuthority(id);
        if (ReturnCodeUtil.isFail(returnCode)) {
            return viewService.forwardFailView(returnCode, authorityIndexViewName);
        } else {
            return viewService.forwardSuccessView(returnCode, getAuthorityIndexPageUrl(), getAuthorityIndexPageUrl());
        }
    }
}
