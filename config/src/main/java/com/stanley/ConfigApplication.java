package com.stanley;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Spring Cloud Config 配置服务器
 * @date 2017/8/15
 * @author 13346450@qq.com 童晟
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class ConfigApplication {

	/**
	 * @Description
	 * 需要配置profile为native才能读取本地配置
	 * @date 2017/8/15
	 * @author 13346450@qq.com 童晟
	 * @param
	 * @return
	 */
	public static void main(String[] args) {
		new SpringApplicationBuilder(ConfigApplication.class).web(true).profiles("native").run(args);
	}
}
