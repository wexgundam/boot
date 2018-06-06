/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:boot
 * Module Name:TODO:Module
 */
package org.mose.system.repository.mysql;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mose.boot.configuration.datasource.DruidConfiguration;
import org.mose.system.modal.Scenario;
import org.mose.system.repository.IScenarioRepository;
import org.mose.boot.test.ApplicationInitializer;
import org.mose.util.code.ReturnCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * what:    测试ScenarioMysqlRepository.<br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2017/12/2
 */
@SpringBootTest(classes = ApplicationInitializer.class)
@Import(DruidConfiguration.class)
@ComponentScan("org.mose.system.repository")
@ActiveProfiles({"mysql"})
@RunWith(SpringRunner.class)
@Transactional
public class TestScenarioMysqlRepository {
    @Autowired
    private IScenarioRepository scenarioRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testRepository() {
        Scenario scenario = new Scenario();
        int returnCode = scenarioRepository.insertOne(scenario);
        assertEquals(ReturnCodeUtil.SUCCESS__INSERT, returnCode);
        assertFalse(scenario.getId() == 0);
        Scenario queryOne = scenarioRepository.queryOne(scenario.getId());
        assertEquals(scenario, queryOne);
        List<Scenario> scenarios = scenarioRepository.queryAll();
        assertNotNull(scenarios);
        scenarioRepository.queryChildrenCount(scenario.getId());
        returnCode = scenarioRepository.updateOne(scenario);
        assertEquals(ReturnCodeUtil.SUCCESS__UPDATE, returnCode);
        returnCode = scenarioRepository.deleteOne(scenario);
        assertEquals(ReturnCodeUtil.SUCCESS__DELETE, returnCode);
        queryOne = scenarioRepository.queryOne(scenario.getId());
        assertNull(queryOne);
    }
}
