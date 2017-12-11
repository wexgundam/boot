package org.mose.boot.system.controller;

import org.mose.boot.common.service.ResourceService;
import org.mose.boot.common.service.ViewService;
import org.mose.boot.common.vo.Pagination;
import org.mose.boot.system.modal.Role;
import org.mose.boot.system.modal.User;
import org.mose.boot.system.service.RoleService;
import org.mose.boot.system.service.UserService;
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
    private RoleService roleService;
    @Autowired
    private ViewService viewService;

    /**
     * 该控制器管理的主viewName，其下包含的所有view的激活侧边栏都为该ViewName
     */
    String indexViewName = "/system/user/index";
    String indexPageUrl = null;
    String roleIndexViewName = "/system/user/role/index";
    String roleIndexPageUrl = null;

    private String getIndexPageUrl() {
        indexPageUrl = indexPageUrl == null ? resourceService.getDynamicResourceServerUrl() + indexViewName + ".htm" : indexPageUrl;
        return indexPageUrl;
    }

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
    public ModelAndView indexView(Pagination pagination) {
        pagination.setUrl(getIndexPageUrl());
        pagination.setRowCount(userService.queryUserCount());

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("pagination", pagination.createHtml());
        parameters.put("users", userService.queryUserList(pagination.getPageNumber(), pagination.getPageRowCount()));

        ModelAndView modelAndView = viewService.forwardDecorateView(indexViewName, getIndexPageUrl(), parameters);
        return modelAndView;
    }

    /**
     * 请求新增页面
     *
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addView() {
        ModelAndView modelAndView = viewService.forwardDecorateView("/system/user/add", getIndexPageUrl());
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
            return viewService.forwardFailView(returnCode, WebUtil.viewName2Url(indexViewName));
        } else {
            return viewService.forwardSuccessView(returnCode, indexViewName + ".htm", indexViewName + ".htm");
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
    public ModelAndView updateView(int id) {
        User user = userService.queryUser(id);

        ModelAndView modelAndView = viewService.forwardDecorateView("/system/user/update", indexViewName + ".htm");
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
            return viewService.forwardFailView(returnCode, indexViewName);
        } else {
            return viewService.forwardSuccessView(returnCode, indexViewName + ".htm", indexViewName + ".htm");
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
            return viewService.forwardFailView(returnCode, indexViewName);
        } else {
            return viewService.forwardSuccessView(returnCode, indexViewName + ".htm", indexViewName + ".htm");
        }
    }

    /**
     * 展示场景index视图
     *
     * @return
     */
    @RequestMapping("/role/index")
    public ModelAndView roleIndexView(int userId, Pagination pagination) {
        pagination.setUrl(getRoleIndexPageUrl());
        pagination.setRowCount(userService.queryRoleCount(userId));

        ModelAndView modelAndView = viewService.forwardDecorateView(roleIndexViewName, getRoleIndexPageUrl());
        modelAndView.addObject("userId", userId);
        modelAndView.addObject("roles", userService.queryRoles(userId, pagination.getPageNumber(), pagination.getPageRowCount()));
        modelAndView.addObject("pagination", pagination.createHtml());
        return modelAndView;
    }

    /**
     * 请求新增页面
     *
     * @return
     */
    @RequestMapping(value = "/role/update", method = RequestMethod.GET)
    public ModelAndView selectRoleView(int userId, Pagination pagination) {
        pagination.setUrl(getRoleIndexPageUrl());
        pagination.setRowCount(roleService.queryRoleCount());

        ModelAndView modelAndView = viewService.forwardDecorateView("/system/user/role/update", getRoleIndexPageUrl());
        modelAndView.addObject("userId", userId);
        modelAndView.addObject("roles", roleService.queryRoleList(pagination.getPageNumber(), pagination.getPageRowCount()));
        modelAndView.addObject("pagination", pagination.createHtml());
        return modelAndView;
    }

//    /**
//     * 执行新增操作
//     *
//     * @return
//     */
//    @RequestMapping(value = "/role/add", method = RequestMethod.POST)
//    public ModelAndView addUser(int userId, Role role) {
//        int returnCode = userService.addRole(, role);
//        if (ReturnCodeUtil.isFail(returnCode)) {
//            return viewService.forwardFailView(returnCode, WebUtil.viewName2Url(indexViewName));
//        } else {
//            return viewService.forwardSuccessView(returnCode, indexViewName + ".htm", indexViewName + ".htm");
//        }
//    }
//
//    /**
//     * 请求更新界面
//     *
//     * @param id
//     *
//     * @return
//     */
//    @RequestMapping(value = "/update", method = RequestMethod.GET)
//    public ModelAndView updateView(int id) {
//        User user = userService.queryUser(id);
//
//        ModelAndView modelAndView = viewService.forwardDecorateView("/system/user/update", indexViewName + ".htm");
//        modelAndView.addObject("user", user);
//        return modelAndView;
//    }
//
//    /**
//     * 请求更新操作
//     *
//     * @param user
//     *
//     * @return
//     */
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public ModelAndView updateUser(User user) {
//        int returnCode = userService.updateUser(user);
//        if (ReturnCodeUtil.isFail(returnCode)) {
//            return viewService.forwardFailView(returnCode, indexViewName);
//        } else {
//            return viewService.forwardSuccessView(returnCode, indexViewName + ".htm", indexViewName + ".htm");
//        }
//    }
//
//    /**
//     * 执行删除操作
//     *
//     * @param id
//     *
//     * @return
//     */
//    @RequestMapping(value = "/delete")
//    public ModelAndView deleteUser(int id) {
//        int returnCode = userService.deleteUser(id);
//        if (ReturnCodeUtil.isFail(returnCode)) {
//            return viewService.forwardFailView(returnCode, indexViewName);
//        } else {
//            return viewService.forwardSuccessView(returnCode, indexViewName + ".htm", indexViewName + ".htm");
//        }
//    }


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
}
