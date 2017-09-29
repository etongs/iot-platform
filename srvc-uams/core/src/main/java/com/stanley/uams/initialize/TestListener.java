package com.stanley.uams.initialize;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * ${DESCRIPTION}
 *
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/8/7

@WebListener
 **/
public class TestListener implements ServletContextListener{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("测试监听器已启动");
    }


    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("测试监听器销毁");
    }
}
