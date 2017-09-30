package com.stanley.uams.shiro;

import com.stanley.utils.CommonUtil;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 角色过滤器，增加ajax的判断
 * 如果是ajax，直接输出，否则按原方法执行
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/9/30
 **/
public class MyRolesFilter extends RolesAuthorizationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        if(CommonUtil.isAjax((HttpServletRequest)request)){
            CommonUtil.outputString(httpServletResponse,true,"您所属的角色没有该功能权限，无法访问！");
        }else {
            super.onAccessDenied(request, response);
        }
        return false;
    }
}
