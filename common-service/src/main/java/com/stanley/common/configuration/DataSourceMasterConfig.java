package com.stanley.common.configuration;

import com.stanley.utils.PageInterceptor;
import com.stanley.utils.StringUtils;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * mybatis主数据源配置（基类）
 *
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/8/2
 **/
public class DataSourceMasterConfig {
    @Value("${mybatis.aliase-package}")
    private String aliasePackage;

    @Bean(name = "masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    @Primary
    public DataSource createDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory createSqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        org.apache.ibatis.session.Configuration  config = new org.apache.ibatis.session.Configuration();
        config.setMapUnderscoreToCamelCase(true);  //开启查询时字段名自动转驼峰功能
        config.setCallSettersOnNulls(true);  //查询结果为map时，数据为空的字段，则该字段显示为null （默认省略不显示）
        config.setCacheEnabled(false);  //开启缓存，先暂停
        config.setLazyLoadingEnabled(false); //关闭延迟加载，初始化时一次性加载
        config.setDefaultStatementTimeout(7000);    //设置超时时间，它决定驱动等待一个数据库响应的时间
        bean.setConfiguration(config);
        //设置分页插件
        PageInterceptor pageInterceptor = new PageInterceptor();
        pageInterceptor.setDialect("mysql");
        bean.setPlugins(new Interceptor[]{pageInterceptor});
        //设置实体包的路径
        bean.setTypeAliasesPackage("com.stanley.common.domain.mybatis,"+aliasePackage);
        //设置xml路径
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mybatis/master/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "masterTransactionManager")
    @Primary
    public DataSourceTransactionManager createTransactionManager(@Qualifier("masterDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "masterSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate createSqlSessionTemplate(@Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
