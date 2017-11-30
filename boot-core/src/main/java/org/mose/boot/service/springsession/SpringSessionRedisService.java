package org.mose.boot.service.springsession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
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
public class SpringSessionRedisService {
    @Autowired
    @Qualifier("sessionRedisTemplate")
    RedisTemplate sessionRedisTemplate;
    @Autowired
    private RedisOperationsSessionRepository sessionRepository;

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
    public Collection<? extends Session> getSessionsByPrincple(String principle) {
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
}
