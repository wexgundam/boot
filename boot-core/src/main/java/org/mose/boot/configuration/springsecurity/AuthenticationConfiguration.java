package org.mose.boot.configuration.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.ErrorPageFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.DispatcherType;
import javax.swing.*;

/**
 * what:    Spring Security的java configuration
 *
 * @Author: 靳磊
 * @Date: 2017/7/19 13:47
 */
@Configuration
@EnableWebSecurity
public class AuthenticationConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    AuthenticationSuccessHandler authenticationSuccessHandler;

    /**
     * what:    配置Spring Security
     * <p>
     * 1.允许任何人访问登录地址（"/loging.html"）
     * 2.用户认证处理地址("/login")
     * 3.允许任何人访问静态资源目录（"/assets/**"）
     * 4.开启对任何地址（"/**"）的访问控制，要求必须具备"ROLE_USER"的角色、
     * 5.开启默认form表单形式的用户登入，访问地址为"/login.jsp"，登录成功后自动跳转到用户前一次的访问地址
     * 6.开启remember me设置
     * 7.设置用户信息获取服务
     * 8.关闭csrf限制，该功能以后再讲，默认为开启状态<br>
     *
     * @param http
     *
     * @return
     *
     * @Author: 靳磊
     * @Date: 2017/7/19 13:47
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/assets/**").permitAll()
                .antMatchers("/**").hasRole("User")
                .and().formLogin().loginPage("/login.htm").permitAll().loginProcessingUrl("/login.htm")
                .successHandler(authenticationSuccessHandler)
                .and().logout().logoutUrl("/logout.htm").permitAll()
                //配置未授权处理地址
                .and().exceptionHandling().accessDeniedPage("/common/result/denied.htm")
                //Spring Security的默认启用防止固化session攻击
                .and().sessionManagement().sessionFixation().migrateSession()
                //设置session最大并发数为1，当建立新session时，原session将expired，并且跳转到登录界面
                .maximumSessions(1).expiredUrl("/login.htm")
                .and().and().csrf().disable();
    }

    /**
     * what:    配置认证细节
     *
     * @param auth
     *
     * @return
     *
     * @Author: 靳磊
     * @Date: 2017/7/21 17:04
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, UserDetailsService userDetailsService) throws Exception {
        auth
                .authenticationEventPublisher(authenticationEventPublisher())//注入事件发布者
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());//启用密码加密功能
    }

    /**
     * what:    认证事件发布器
     *
     * @Author: 靳磊
     * @Date: 2017/8/1 16:55
     */
    @Bean
    public AuthenticationEventPublisher authenticationEventPublisher() {
        return new DefaultAuthenticationEventPublisher();
    }

    /**
     * what:    密码加密算法
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * what:    增加filter，用于处理Error信息.<br/>
     *          原因是Spring Security在处理Error信息时将信息转发到tomcat，绕过Spring，<br/>
     *          增加该FIlter后能够拦截error信息 <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/8/27
     */
    @Bean
    public FilterRegistrationBean errorFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new ErrorPageFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ERROR);
        return filterRegistrationBean;
    }
}