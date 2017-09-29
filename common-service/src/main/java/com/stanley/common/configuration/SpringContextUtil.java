package com.stanley.common.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring 上下文工具类
 * 在普通类可以通过调用SpringUtils.getAppContext()获取applicationContext对象
 *
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/8/19
 **/
@Component
public class SpringContextUtil implements ApplicationContextAware {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringContextUtil.applicationContext == null) {
            SpringContextUtil.applicationContext = applicationContext;
        }
        logger.info("========ApplicationContext配置成功,applicationContext={}========", SpringContextUtil.applicationContext);
    }

    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //通过name获取 Bean.
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }

}
