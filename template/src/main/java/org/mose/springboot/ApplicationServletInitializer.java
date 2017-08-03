package org.mose.springboot;

import org.mose.springboot.servlet.HttpSessionManager;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.util.WebAppRootListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * 以war包形式部署到web容器的启动类
 */
public class ApplicationServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ApplicationInitializer.class);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);


        // http session timeout
        servletContext.addListener(HttpSessionManager.class);

        // 通过System.getProperty("template.root")获取项目的路径
        servletContext.setInitParameter("webAppRootKey", "template.root");
        servletContext.addListener(WebAppRootListener.class);

        // 建立Spring Bean对request,session,globalsession三种作用域的支持
        servletContext.addListener(RequestContextListener.class);
    }
}


