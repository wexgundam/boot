package org.mose.springboot.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mose.springboot.springsession.service.SpringSessionRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
    SpringSessionRedisService springSessionRedisService;

    @Test
    public void testQueryAll() {
        Set<String> allKeys = springSessionRedisService.getAllKeys();
        for (String key : allKeys) {
            Collection<? extends Session> sessions = springSessionRedisService.getSessionsByKey(key);
            for (Session session : sessions) {
                SecurityContext securityContext = session.getAttribute("SPRING_SECURITY_CONTEXT");
            }
        }
        System.out.println(springSessionRedisService.getSessionsByPrincple("admin").size());
        System.out.println(redisTemplate.opsForValue().get("test"));
    }
}
