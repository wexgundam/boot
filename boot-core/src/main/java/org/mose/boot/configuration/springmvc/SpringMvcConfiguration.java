package org.mose.boot.configuration.springmvc;

import org.mose.boot.service.spring.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

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
public class SpringMvcConfiguration extends WebMvcConfigurerAdapter {
    @Autowired
    ResourceService resourceService;

    /**
     * 支持跨域请求
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowedHeaders("*");
    }

    /**
     * Spring boot默认创建了InternalResourceViewResolver
     *
     * 这里对InternalResourceViewResolver进行一些自定义配置
     *
     * @return
     */
    @Autowired
    public void configureViewResolver(InternalResourceViewResolver viewResolver) {
        viewResolver.getAttributesMap().putAll(resourceService.toMap());
    }
}
