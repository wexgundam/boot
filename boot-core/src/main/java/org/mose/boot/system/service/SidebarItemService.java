/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:boot
 * Module Name:TODO:Module
 */
package org.mose.boot.system.service;

import org.mose.boot.common.service.ISidebarItemService;
import org.mose.boot.common.service.ResourceService;
import org.mose.boot.common.vo.SidebarItem;
import org.mose.boot.system.modal.Scenario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * what:    (这里用一句话描述这个类的作用). <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
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
    @Autowired
    ScenarioService scenarioService;

    /**
     * 获取全部场景，并生成侧边菜单模型
     *
     * @return
     */
    @Override
    public List<SidebarItem> getSidebarItems(String username) {
        List<SidebarItem> sidebarItems = new ArrayList<>();
        for (Scenario scenario : scenarioService.queryAllScenariosTree()) {
            sidebarItems.add(createSidebarItem(null, scenario));
        }
        return sidebarItems;
    }

    /**
     * 根据给定场景生成侧边菜单项
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
