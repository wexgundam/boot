package org.mose.boot.system.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mose.boot.system.modal.Authority;
import org.mose.boot.system.modal.Scenario;
import org.mose.boot.system.modal.User;
import org.mose.boot.system.repository.IScenarioRepository;
import org.mose.boot.util.ztree.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * what:    场景服务
 *
 * @Author: 靳磊
 * @Date: 2017/8/18 14:43
 */
@Service
public class ScenarioService {
    /**
     * 异常日志
     */
    private Logger exceptionLogger = LoggerFactory.getLogger("exceptionLogger");
    /**
     * 用户服务
     */
    @Autowired
    private UserService userService;
    /**
     * 场景数据获取对象
     */
    @Autowired
    private IScenarioRepository scenarioRepository;

    /**
     * what:    获取所有场景并按照Tree组织排序
     *
     * @return
     */
    public List<Scenario> queryAllScenariosTree() {
        List<Scenario> allScenarios = scenarioRepository.queryAll();
        return toTree(allScenarios);
    }

    /**
     * what:    将场景List转成Tree
     *
     * @param scenarios
     *
     * @return
     */
    private List<Scenario> toTree(List<Scenario> scenarios) {
        List<Scenario> scenarioTree = new ArrayList<>();
        for (Scenario scenario : scenarios) {
            if (scenario.getParent() == null) {
                findChildren(scenarios, scenario);
                scenarioTree.add(scenario);
            }
        }
        Collections.sort(scenarioTree, Comparator.comparingInt(Scenario::getOrderIndex));
        return scenarioTree;
    }

    /**
     * what:    获取给定场景的子场景
     * how:     通过递归算法
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
     * what:    根据给定的用户名查询用户可访问的场景，并生成Tree
     *
     * @param username
     *
     * @return
     */
    public List<Scenario> queryAllScenariosTreeByUsername(String username) {
        User user = userService.queryUserWithAuthoritiesByUsername(username);
        List<String> authorityNames = new ArrayList<>();
        for (Authority authority : user.getAuthorities()) {
            authorityNames.add(authority.getName());
        }

        List<Scenario> allScenarios = scenarioRepository.queryAll();
        return toTree(allScenarios, authorityNames);
    }

    /**
     * what:    遍历场景列表，获取符合给定权限的场景，并转成Tree
     *
     * @param scenarios
     * @param authorityNames
     *
     * @return
     */
    private List<Scenario> toTree(List<Scenario> scenarios, List<String> authorityNames) {
        List<Scenario> scenarioTree = new ArrayList<>();
        for (Scenario scenario : scenarios) {
            if (scenario.getParent() == null && authorityNames.contains(scenario.getAuthorityName())) {
                findChildren(scenarios, scenario, authorityNames);
                scenarioTree.add(scenario);
            }
        }
        Collections.sort(scenarioTree, Comparator.comparingInt(Scenario::getOrderIndex));
        return scenarioTree;
    }

    /**
     * what:    获取符合权限的场景的子场景
     *
     * @param scenarios
     * @param scenario
     */
    private void findChildren(List<Scenario> scenarios, Scenario scenario, List<String> authorityNames) {
        List<Scenario> children = new ArrayList<>();
        for (Scenario child : scenarios) {
            if (scenario.equals(child.getParent()) && authorityNames.contains(child.getAuthorityName())) {
                findChildren(scenarios, child);
                children.add(child);
            }
        }
        Collections.sort(children, Comparator.comparingInt(Scenario::getOrderIndex));
        scenario.setChildren(children.isEmpty() ? null : children);
    }

    /**
     * what:    获取所有场景并按照List组织排序
     * warning: 访问该方法需要具备要求的权限
     *
     * @return
     */
    @PreAuthorize("hasRole('SystemScenario')")
    public List<Scenario> queryAllScenariosList() {
        List<Scenario> scenarios = new ArrayList<>();
        for (Scenario scenario : queryAllScenariosTree()) {
            scenarios.addAll(toList(scenario));
        }
        return scenarios;
    }

    /**
     * what:    递归方法，遍历scenario的scenario，将其均加入到list，最后返回所有list形式的集合
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
     * what:    根据给定的id查询
     *
     * @param id
     *
     * @return
     */
    public Scenario queryScenario(int id) {
        return scenarioRepository.queryOne(id);
    }

    /**
     * what:    获得所有场景基于ZTree的Json结构
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
     * what:    获得给定场景对应的TreeNode
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
     * what:    删除记录
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
     * what:    更新
     *
     * @param scenario
     */
    @Transactional
    public int updateScenario(Scenario scenario) {
        return scenarioRepository.updateOne(scenario);
    }

    /**
     * what:    删除给定id对应的记录
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
