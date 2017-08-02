package org.mose.spring.boot.sitemesh;

import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;

/**
 * sitemesh映射
 */
//@Configuration
public class SitemeshConfiguration {
    /**
     * 设置sitemesh映射
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean sitemesh() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new ConfigurableSiteMeshFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setDispatcherTypes(DispatcherType.FORWARD, DispatcherType.ERROR);
        return filterRegistrationBean;
    }
}