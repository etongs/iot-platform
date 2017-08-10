package com.stanley.uams.initialize;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 第二数据源初始化，没有第二数据源时，请关闭注解
 *
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/8/7
 *
@Configuration
@MapperScan(basePackages = "com.stanley.uams.mapper.slave", sqlSessionTemplateRef  = "slaveSqlSessionTemplate")
 **/
public class DataSourceSlave extends com.stanley.common.configuration.DataSourceSlaveConfig {
}
