package com.stanley.uams.shiro.filter;

import com.stanley.utils.CommonUtil;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义用户过滤器（判断登录状态，是否ajax请求）
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/10/11
 **/
public class MyUserFilter extends UserFilter {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        log.debug("请求被拒绝后的跳转");
        if (CommonUtil.isAjax((HttpServletRequest)request)){
            this.saveRequest(request);
            CommonUtil.outputString((HttpServletResponse)response, false, "您的登录已过期或者未登录，请重新登录！");
        } else{
            this.saveRequest(request);
            WebUtils.issueRedirect(request, response, "/401");
        }
        return false;
    }
}
