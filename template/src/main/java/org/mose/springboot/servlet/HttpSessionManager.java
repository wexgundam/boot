package org.mose.springboot.servlet;

import javax.servlet.http.HttpSessionEvent;

/**
 * Description:设置Http Session Timeout
 *
 * @Author: 靳磊
 * @Date: 2017/8/2 11:08
 */
public class HttpSessionManager implements javax.servlet.http.HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        event.getSession().setMaxInactiveInterval(30 * 60);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
    }
}