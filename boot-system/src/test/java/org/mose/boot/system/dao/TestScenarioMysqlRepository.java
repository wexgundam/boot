package org.mose.boot.system.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mose.boot.system.modal.Scenario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Description:
 *
 * @Author: 靳磊
 * @Date: 2017/8/14:23
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Profile("mysql")
@Transactional
public class TestScenarioMysqlRepository {
    @Autowired
    IScenarioRepository repository;
    private Scenario scenario;

    @Before
    public void insertOne() {
        Scenario parent = new Scenario();
        parent.setName("北京");
        repository.insertOne(parent);

        scenario = new Scenario();
        scenario.setName("name");
        scenario.setDescription("description");
        scenario.setParent(parent);
        scenario.setUrl("2");
        scenario.setUrlTarget("3");
        scenario.setIcon("icon");
        scenario.setDisplayOrder(1);
        repository.insertOne(scenario);
    }

    @Test
    public void testQueryOne() {
        Scenario queryOne = repository.queryOne(scenario.getId());
        Assert.assertNotNull(queryOne);
        Assert.assertEquals(queryOne.getName(), this.scenario.getName());
        Assert.assertEquals(queryOne.getDescription(), this.scenario.getDescription());
        Assert.assertEquals(queryOne.getParent().getId(), this.scenario.getParent().getId());
        Assert.assertEquals(queryOne.getParent().getName(), this.scenario.getParent().getName());
        Assert.assertNotNull(queryOne.getParent());
        Assert.assertEquals(queryOne.getUrl(), this.scenario.getUrl());
        Assert.assertEquals(queryOne.getUrlTarget(), this.scenario.getUrlTarget());
        Assert.assertEquals(queryOne.getIcon(), this.scenario.getIcon());
        Assert.assertEquals(queryOne.getDisplayOrder(), this.scenario.getDisplayOrder());
    }

    @Test
    public void testQueryAll() {
        List<Scenario> scenarios = repository.queryAll();
        Assert.assertFalse(scenarios.isEmpty());
    }

    @Test
    public void testUpdateOne() {
        scenario.setName("角色");
        scenario.setDescription("description2");
        scenario.setUrl("22");
        scenario.setUrlTarget("33");
        scenario.setIcon("icon2");
        scenario.setDisplayOrder(12);

        repository.updateOne(scenario);
        Scenario queriedOne = repository.queryOne(scenario.getId());

        Assert.assertNotNull(queriedOne);
        Assert.assertEquals(queriedOne.getName(), scenario.getName());
        Assert.assertEquals(queriedOne.getDescription(), scenario.getDescription());
        Assert.assertEquals(queriedOne.getParent().getId(), scenario.getParent().getId());
        Assert.assertNotNull(queriedOne.getParent());
        Assert.assertEquals(queriedOne.getUrl(), scenario.getUrl());
        Assert.assertEquals(queriedOne.getUrlTarget(), scenario.getUrlTarget());
        Assert.assertEquals(queriedOne.getIcon(), scenario.getIcon());
        Assert.assertEquals(queriedOne.getDisplayOrder(), scenario.getDisplayOrder());
    }

    @Test
    public void deleteOne() {
        repository.deleteOne(scenario);
        Scenario queryOne = repository.queryOne(this.scenario.getId());
        Assert.assertNull(queryOne);
    }
}
