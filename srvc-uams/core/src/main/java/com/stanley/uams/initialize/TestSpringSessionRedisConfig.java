package com.stanley.uams.initialize;

import org.springframework.context.annotation.Configuration;

/**
 * 无用了，被shiro的session管理
 * 初始化session-redis配置
 *  maxInactiveIntervalInSeconds: session超时秒数(8小时)
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/8/18
 *
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60 * 60 * 8)
 **/
public class TestSpringSessionRedisConfig {
}
