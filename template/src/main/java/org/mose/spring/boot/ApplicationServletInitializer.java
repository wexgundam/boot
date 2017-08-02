package org.mose.spring.boot;

import org.mose.spring.boot.servlet.HttpSessionManager;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

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

        // Servlet context init parameters
        servletContext.setInitParameter("webAppRootKey", "template.root");

        // http session timeout
        servletContext.addListener(new HttpSessionManager());
    }
}


