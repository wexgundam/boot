package org.mose.boot.system.controller;

import org.mose.boot.common.service.ResourceService;
import org.mose.boot.common.service.ViewService;
import org.mose.boot.common.vo.Pagination;
import org.mose.boot.system.modal.Role;
import org.mose.boot.system.service.RoleService;
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
 * Description: 角色控制器
 *
 * @Author: 靳磊
 * @Date: 2017/8/18 14:52
 */
@Controller
@RequestMapping("/system/role")
public class RoleController {
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ViewService viewService;

    /**
     * 该控制器管理的主viewName，其下包含的所有view的激活侧边栏都为该ViewName
     */
    String roleIndexViewName = "/system/role/index";
    String roleIndexPageUrl = null;

    private String getRoleIndexPageUrl() {
        roleIndexPageUrl = roleIndexPageUrl == null ? resourceService.getDynamicResourceServerUrl() + roleIndexViewName + ".htm" : roleIndexPageUrl;
        return roleIndexPageUrl;
    }

    /**
     * 展示场景index视图
     *
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView roleIndexView(Pagination pagination) {
        pagination.setUrl(getRoleIndexPageUrl());
        pagination.setRowCount(roleService.queryRoleCount());

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("pagination", pagination.createHtml());
        parameters.put("authorities", roleService.queryManyRoles(pagination.getPageNumber(), pagination.getPageRowCount()));

        ModelAndView modelAndView = viewService.forwardDecorateView(roleIndexViewName, getRoleIndexPageUrl(), parameters);
        return modelAndView;
    }

    /**
     * 请求新增页面
     *
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addRoleView() {
        ModelAndView modelAndView = viewService.forwardDecorateView("/system/role/add", getRoleIndexPageUrl());
        return modelAndView;
    }

    /**
     * 执行新增操作
     *
     * @param role
     *
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addRole(Role role) {
        int returnCode = roleService.addRole(role);
        if (ReturnCodeUtil.isFail(returnCode)) {
            return viewService.forwardFailView(returnCode, WebUtil.viewName2Url(roleIndexViewName));
        } else {
            return viewService.forwardSuccessView(returnCode, getRoleIndexPageUrl(), getRoleIndexPageUrl());
        }
    }

    /**
     * 请求更新界面
     *
     * @param id
     *
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView updateRoleView(int id) {
        Role role = roleService.queryRole(id);

        ModelAndView modelAndView = viewService.forwardDecorateView("/system/role/update", getRoleIndexPageUrl());
        modelAndView.addObject("role", role);
        return modelAndView;
    }

    /**
     * 请求更新操作
     *
     * @param role
     *
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView updateRole(Role role) {
        int returnCode = roleService.updateRole(role);
        if (ReturnCodeUtil.isFail(returnCode)) {
            return viewService.forwardFailView(returnCode, roleIndexViewName);
        } else {
            return viewService.forwardSuccessView(returnCode, getRoleIndexPageUrl(), getRoleIndexPageUrl());
        }
    }

    /**
     * 执行删除操作
     *
     * @param id
     *
     * @return
     */
    @RequestMapping(value = "/delete")
    public ModelAndView deleteRole(int id) {
        int returnCode = roleService.deleteRole(id);
        if (ReturnCodeUtil.isFail(returnCode)) {
            return viewService.forwardFailView(returnCode, roleIndexViewName);
        } else {
            return viewService.forwardSuccessView(returnCode, getRoleIndexPageUrl(), getRoleIndexPageUrl());
        }
    }
}