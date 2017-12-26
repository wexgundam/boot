package org.mose.boot;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.util.WebAppRootListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * what:    以war包形式部署到web容器的启动类
 * when:    当以war形式使用spring boot时，通过该类替换web.xml
 * warning: 只能在servlet3.1以上环境使用
 */
public class ApplicationServletInitializer extends SpringBootServletInitializer {
    /**
     * 配置spring boot application initializer
     *
     * @param builder
     *
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ApplicationInitializer.class);
    }

    /**
     * 容器启动后增加配置
     *
     * @param servletContext
     *
     * @throws ServletException
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);

        // 监控在线用户
        servletContext.addListener(new HttpSessionEventPublisher());

        // 通过System.getProperty("template.root")获取项目的路径
        servletContext.setInitParameter("webAppRootKey", "template.root");
        servletContext.addListener(WebAppRootListener.class);

        // 建立Spring Bean对request,session,globalsession三种作用域的支持
        servletContext.addListener(RequestContextListener.class);
    }
}


