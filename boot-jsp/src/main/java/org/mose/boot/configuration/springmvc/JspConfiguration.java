package org.mose.boot.configuration.springmvc;

import org.mose.common.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * what:    SpringMvc 配置
 */
@Configuration
public class JspConfiguration {
    /**
     * 资源地址获取服务
     */
    @Autowired
    ResourceService resourceService;

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
}
