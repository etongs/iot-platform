package com.stanley.uams.initialize;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * ${DESCRIPTION}
 *
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/8/7

@WebFilter(urlPatterns = "/*", filterName = "myFilter")
 **/
public class TestFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("testFilter初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("过滤中");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("filter销毁");
    }
}
