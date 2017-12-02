/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:boot
 * Module Name:TODO:Module
 */
package org.mose.boot.configuration.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * what:    (这里用一句话描述这个类的作用). <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author TODO:Who created on 2017/12/2
 */
@Component
public class MockService {
    @Cacheable(value = "tileImageCache")
    public String findOrCreateItem(int id) {
        System.out.println("create item[" + id + "]");
        return Integer.toString(id);
    }
}
