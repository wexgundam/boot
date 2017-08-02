package org.mose.spring.boot.springmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

/**
 * SpringMvc 配置
 *
 * Spring boot默认配置了如下bean：
 * 1. MappingJackson2HttpMessageConverter设置Controller注解，及json格式解析器
 * 2. RequestMappingHandlerAdapter处理请求头、获取handlerMethod等
 * 3. InternalResourceViewResolver设置Jsp视图解析器
 */
@Configuration
public class SpringMvcConfiguration extends WebMvcConfigurerAdapter {
    @Value("${static.resource.server.url}")
    private String staticResourceServerUrl;
    @Value("${dynamic.resource.server.url}")
    private String dynamicResourceServerUrl;
    @Value("${web.title}")
    private String webTitle;

    /**
     * Spring boot默认创建了InternalResourceViewResolver
     *
     * 这里对InternalResourceViewResolver进行一些自定义配置
     *
     * @return
     */
    @Autowired
    public void configureViewResolver(InternalResourceViewResolver viewResolver) {
        viewResolver.getAttributesMap().put("staticResourceServerUrl", staticResourceServerUrl);
        viewResolver.getAttributesMap().put("dynamicResourceServerUrl", dynamicResourceServerUrl);
        viewResolver.getAttributesMap().put("webTitle", webTitle);
    }

    /**
     * 设置支持上传文件
     *
     * @return
     */
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        super.extendHandlerExceptionResolvers(exceptionResolvers);
    }



    //    @Bean
//    public HandlerInterceptor checkLoginInterceptor() {
//        return new CheckLoginInterceptor();
//    }
//
//    @Bean
//    public HandlerInterceptor authorityInterceptor() {
//        return new AuthorityInterceptor();
//    }

//    /**
//     * 设置拦截器，处理登录及权限控制
//     *
//     * @param registry
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 多个拦截器组成一个拦截器链
//        // addPathPatterns 用于添加拦截规则
//        // excludePathPatterns 用户排除拦截
//        // 拦截器按照声明的顺序执行
//        registry.addInterceptor(checkLoginInterceptor()).addPathPatterns("index.htm", "/sys/*/*");
//        registry.addInterceptor(authorityInterceptor()).addPathPatterns("/sys/*/*");
//        super.addInterceptors(registry);
//    }


//    /**
//     * TODO 设置支持上传文件
//     *
//     * @return
//     */
//    @Bean
//    public CommonsMultipartResolver multipartResolver() {
//        return new CommonsMultipartResolver();
//    }
//
//    @Bean
//    public PubConfig pubConfig() {
//        PubConfig pubConfig = new PubConfig();
//        pubConfig.setDynamicServer(dynamicResourceServerUrl);
//        pubConfig.setStaticServer(staticResourceServerUrl);
//        return pubConfig;
//    }
}
