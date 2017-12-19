package org.mose.boot.configuration.springsecurity;

import org.mose.boot.system.modal.Authority;
import org.mose.boot.system.modal.User;
import org.mose.boot.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:Spring Security的java configuration
 *
 * @Author: 靳磊
 * @Date: 2017/7/19 13:47
 */
@Configuration
@EnableWebSecurity
public class AuthenticationConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;
    @Autowired
    AuthenticationSuccessHandler authenticationSuccessHandler;

    /**
     * Description：配置Spring Security
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
                .and().formLogin().loginPage("/login.htm").permitAll().loginProcessingUrl("/login")
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
     * Description:配置认证细节
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
     * Description:认证事件发布器
     *
     * @Author: 靳磊
     * @Date: 2017/8/1 16:55
     */
    @Bean
    public AuthenticationEventPublisher authenticationEventPublisher() {
        return new DefaultAuthenticationEventPublisher();
    }

    /**
     * 获取默认创建的UserDetailsService，开启分组功能，关闭用户直接授权功能，并发布为Spring Bean
     *
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetailsService userDetailsService = new UserDetailsService() {
            public static final String SPRING_SECURITY_GRANTED_AUTHORITY_PREFIX = "ROLE_";
            @Autowired
            UserService userService;

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                User user = userService.queryUserWithAuthoritiesByUsername(username);
                List<GrantedAuthority> grantedAuthorities =  new ArrayList<>();
                if (user.getAuthorities() != null && !user.getAuthorities().isEmpty()) {
                    for (Authority authority : user.getAuthorities()) {
                        grantedAuthorities.add(new SimpleGrantedAuthority(SPRING_SECURITY_GRANTED_AUTHORITY_PREFIX + authority.getName()));
                    }
                }
                org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
                        user.getUsername(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(), user.isCredentialsNonExpired(), user.isAccountNonLocked(), grantedAuthorities);
                return userDetails;
            }
        };
        return userDetailsService;
    }

    /**
     * 密码加密算法
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}