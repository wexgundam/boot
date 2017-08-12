package org.mose.springboot.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Description:
 *
 * @Author: 靳磊
 * @Date: 2017/8/12:21
 */
@Configuration
public class DaoConfiguration {
    @Profile("dev")
    @Bean
    public IPaging mysqlPaging() {
        return new MysqlPaging();
    }

    @Profile("prod")
    @Bean
    public IPaging oraclePaging() {
        return new OraclePaging();
    }
}
