package org.mose.springboot.springsecurity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.stereotype.Component;

/**
 * 接收Spring Security发布的AbstractAuthenticationEvent
 *
 * Created by Administrator on 2017/8/1.
 */
@Component
public class AuthenticationEventListener implements ApplicationListener<AbstractAuthenticationEvent> {
    private static Logger logger = LoggerFactory.getLogger("sysLog");

    @Override
    public void onApplicationEvent(AbstractAuthenticationEvent event) {
        logger.info("Receive event of type:" + event.getClass().getName() + ":" + event.toString());
    }
}
