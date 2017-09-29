package com.stanley.common.configuration;

import com.stanley.utils.PageInterceptor;
import org.apache.ibatis.io.VFS;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
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
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

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
        //开发时直接使用IDEA 执行main方法运行时一切正常，但是打成Jar 包后使用java -jar启动时配置的Domain 别名均会失效
        //DefaultVFS在获取jar上存在问题，使用springboot只能用SpringBootVFS的实现
        VFS.addImplClass(SpringBootVFS.class);
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        Configuration config = new Configuration();
        config.setMapUnderscoreToCamelCase(true);  //开启查询时字段名自动转驼峰功能
        config.setCallSettersOnNulls(true);  //查询结果为map时，数据为空的字段，则该字段显示为null （默认省略不显示）
        config.setCacheEnabled(true);  //开启缓存，用redis做缓存服务
        //配置默认的执行器。SIMPLE 执行器没有什么特别之处。REUSE 执行器重用预处理语句。
        //BATCH 执行器重用语句和批量更新(用batch会导致无返回值，慎用) config.setDefaultExecutorType(ExecutorType.BATCH);
        config.setLazyLoadingEnabled(false); //关闭延迟加载，初始化时一次性加载
        config.setDefaultStatementTimeout(9000);    //设置超时时间，它决定驱动等待一个数据库响应的时间
        bean.setConfiguration(config);
        //设置分页插件
        PageInterceptor pageInterceptor = new PageInterceptor();
        pageInterceptor.setDialect("mysql");
        bean.setPlugins(new Interceptor[]{pageInterceptor});
        //设置实体包的路径
        bean.setTypeAliasesPackage("com.stanley.common.domain.mybatis,"+aliasePackage);
        //设置xml路径
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mybatis/master/**/*.xml"));
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
