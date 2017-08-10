package com.stanley;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证过滤器
 *
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/8/9
 **/
@Component
public class ValidateFilter extends ZuulFilter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        logger.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getParameter("token");
        /*if(accessToken == null) {
            logger.warn("token 为空，验证失败！");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().setContentType("text/html;charset=UTF-8");
                ctx.getResponse().setCharacterEncoding("UTF-8");
                ctx.getResponse().getWriter().println("token 为空，验证失败！");
            }catch (Exception e){}
            return null;
        }*/
        logger.info("zuul过滤器通过");
        return null;
    }
}
