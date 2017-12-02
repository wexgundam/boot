/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:boot
 * Module Name:TODO:Module
 */
package org.mose.boot.configuration.cache;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mose.boot.system.service.ScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * what:    (这里用一句话描述这个类的作用). <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author TODO:Who created on 2017/12/2
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Profile("mysql")
public class TestEhcacheConfiguration {
    @Autowired
    MockService service;

    @Before
    public void before() {
    }

    @Test
    public void testCache() {
        service.findOrCreateItem(1);
        service.findOrCreateItem(2);
        service.findOrCreateItem(1);
    }
}
