package org.mose.boot.system.controller;

import org.mose.boot.common.service.ResourceService;
import org.mose.boot.common.service.ViewService;
import org.mose.boot.common.vo.Pagination;
import org.mose.boot.system.modal.User;
import org.mose.boot.system.service.RoleAuthorityService;
import org.mose.boot.system.service.RoleService;
import org.mose.boot.system.service.UserRoleService;
import org.mose.boot.system.service.UserService;
import org.mose.boot.util.code.ReturnCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 用户控制器
 *
 * @Author: 靳磊
 * @Date: 2017/8/18 14:52
 */
@Controller
@RequestMapping("/system/user")
public class UserController {
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleAuthorityService roleAuthorityService;
    @Autowired
    private ViewService viewService;

    /**
     * 该控制器管理的主viewName，其下包含的所有view的激活侧边栏都为该ViewName
     */
    String userRIndexViewName = "/system/user/index";
    String userIndexPageUrl = null;
    String userRoleIndexViewName = "/system/user/role/index";
    String userRoleIndexPageUrl = null;
    String userAuthorityIndexViewName = "/system/user/authority/index";
    String userAuthorityIndexPageUrl = null;

    private String getUserIndexPageUrl() {
        userIndexPageUrl = userIndexPageUrl == null ? resourceService.getDynamicResourceServerUrl() + userRIndexViewName + ".htm" : userIndexPageUrl;
        return userIndexPageUrl;
    }

    private String getUserRoleIndexPageUrl() {
        userRoleIndexPageUrl = userRoleIndexPageUrl == null ? resourceService.getDynamicResourceServerUrl() + userRoleIndexViewName + ".htm" : userRoleIndexPageUrl;
        return userRoleIndexPageUrl;
    }

    private String getUserAuthorityIndexPageUrl() {
        userAuthorityIndexPageUrl = userAuthorityIndexPageUrl == null ? resourceService.getDynamicResourceServerUrl() + userAuthorityIndexViewName + ".htm" : userAuthorityIndexPageUrl;
        return userAuthorityIndexPageUrl;
    }

    private String getUserRoleIndexPageUrl(int userId) {
        StringBuilder roleIndexPageUrl = new StringBuilder();
        roleIndexPageUrl.append(getUserRoleIndexPageUrl());
        roleIndexPageUrl.append("?userId=").append(userId);
        return roleIndexPageUrl.substring(0);
    }

    /**
     * 展示场景index视图
     *
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView userIndexView(Pagination pagination) {
        pagination.setUrl(getUserIndexPageUrl());
        pagination.setRowCount(userService.queryUserCount());

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("pagination", pagination.createHtml());
        parameters.put("users", userService.queryManyUsers(pagination.getPageNumber(), pagination.getPageRowCount()));

        ModelAndView modelAndView = viewService.forwardDecorateView(userRIndexViewName, getUserIndexPageUrl(), parameters);
        return modelAndView;
    }

    /**
     * 请求新增页面
     *
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addUserView() {
        ModelAndView modelAndView = viewService.forwardDecorateView("/system/user/add", getUserIndexPageUrl());
        return modelAndView;
    }

    /**
     * 执行新增操作
     *
     * @param user
     *
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addUser(User user) {
        int returnCode = userService.addUser(user);
        if (ReturnCodeUtil.isFail(returnCode)) {
            return viewService.forwardFailView(returnCode, getUserIndexPageUrl());
        } else {
            return viewService.forwardSuccessView(returnCode, getUserIndexPageUrl(), getUserIndexPageUrl());
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
    public ModelAndView updateUserView(int id) {
        User user = userService.queryUser(id);

        ModelAndView modelAndView = viewService.forwardDecorateView("/system/user/update", getUserIndexPageUrl());
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    /**
     * 请求更新操作
     *
     * @param user
     *
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView updateUser(User user) {
        int returnCode = userService.updateUser(user);
        if (ReturnCodeUtil.isFail(returnCode)) {
            return viewService.forwardFailView(returnCode, userRIndexViewName);
        } else {
            return viewService.forwardSuccessView(returnCode, getUserIndexPageUrl(), getUserIndexPageUrl());
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
    public ModelAndView deleteUser(int id) {
        int returnCode = userService.deleteUser(id);
        if (ReturnCodeUtil.isFail(returnCode)) {
            return viewService.forwardFailView(returnCode, userRIndexViewName);
        } else {
            return viewService.forwardSuccessView(returnCode, getUserIndexPageUrl(), getUserIndexPageUrl());
        }
    }

    /**
     * 展示场景index视图
     *
     * @return
     */
    @RequestMapping("/role/index")
    public ModelAndView userRoleIndexView(int userId, Pagination pagination) {
        pagination.setUrl(getUserRoleIndexPageUrl());
        pagination.setRowCount(userRoleService.queryRoleCountByUserId(userId));

        ModelAndView modelAndView = viewService.forwardDecorateView(userRoleIndexViewName, getUserIndexPageUrl());
        modelAndView.addObject("userId", userId);
        modelAndView.addObject("roles", userRoleService.queryManyRolesByUserId(userId, pagination.getPageNumber(), pagination.getPageRowCount()));
        modelAndView.addObject("pagination", pagination.createHtml());
        return modelAndView;
    }

    /**
     * 请求新增页面
     *
     * @return
     */
    @RequestMapping(value = "/role/update", method = RequestMethod.GET)
    public ModelAndView updateUserRoleView(int userId) {
        ModelAndView modelAndView = viewService.forwardDecorateView("/system/user/role/update", getUserIndexPageUrl());
        modelAndView.addObject("userId", userId);
        modelAndView.addObject("roles", roleService.queryAllRoles());
        modelAndView.addObject("userRoles", userRoleService.queryAllRolesByUserId(userId));
        return modelAndView;
    }

    /**
     * 请求新增页面
     *
     * @return
     */
    @RequestMapping(value = "/role/update", method = RequestMethod.POST)
    public ModelAndView updateUserRole(int userId, String roleIdArrayString) {
        int returnCode = userRoleService.updateUserRoles(userId, roleIdArrayString);
        if (ReturnCodeUtil.isFail(returnCode)) {
            return viewService.forwardFailView(returnCode, getUserIndexPageUrl());
        } else {
            return viewService.forwardSuccessView(returnCode, getUserIndexPageUrl(), getUserRoleIndexPageUrl(userId));
        }
    }

    /**
     * 请求新增页面
     *
     * @return
     */
    @RequestMapping(value = "/role/delete")
    public ModelAndView deleteUserRole(int userId, int roleId) {
        int returnCode = userRoleService.deleteUserRole(userId, roleId);
        if (ReturnCodeUtil.isFail(returnCode)) {
            return viewService.forwardFailView(returnCode, getUserIndexPageUrl());
        } else {
            return viewService.forwardSuccessView(returnCode, getUserIndexPageUrl(), getUserRoleIndexPageUrl(userId));
        }
    }


    /**
     * 展示场景index视图
     *
     * @return
     */
    @RequestMapping("/authority/index")
    public ModelAndView userAuthorityIndexView(int userId, Pagination pagination) {
        pagination.setUrl(getUserAuthorityIndexPageUrl());
        pagination.setRowCount(roleAuthorityService.queryAuthorityCountByUserId(userId));

        ModelAndView modelAndView = viewService.forwardDecorateView(userAuthorityIndexViewName, getUserIndexPageUrl());
        modelAndView.addObject("userId", userId);
        modelAndView.addObject("authorities", roleAuthorityService.queryManyAuthoritiesByUserId(userId, pagination.getPageNumber(), pagination.getPageRowCount()));
        modelAndView.addObject("pagination", pagination.createHtml());
        return modelAndView;
    }

    public ResourceService getResourceService() {
        return resourceService;
    }

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserRoleService getUserRoleService() {
        return userRoleService;
    }

    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    public ViewService getViewService() {
        return viewService;
    }

    public void setViewService(ViewService viewService) {
        this.viewService = viewService;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public RoleAuthorityService getRoleAuthorityService() {
        return roleAuthorityService;
    }

    public void setRoleAuthorityService(RoleAuthorityService roleAuthorityService) {
        this.roleAuthorityService = roleAuthorityService;
    }
}
