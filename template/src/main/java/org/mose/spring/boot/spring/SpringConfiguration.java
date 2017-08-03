package org.mose.spring.boot.spring;

import org.mose.spring.boot.util.spring.SpringContextHolder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Description:配置Spring Application Context
 *
 * Spring boot默认创建的bean：
 * 1. JdbcTemplate
 * 2. NamedParameterJdbcTemplate
 *
 * Spring boot默认启动的annotation
 * 1. @EnableConfigurationProperties
 *
 * @Author: 靳磊
 * @Date: 2017/8/2:22
 */
@Configuration
public class SpringConfiguration {
    /**
     * 配置springContextConfiguration
     *
     * @return
     */
    @Bean
    public SpringContextHolder springContextConfiguration() {
        return new SpringContextHolder();
    }
}
