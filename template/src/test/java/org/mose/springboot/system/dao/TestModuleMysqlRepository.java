package org.mose.springboot.system.dao;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mose.springboot.system.modal.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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
public class TestModuleMysqlRepository {
    @Autowired
    IModuleRepository repository;
    private Module module;

    @Before
    public void insertOne() {
        module = new Module();
        module.setName("name");
        module.setDescription("description");
        module.setParentId(1);
        module.setScenarioId(2);
        module.setIcon("icon");
        module.setDisplayOrder(1);
        module.setId(repository.insertOne(module).intValue());
    }

    @Test
    public void testOne() {
        Module queryOne = repository.queryOne(module.getId());
        Assert.assertNotNull(queryOne);
        Assert.assertEquals(queryOne.getName(), this.module.getName());
        Assert.assertEquals(queryOne.getDescription(), this.module.getDescription());
        Assert.assertEquals(queryOne.getParentId(), this.module.getParentId());
        Assert.assertNotNull(queryOne.getParent());
        Assert.assertEquals(queryOne.getScenarioId(), this.module.getScenarioId());
        Assert.assertEquals(queryOne.getIcon(), this.module.getIcon());
        Assert.assertEquals(queryOne.getDisplayOrder(), this.module.getDisplayOrder());
    }

    @Test
    public void testQueryAll() {
        repository.queryAll();
    }

    @Test
    public void testUpdateOne() {
        Module module = new Module();
        module.setId(this.module.getId());
        module.setName("name2");
        module.setDescription("description2");
        module.setParentId(12);
        module.setScenarioId(22);
        module.setIcon("icon2");
        module.setDisplayOrder(12);

        repository.updateOne(module);
        Module queriedOne = repository.queryOne(module.getId());

        Assert.assertNotNull(queriedOne);
        Assert.assertEquals(queriedOne.getName(), module.getName());
        Assert.assertEquals(queriedOne.getDescription(), module.getDescription());
        Assert.assertEquals(queriedOne.getParentId(), module.getParentId());
        Assert.assertNotNull(queriedOne.getParent());
        Assert.assertEquals(queriedOne.getScenarioId(), module.getScenarioId());
        Assert.assertEquals(queriedOne.getIcon(), module.getIcon());
        Assert.assertEquals(queriedOne.getDisplayOrder(), module.getDisplayOrder());
    }

    @Test
    public void deleteOne() {
        repository.deleteOne(module);
        Module queryOne = repository.queryOne(this.module.getId());
        Assert.assertNull(queryOne);
    }
}
