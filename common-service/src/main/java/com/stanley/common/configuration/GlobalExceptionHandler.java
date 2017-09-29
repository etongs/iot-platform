package com.stanley.common.configuration;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 全局异常捕获处理
 * @date 2017/8/7
 * @author 13346450@qq.com 童晟
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String defaultExceptionHandler(HttpServletRequest req, Exception e){
        e.printStackTrace();
        StringBuilder stringBuilder = new StringBuilder("<h4>内部程序错误，请稍后重试。</h4>");
        stringBuilder.append(e.toString().replace("\r", "").replace("\n", "<br>"));
        return stringBuilder.toString();
    }

}
