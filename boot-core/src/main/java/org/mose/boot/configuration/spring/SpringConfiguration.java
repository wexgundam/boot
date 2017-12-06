package org.mose.boot.configuration.spring;

import org.mose.boot.common.dao.IPaging;
import org.mose.boot.common.dao.MysqlPaging;
import org.mose.boot.common.dao.OraclePaging;
import org.mose.boot.util.spring.SpringContextHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

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

    @Bean
    @Scope("prototype")
    @Profile("mysql")
    public IPaging mysqlPaging() {
        return new MysqlPaging();
    }

    @Bean
    @Scope("prototype")
    @Profile("oracle")
    public IPaging oraclePaging() {
        return new OraclePaging();
    }
}
