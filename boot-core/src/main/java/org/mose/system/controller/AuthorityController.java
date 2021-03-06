package org.mose.system.controller;

import org.mose.common.service.ResourceService;
import org.mose.common.service.ViewService;
import org.mose.common.vo.Pagination;
import org.mose.system.modal.Authority;
import org.mose.system.service.AuthorityService;
import org.mose.util.code.ReturnCodeUtil;
import org.mose.util.web.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * what:    权限控制器. <br/>
 *
 * @author 靳磊 created on 2017/12/25
 */
@Controller
@RequestMapping("/system/authority")
public class AuthorityController {
    /**
     * 资源地址服务
     */
    @Autowired
    private ResourceService resourceService;
    /**
     * 权限管理服务
     */
    @Autowired
    private AuthorityService authorityService;
    /**
     * 视图服务
     */
    @Autowired
    private ViewService viewService;

    /**
     * what:    该控制器管理的主viewName，其下包含的所有view的激活侧边栏都为该ViewName
     */
    String authorityIndexViewName = "/system/authority/index";
    String authorityIndexPageUrl = null;

    private String getAuthorityIndexPageUrl() {
        authorityIndexPageUrl = authorityIndexPageUrl == null ? resourceService.getDynamicResourceServerUrl() + authorityIndexViewName + ".htm" : authorityIndexPageUrl;
        return authorityIndexPageUrl;
    }

    /**
     * what:    展示场景index视图
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
     * what:    请求新增页面
     *
     * @return
     */
    @RequestMapping(value = "/add.htm", method = RequestMethod.GET)
    public ModelAndView addAuthorityView() {
        ModelAndView modelAndView = viewService.forwardDecorateView("/system/authority/add", getAuthorityIndexPageUrl());
        return modelAndView;
    }

    /**
     * what:    执行新增操作
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
     * what:    请求更新界面
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
     * what:    请求更新操作
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
     * what:    执行删除操作
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
