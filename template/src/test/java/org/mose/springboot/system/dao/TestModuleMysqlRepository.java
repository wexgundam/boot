package org.mose.springboot.system.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mose.springboot.system.modal.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Description:
 *
 * @Author: 靳磊
 * @Date: 2017/8/14:23
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Profile("dev")
public class TestModuleMysqlRepository {
    @Autowired
    ModuleMysqlRepository repository;

    @Test
    @Rollback
    public void testQueryAll() {
        List<Module> modules = repository.queryAll();
        Assert.assertFalse(modules.isEmpty());
    }
}
