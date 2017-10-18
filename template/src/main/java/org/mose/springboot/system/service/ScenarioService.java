package org.mose.springboot.system.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mose.springboot.metronic.modal.SidebarItem;
import org.mose.springboot.spring.ResourceConfiguration;
import org.mose.springboot.system.dao.IScenarioRepository;
import org.mose.springboot.system.modal.Scenario;
import org.mose.springboot.util.log.LogUtil;
import org.mose.springboot.util.ztree.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Description: 场景服务
 *
 * @Author: 靳磊
 * @Date: 2017/8/18 14:43
 */
@Service
public class ScenarioService {
    /**
     * 场景数据获取对象
     */
    @Autowired
    private IScenarioRepository scenarioRepository;
    /**
     * 系统资源配置
     */
    @Autowired
    ResourceConfiguration resourceConfiguration;

    /**
     * 获取所有场景并按照树形组织排序
     *
     * @return
     */
//    @Cacheable(value = "sysCache", key = "'scenarioList'")
    public List<Scenario> getScenarioList() {
        List<Scenario> scenarios = new ArrayList<>();
        for (Scenario scenario : getScenarioTree()) {
            scenarios.addAll(toList(scenario));
        }
        return scenarios;
    }

    /**
     * 递归方法，遍历scenario的scenario，将其均加入到list，最后返回所有list形式的集合
     *
     * @param scenario
     * @return
     */
    private List<Scenario> toList(Scenario scenario) {
        List<Scenario> scenarios = new ArrayList<>();
        scenarios.add(scenario);
        if (scenario.getChildren() != null && !scenario.getChildren().isEmpty()) {
            for (Scenario child : scenario.getChildren()) {
                scenarios.addAll(toList(child));
            }
        }
        return scenarios;
    }

    /**
     * 获取所有场景并按照树形组织排序
     *
     * @return
     */
//    @Cacheable(value = "sysCache", key = "'scenarioTree'")
    public List<Scenario> getScenarioTree() {
        List<Scenario> scenarios = new ArrayList<>();
        List<Scenario> allScenarios = scenarioRepository.queryAll();
        for (Scenario scenario : allScenarios) {
            if (scenario.getParent() == null) {
                findChildren(allScenarios, scenario);
                scenarios.add(scenario);
            }
        }
        Collections.sort(scenarios, Comparator.comparingInt(Scenario::getDisplayOrder));
        return scenarios;
    }

    /**
     * 获取给定场景的子场景
     *
     * @param scenarios
     * @param scenario
     */
    private void findChildren(List<Scenario> scenarios, Scenario scenario) {
        List<Scenario> children = new ArrayList<>();
        for (Scenario child : scenarios) {
            if (scenario.equals(child.getParent())) {
                findChildren(scenarios, child);
                children.add(child);
            }
        }
        Collections.sort(children, Comparator.comparingInt(Scenario::getDisplayOrder));
        scenario.setChildren(children.isEmpty() ? null : children);
    }

    /**
     * 获取全部场景，并生成侧边菜单模型
     *
     * @return
     */
//    @Cacheable(value = "sysCache", key = "'sidebarItems'")
    public List<SidebarItem> createSidebarItems() {
        List<SidebarItem> sidebarItems = new ArrayList<>();
        for (Scenario scenario : getScenarioTree()) {
            sidebarItems.add(createSidebarItem(null, scenario));
        }
        return sidebarItems;
    }

    /**
     * 根据给定场景生成侧边菜单项
     *
     * @param parentSidebarItem
     * @param scenario
     * @return
     */
    private SidebarItem createSidebarItem(SidebarItem parentSidebarItem, Scenario scenario) {
        SidebarItem sidebarItem = new SidebarItem();
        sidebarItem.setId(scenario.getId());
        sidebarItem.setName(scenario.getName());
        sidebarItem.setUrl(resourceConfiguration.getDynamicResourceServerUrl() + (scenario.getUrl() == null ? "/index.htm" : scenario.getUrl()));
        sidebarItem.setUrlTarget(scenario.getUrlTarget());
        sidebarItem.setIcon(scenario.getIcon());
        sidebarItem.setOrder(scenario.getDisplayOrder());
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


    /**
     * Description:删除记录
     *
     * @param scenario
     * @return
     *
     * @Author: 靳磊
     * @Date: 2017/10/18 13:19
     */
    @Transactional
    public int addScenario(Scenario scenario) {
        return scenarioRepository.insertOne(scenario);
    }

    /**
     * Description: 获得所有场景基于ZTree的Json结构
     *
     * @return
     *
     * @Author: 靳磊
     * @Date: 2017/10/18 13:20
     */
    public String getScenarioZTreeJson() {
        try {
            List<Scenario> scenarioTree = getScenarioTree();
            List<TreeNode> treeNodes = new ArrayList<>();
            for (Scenario scenario : scenarioTree) {
                TreeNode treeNode = createTreeNode(scenario);
                treeNodes.add(treeNode);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(treeNodes);
        } catch (JsonProcessingException e) {
            LogUtil.error(e);
        }
        return null;
    }

    /**
     * Description: 获得给定场景对应的TreeNode
     *
     * @param scenario
     * @return
     *
     * @Author: 靳磊
     * @Date: 2017/10/18 13:20
     */
    private TreeNode createTreeNode(Scenario scenario) {
        TreeNode treeNode = new TreeNode();
        treeNode.setId(Integer.toString(scenario.getId()));
        treeNode.setPId(scenario.getParentId() == null ? null : scenario.getParentId().toString());
        treeNode.setName(scenario.getName());
        treeNode.setOpen(true);
        if (scenario.getChildren() != null && !scenario.getChildren().isEmpty()) {
            List<TreeNode> children = new ArrayList<>();
            for (Scenario childScenario : scenario.getChildren()) {
                TreeNode child = createTreeNode(childScenario);
                children.add(child);
            }
            treeNode.setChildren(children);
        }
        return treeNode;
    }

    /**
     * 删除给定id对应的记录
     *
     * @param id
     * @return
     */
    @Transactional
    public int deleteScenario(int id) {
        return scenarioRepository.deleteOne(id);
    }

    /**
     * 根据给定的id查询
     *
     * @param id
     * @return
     */
    public Scenario getScenario(int id) {
        return scenarioRepository.queryOne(id);
    }
}
