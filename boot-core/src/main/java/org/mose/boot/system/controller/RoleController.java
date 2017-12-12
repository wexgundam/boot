package org.mose.boot.system.controller;

import org.mose.boot.common.service.ResourceService;
import org.mose.boot.common.service.ViewService;
import org.mose.boot.common.vo.Pagination;
import org.mose.boot.system.modal.Role;
import org.mose.boot.system.modal.RoleAuthority;
import org.mose.boot.system.service.AuthorityService;
import org.mose.boot.system.service.RoleAuthorityService;
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
    private RoleAuthorityService roleAuthorityService;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private ViewService viewService;

    /**
     * 该控制器管理的主viewName，其下包含的所有view的激活侧边栏都为该ViewName
     */
    String roleIndexViewName = "/system/role/index";
    String roleIndexPageUrl = null;
    String roleAuthorityIndexViewName = "/system/role/authority/index";
    String roleAuthorityIndexPageUrl = null;

    private String getRoleIndexPageUrl() {
        roleIndexPageUrl = roleIndexPageUrl == null ? resourceService.getDynamicResourceServerUrl() + roleIndexViewName + ".htm" : roleIndexPageUrl;
        return roleIndexPageUrl;
    }

    private String getRoleAuthorityIndexPageUrl() {
        roleAuthorityIndexPageUrl = roleAuthorityIndexPageUrl == null ? resourceService.getDynamicResourceServerUrl() + roleAuthorityIndexViewName + ".htm" : roleAuthorityIndexPageUrl;
        return roleAuthorityIndexPageUrl;
    }

    private String getRoleAuthorityIndexPageUrl(int roleId) {
        StringBuilder roleIndexPageUrl = new StringBuilder();
        roleIndexPageUrl.append(getRoleAuthorityIndexPageUrl());
        roleIndexPageUrl.append("?roleId=").append(roleId);
        return roleIndexPageUrl.substring(0);
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
        parameters.put("roles", roleService.queryManyRoles(pagination.getPageNumber(), pagination.getPageRowCount()));

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


    /**
     * 展示场景index视图
     *
     * @return
     */
    @RequestMapping("/authority/index")
    public ModelAndView roleAuthorityIndexView(int roleId, Pagination pagination) {
        pagination.setUrl(getRoleAuthorityIndexPageUrl());
        pagination.setRowCount(roleAuthorityService.queryAuthorityCountByRoleId(roleId));

        ModelAndView modelAndView = viewService.forwardDecorateView(roleAuthorityIndexViewName, getRoleIndexPageUrl());
        modelAndView.addObject("roleId", roleId);
        modelAndView.addObject("authorities", roleAuthorityService.queryManyAuthoritiesByRoleId(roleId, pagination.getPageNumber(), pagination.getPageRowCount()));
        modelAndView.addObject("pagination", pagination.createHtml());
        return modelAndView;
    }

    /**
     * 请求新增页面
     *
     * @return
     */
    @RequestMapping(value = "/authority/update", method = RequestMethod.GET)
    public ModelAndView updateRoleAuthorityView(int roleId) {
        ModelAndView modelAndView = viewService.forwardDecorateView("/system/role/authority/update", getRoleIndexPageUrl());
        modelAndView.addObject("roleId", roleId);
        modelAndView.addObject("authorities", authorityService.queryAllAuthorities());
        modelAndView.addObject("roleAuthorities", roleAuthorityService.queryAllAuthoritiesByRoleId(roleId));
        return modelAndView;
    }

    /**
     * 请求新增页面
     *
     * @return
     */
    @RequestMapping(value = "/authority/update", method = RequestMethod.POST)
    public ModelAndView updateRoleAuthority(int roleId, String authorityIdArrayString) {
        int returnCode = roleAuthorityService.updateRoleAuthorities(roleId, authorityIdArrayString);
        if (ReturnCodeUtil.isFail(returnCode)) {
            return viewService.forwardFailView(returnCode, getRoleIndexPageUrl());
        } else {
            return viewService.forwardSuccessView(returnCode, getRoleIndexPageUrl(), getRoleAuthorityIndexPageUrl(roleId));
        }
    }

    /**
     * 请求新增页面
     *
     * @return
     */
    @RequestMapping(value = "/authority/delete")
    public ModelAndView deleteRoleAuthority(int roleId, int authorityId) {
        int returnCode = roleAuthorityService.deleteRoleAuthority(roleId, authorityId);
        if (ReturnCodeUtil.isFail(returnCode)) {
            return viewService.forwardFailView(returnCode, getRoleIndexPageUrl());
        } else {
            return viewService.forwardSuccessView(returnCode, getRoleIndexPageUrl(), getRoleAuthorityIndexPageUrl(roleId));
        }
    }


    public ResourceService getResourceService() {
        return resourceService;
    }

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    public RoleAuthorityService getRoleAuthorityService() {
        return roleAuthorityService;
    }

    public void setRoleAuthorityService(RoleAuthorityService roleAuthorityService) {
        this.roleAuthorityService = roleAuthorityService;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public ViewService getViewService() {
        return viewService;
    }

    public void setViewService(ViewService viewService) {
        this.viewService = viewService;
    }

    public AuthorityService getAuthorityService() {
        return authorityService;
    }

    public void setAuthorityService(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }
}
