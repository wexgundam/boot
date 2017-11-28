package org.mose.boot.redis;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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
//    @Autowired
//    StringRedisTemplate redisTemplate;
//    @Autowired
//    RedisConnectionFactory redisConnectionFactory;
//    @Autowired
//    SpringSessionRedisService springSessionRedisService;
//
//    @Test
//    public void testQueryAll() {
//        Set<String> allKeys = springSessionRedisService.getAllKeys();
//        for (String key : allKeys) {
//            Collection<? extends Session> sessions = springSessionRedisService.getSessionsByKey(key);
//            for (Session session : sessions) {
//                SecurityContext securityContext = session.getAttribute("SPRING_SECURITY_CONTEXT");
//            }
//        }
//        System.out.println(springSessionRedisService.getSessionsByPrincple("admin").size());
//        System.out.println(redisTemplate.opsForValue().get("test"));
//    }
}
