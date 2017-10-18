package org.mose.springboot.redis;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Description: 配置Redis环境
 *
 * @Author: 靳磊
 * @Date: 2017/10/18 10:50
 */
@Configuration
@PropertySource("classpath:/config/application-redis.properties")
public class RedisConfiguration {
}
