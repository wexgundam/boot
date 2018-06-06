/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:boot
 * Module Name:TODO:Module
 */
package org.mose.system.service;

import org.mose.common.service.ISidebarItemService;
import org.mose.common.service.ResourceService;
import org.mose.common.vo.SidebarItem;
import org.mose.system.modal.Scenario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * what:    侧边栏菜单项服务. <br/>
 * <p>
 * 根据场景生成侧边栏菜单项
 *
 * @author 靳磊 created on 2017/12/14
 */
@Service
public class SidebarItemService implements ISidebarItemService {
    /**
     * 系统资源配置
     */
    @Autowired
    ResourceService resourceService;
    /**
     * 场景服务
     */
    @Autowired
    ScenarioService scenarioService;

    /**
     * what:    获取全部场景，并生成侧边菜单模型
     *
     * @return
     */
    @Override
    public List<SidebarItem> getSidebarItems(String username) {
        List<SidebarItem> sidebarItems = new ArrayList<>();
        for (Scenario scenario : scenarioService.queryAllScenariosTreeByUsername(username)) {
            sidebarItems.add(createSidebarItem(null, scenario));
        }
        return sidebarItems;
    }

    /**
     * what:    根据给定场景生成侧边菜单项
     *
     * @param parentSidebarItem
     * @param scenario
     *
     * @return
     */
    private SidebarItem createSidebarItem(SidebarItem parentSidebarItem, Scenario scenario) {
        SidebarItem sidebarItem = new SidebarItem();
        sidebarItem.setId(scenario.getId());
        sidebarItem.setName(scenario.getName());
        sidebarItem.setUrl(resourceService.getDynamicResourceServerUrl() + (scenario.getUrl() == null ? "/index.htm" : scenario.getUrl()));
        sidebarItem.setUrlTarget(scenario.getUrlTarget());
        sidebarItem.setIcon(scenario.getIcon());
        sidebarItem.setOrder(scenario.getOrderIndex());
        sidebarItem.setParent(scenario.getParent() == null ? null : parentSidebarItem);
        if (scenario.getChildren() != null && !scenario.getChildren().isEmpty()) {
            List<SidebarItem> children = new ArrayList<>();
            for (Scenario child : scenario.getChildren()) {
                children.add(createSidebarItem(sidebarItem, child));
            }
            sidebarItem.setChildren(children);
        }
        return sidebarItem;
    }
}
