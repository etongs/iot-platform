package com.stanley.uams.shiro;

import com.stanley.utils.CommonUtil;
import org.apache.shiro.web.filter.authc.UserFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户过滤器，增加ajax的判断
 * 如果是ajax，直接输出，否则按原方法执行
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/9/30
 **/
public class MyUserFilter extends UserFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        if(CommonUtil.isAjax((HttpServletRequest)request)){
            CommonUtil.outputString(httpServletResponse,true,"您没有该功能的权限，请返回！");
        }else {
            super.onAccessDenied(request, response);
        }
        return false;
    }
}
