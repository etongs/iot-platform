package com.stanley.uams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;

/**
 * 权限管理服务
 * 包括用户、机构、角色、权限、菜单、公共接口配置等基础架构功能
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/8/4
 * @ServletComponentScan
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@ComponentScan(basePackages = {"com.stanley.common.annotation","com.stanley.common.configuration","com.stanley.common.dao","com.stanley.uams"})
public class UamsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UamsApplication.class, args);
    }
}
