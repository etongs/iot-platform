package com.stanley.uams.initialize;

import com.stanley.common.configuration.DataSourceMasterConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 主数据源初始化
 *
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/8/7
 **/
@Configuration
@MapperScan(basePackages = {"com.stanley.uams.mapper.master"}, sqlSessionTemplateRef  = "masterSqlSessionTemplate")
public class DataSourceMaster extends DataSourceMasterConfig {
}
