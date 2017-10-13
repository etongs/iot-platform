package com.stanley.common.configuration;

import com.stanley.utils.CommonUtil;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 全局异常捕获处理
 * @date 2017/8/7
 * @author 13346450@qq.com 童晟
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * @Description 通用异常
     * @date 2017/10/12
     * @author 13346450@qq.com 童晟
     * @param
     * @return
     */
    @ExceptionHandler(Exception.class)
    public String defaultExceptionHandler(HttpServletRequest req, Exception e){
        e.printStackTrace();
        StringBuilder stringBuilder = new StringBuilder("<h5>内部程序错误：</h5>");
        stringBuilder.append(e.toString().replace("\r", "").replace("\n", "<br>"));
        return stringBuilder.toString();
    }

    /**
     * shiro权限异常
     */
    @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
    public String authorizationException(HttpServletRequest request, HttpServletResponse response) {
        if (CommonUtil.isAjax(request)) {
            CommonUtil.outputString(response,false,"您没有权限操作该功能！");
            return null;
        } else {
            return "redirect:/403";
        }
    }

}
