package org.mose.boot.configuration.springmvc;

import org.mose.boot.common.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.Thymeleaf;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import java.util.Map;

/**
 * what:    SpringMvc 配置
 */
@Configuration
public class SpringMvcConfiguration extends WebMvcConfigurerAdapter {
    /**
     * 资源地址获取服务
     */
    @Autowired
    ResourceService resourceService;

    /**
     * what:    支持跨域请求
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
     * what:    Spring boot默认创建了InternalResourceViewResolver
     * <p>
     * 这里对InternalResourceViewResolver进行一些自定义配置
     *
     * @return
     */
    @Autowired
    public void configureViewResolver(InternalResourceViewResolver viewResolver) {
        viewResolver.getAttributesMap().putAll(resourceService.toMap());
    }

    @Autowired
    public void configureViewResolver(ThymeleafViewResolver viewResolver) {
        Map<? extends String, ?> resources = resourceService.toMap();
        for (String key : resources.keySet()) {
            viewResolver.addStaticVariable(key, resources.get(key));
        }
    }


    /**
     * what:    添加拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new OperationInterceptor());
    }
}
