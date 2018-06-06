package org.mose.configuration.spirngmvc;

import org.mose.common.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import java.util.Map;

/**
 * what:    SpringMvc 配置
 */
@Configuration
public class ThymeleafConfiguration {
    /**
     * 资源地址获取服务
     */
    @Autowired
    ResourceService resourceService;

    /**
     * what:   这里对ThymeleafViewResolver进行一些自定义配置
     *
     * @return
     */
    @Autowired
    public void configureViewResolver(ThymeleafViewResolver viewResolver) {
        Map<? extends String, ?> resources = resourceService.toMap();
        for (String key : resources.keySet()) {
            viewResolver.addStaticVariable(key, resources.get(key));
        }
    }
}
