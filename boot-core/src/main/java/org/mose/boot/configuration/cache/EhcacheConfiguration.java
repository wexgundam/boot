package org.mose.boot.configuration.cache;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * what:    缓存配置
 * warning: 使用注解的方式，同一个类内的方法互相调用返回结果无法建立缓存
 *
 * @Author: 靳磊
 * @Date: 2017/8/21:22
 */
@Configuration
@EnableCaching
public class EhcacheConfiguration {
}
