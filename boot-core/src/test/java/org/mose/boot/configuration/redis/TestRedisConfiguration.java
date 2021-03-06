package org.mose.boot.configuration.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mose.system.service.SpringSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.session.Session;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;

/**
 * Description:
 *
 * @Author: 靳磊
 * @Date: 2017/8/14:23
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Profile("mysql")
@Transactional
public class TestRedisConfiguration {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    RedisConnectionFactory redisConnectionFactory;
    @Autowired
    SpringSessionService springSessionService;

    @Test
    public void testQueryAll() {
        Set<String> allKeys = springSessionService.getAllKeys();
        for (String key : allKeys) {
            Collection<? extends Session> sessions = springSessionService.getSessionsByKey(key);
            for (Session session : sessions) {
                SecurityContext securityContext = session.getAttribute("SPRING_SECURITY_CONTEXT");
            }
        }
        System.out.println(springSessionService.getSessionsByUsername("admin").size());
        System.out.println(redisTemplate.opsForValue().get("test"));
    }
}
