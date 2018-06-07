/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:boot
 * Module Name:TODO:Module
 */
package org.mose.common.service;

import org.mose.common.vo.SidebarItem;

import java.util.List;

/**
 * what:    侧边栏条目服务. <br/>
 *
 * @author 靳磊 created on 2017/12/14
 */
public interface ISidebarItemService {
    /**
     * 根据给定用户名获取对应的侧边栏显示条目
     *
     * @param username 用户名称
     *
     * @return
     */
    List<SidebarItem> getSidebarItems(String username);
}
