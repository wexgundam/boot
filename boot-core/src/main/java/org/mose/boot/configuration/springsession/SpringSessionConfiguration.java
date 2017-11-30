package org.mose.boot.configuration.springsession;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Description:Spring Session配置类
 *
 * 负责实现session共享
 *
 * @Author: 靳磊
 * @Date: 2017/9/30 10:04
 */
@EnableRedisHttpSession
public class SpringSessionConfiguration {
}
