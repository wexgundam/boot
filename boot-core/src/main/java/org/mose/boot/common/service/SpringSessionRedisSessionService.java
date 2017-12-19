package org.mose.boot.common.service;

import org.mose.boot.system.modal.Authority;
import org.mose.boot.system.modal.Role;
import org.mose.boot.system.modal.User;
import org.mose.boot.system.service.RoleAuthorityService;
import org.mose.boot.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Description:基于Spring Session提供的服务
 *
 * @Author: 靳磊
 * @Date: 2017/9/30 15:03
 */
@Service
public class SpringSessionRedisSessionService implements ISessionService {
    private static final String SPRING_SECURITY_CONTEXT = "SPRING_SECURITY_CONTEXT";
    @Autowired
    @Qualifier("sessionRedisTemplate")
    RedisTemplate sessionRedisTemplate;
    @Autowired
    private RedisOperationsSessionRepository sessionRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleAuthorityService roleAuthorityService;

    /**
     * Description:获得待查询的用户的redis key
     *
     * @param principle 用户账户名称，如果principle==null，则查询全部用户的key
     *
     * @return
     *
     * @Author: 靳磊
     * @Date: 2017/9/30 15:05
     */
    private String getPrincipleKey(String principle) {
        StringBuffer key = new StringBuffer();
        key.append("*:").append(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME).append(":");
        key.append(principle == null ? "*" : principle);
        return key.substring(0);
    }


    /**
     * Description: 获取给定在线用户名对应存储在的redis的session的key集合
     *
     * @param principle
     *
     * @return
     *
     * @Author: 靳磊
     * @Date: 2017/9/30 15:09
     */
    public Set<String> getKeys(String principle) {
        return sessionRedisTemplate.keys(getPrincipleKey(principle));
    }

    /**
     * Description: 获取所有在线用户对应存储在的redis的session的key集合
     *
     * @return
     *
     * @Author: 靳磊
     * @Date: 2017/9/30 15:09
     */
    public Set<String> getAllKeys() {
        return sessionRedisTemplate.keys(getPrincipleKey(null));
    }

    public Set<String> getSessionIds(String principle) {
        return this.sessionRedisTemplate.boundSetOps(getPrincipleKey(principle)).members();
    }

    /**
     * Description: 从Redis中获取对应用户名的session集合
     *
     * @return
     *
     * @Author: 靳磊
     * @Date: 2017/9/30 15:09
     */
    public Collection<? extends Session> getSessionsByUsername(String principle) {
        return sessionRepository.findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, principle).values();
    }

    /**
     * Description: 从Redis中获取对应key的session
     *
     * @return
     *
     * @Author: 靳磊
     * @Date: 2017/9/30 15:09
     */
    public Collection<? extends Session> getSessionsByKey(String key) {
        List<Session> sessions = new ArrayList<>();
        for (Object sessionId : sessionRedisTemplate.boundSetOps(key).members()) {
            sessions.add(sessionRepository.getSession((String) sessionId));
        }
        return sessions;
    }

    @Override
    public void deleteAllSessionsByRoleId(int roleId) {
        List<User> users = userService.queryAllUsersByRoleId(roleId);
        for (User user : users) {
            List<Authority> authorities = roleAuthorityService.queryAllAuthoritiesByUserId(user.getId());
            user.setAuthorities(authorities);
            for (Session session : getSessionsByUsername(user.getUsername())) {
                sessionRepository.delete(session.getId());
            }
        }
    }

    @Override
    public void deleteAllSessionsByAuthorityId(int authorityId) {
        List<Role> roles = roleAuthorityService.queryAllRolesByAuthorityId(authorityId);
        for (Role role : roles) {
            deleteAllSessionsByRoleId(role.getId());
        }
    }

    @Override
    public void deleteAllSessionsByUserId(int userId) {
        User user = userService.queryUser(userId);
        if (user != null) {
            for (Session session : getSessionsByUsername(user.getUsername())) {
                sessionRepository.delete(session.getId());
            }
        }
    }
}
