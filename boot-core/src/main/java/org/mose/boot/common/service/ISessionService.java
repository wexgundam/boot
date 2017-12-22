/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:boot
 * Module Name:TODO:Module
 */
package org.mose.boot.common.service;

/**
 * what:    web容器session服务. <br/>
 * when:    获取、更新、删除session时.<br/>
 *
 * @author 靳磊 created on 2017/12/19
 */
public interface ISessionService {
    /**
     * what:    删除含有给定角色的用户的session. <br/>
     *
     * @param roleId
     *
     * @return
     *
     * @author 靳磊 created on 2017/12/22
     */
    void deleteAllSessionsByRoleId(int roleId);

    /**
     * what:    删除含有给定权限的用户的session. <br/>
     *
     * @param authorityId
     *
     * @return
     *
     * @author 靳磊 created on 2017/12/22
     */
    void deleteAllSessionsByAuthorityId(int authorityId);

    /**
     * what:    删除含有给定用户的session. <br/>
     *
     * @param userId
     *
     * @return
     *
     * @author 靳磊 created on 2017/12/22
     */
    void deleteAllSessionsByUserId(int userId);
}
