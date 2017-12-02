package org.mose.boot.configuration.cache;

import org.ehcache.jsr107.EhcacheCachingProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 缓存配置
 *
 * ps：使用注解的方式，同一个类内的方法互相调用返回结果无法建立缓存
 *
 * @Author: 靳磊
 * @Date: 2017/8/21:22
 */
@Configuration
@EnableCaching
public class EhcacheConfiguration {
}
