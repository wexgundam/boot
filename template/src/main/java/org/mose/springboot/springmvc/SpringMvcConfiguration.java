package org.mose.springboot.springmvc;

import org.mose.springboot.spring.ResourceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.MultipartConfigElement;

/**
 * SpringMvc 配置
 *
 * Spring boot默认配置了如下bean：
 * 1. MappingJackson2HttpMessageConverter设置Controller注解，及json格式解析器
 * 2. RequestMappingHandlerAdapter处理请求头、获取handlerMethod等
 * 3. InternalResourceViewResolver设置Jsp视图解析器
 * 4. 提供了Multipart File Uploads功能
 * 5. ......
 */
@Configuration
@PropertySource("classpath:/config/application-springmvc.properties")
public class SpringMvcConfiguration extends WebMvcConfigurerAdapter {
    @Autowired
    ResourceConfiguration resourceConfiguration;

    /**
     * Spring boot默认创建了InternalResourceViewResolver
     *
     * 这里对InternalResourceViewResolver进行一些自定义配置
     *
     * @return
     */
    @Autowired
    public void configureViewResolver(InternalResourceViewResolver viewResolver) {
        viewResolver.getAttributesMap().putAll(resourceConfiguration.toMap());
    }
}
