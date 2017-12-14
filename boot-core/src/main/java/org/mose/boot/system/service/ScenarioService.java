package org.mose.boot.system.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mose.boot.system.modal.Scenario;
import org.mose.boot.system.repository.IScenarioRepository;
import org.mose.boot.util.ztree.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private Logger exceptionLogger = LoggerFactory.getLogger("exceptionLogger");
    /**
     * 场景数据获取对象
     */
    @Autowired
    private IScenarioRepository scenarioRepository;

    /**
     * 获取所有场景并按照Tree组织排序
     *
     * @return
     */
//    @Cacheable(value = "sysCache", key = "'scenarioTree'")
    public List<Scenario> queryAllScenariosTree() {
        List<Scenario> scenarios = new ArrayList<>();
        List<Scenario> allScenarios = scenarioRepository.queryAll();
        for (Scenario scenario : allScenarios) {
            if (scenario.getParent() == null) {
                findChildren(allScenarios, scenario);
                scenarios.add(scenario);
            }
        }
        Collections.sort(scenarios, Comparator.comparingInt(Scenario::getOrderIndex));
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
        Collections.sort(children, Comparator.comparingInt(Scenario::getOrderIndex));
        scenario.setChildren(children.isEmpty() ? null : children);
    }


    /**
     * 获取所有场景并按照List组织排序
     *
     * @return
     */
    public List<Scenario> queryAllScenariosList() {
        List<Scenario> scenarios = new ArrayList<>();
        for (Scenario scenario : queryAllScenariosTree()) {
            scenarios.addAll(toList(scenario));
        }
        return scenarios;
    }

    /**
     * 递归方法，遍历scenario的scenario，将其均加入到list，最后返回所有list形式的集合
     *
     * @param scenario
     *
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
     * 根据给定的id查询
     *
     * @param id
     *
     * @return
     */
    public Scenario queryScenario(int id) {
        return scenarioRepository.queryOne(id);
    }

    /**
     * Description: 获得所有场景基于ZTree的Json结构
     *
     * @return
     *
     * @Author: 靳磊
     * @Date: 2017/10/18 13:20
     */
    public String createScenarioZTreeJson() {
        List<Scenario> scenarioTree = queryAllScenariosTree();
        List<TreeNode> treeNodes = new ArrayList<>();
        for (Scenario scenario : scenarioTree) {
            TreeNode treeNode = createTreeNode(scenario);
            treeNodes.add(treeNode);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(treeNodes);
        } catch (JsonProcessingException e) {
            exceptionLogger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * Description: 获得给定场景对应的TreeNode
     *
     * @param scenario
     *
     * @return
     *
     * @Author: 靳磊
     * @Date: 2017/10/18 13:20
     */
    private TreeNode createTreeNode(Scenario scenario) {
        TreeNode treeNode = new TreeNode();
        treeNode.setId(Integer.toString(scenario.getId()));
        treeNode.setPId(scenario.getParent() == null ? null : Integer.toString(scenario.getParent().getId()));
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
     * Description:删除记录
     *
     * @param scenario
     *
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
     * 更新
     *
     * @param scenario
     */
    @Transactional
    public int updateScenario(Scenario scenario) {
        return scenarioRepository.updateOne(scenario);
    }

    /**
     * 删除给定id对应的记录
     *
     * @param id
     *
     * @return
     */
    @Transactional
    public int deleteScenario(int id) {
        return scenarioRepository.deleteOne(id);
    }
}
