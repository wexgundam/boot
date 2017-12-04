/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:boot
 * Module Name:TODO:Module
 */
package org.mose.boot.configuration.cache;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * what:    测试CacheConfiguration是否配置成功 <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author TODO:瓦力 created on 2017/12/2
 */
@SpringBootTest(classes = TestEhcacheConfiguration.class)
@ComponentScan({"org.mose.boot.configuration.cache"})
@RunWith(SpringRunner.class)
@SpringBootApplication
public class TestEhcacheConfiguration {
    @Autowired
    private DemoService demoService;

    @Before
    public void before() {
    }

    @Test
    public void testCache() {
        demoService.findOrCreateItem(1);
        demoService.findOrCreateItem(2);
        demoService.findOrCreateItem(1);
    }
}
