package com.stanley.common.configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Druid配置
 *
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/8/22
 **/
@Configuration
public class DruidConfig {
    /**
     * druid servlet
     * @return
     */
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        servletRegistrationBean.addInitParameter("loginUsername","admin");
        servletRegistrationBean.addInitParameter("loginPassword","admin1234");
        return servletRegistrationBean;
    }

    /**
     * 增加不用过滤的 druid
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean(@Value("${staticfile.suffix}") String suffix) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", suffix+",/druid/*");
        return filterRegistrationBean;
    }

}
