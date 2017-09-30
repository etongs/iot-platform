package com.stanley.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 公共的工具类
 *
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/9/30
 **/
public class CommonUtil {

    private static Logger log = LoggerFactory.getLogger("CommonUtil");

    /**
     * @Description 判断一个http请求是否是ajax请求
     * @date 2017/9/30
     * @author 13346450@qq.com 童晟
     * @param
     * @return
     */
    public static boolean isAjax(HttpServletRequest request){
        String header =  request.getHeader("X-Requested-With");
        if(!StringUtils.isNull(header)){
            if("XMLHttpRequest".equalsIgnoreCase(header))
                return true;
        }
        return false;
    }

    /**
     * @Description 用response输出字符串
     * @date 2017/9/30
     * @author 13346450@qq.com 童晟
     * @param
     * @return
     */
    public static void outputString(HttpServletResponse response, boolean isJson, String text){
        try {
            log.debug("response输出：{} ({}bytes) ", text, text.length());
            if(isJson)
                response.setContentType("application/json;charset="+ Constants.CHARSET);
            else
                response.setContentType("text/html;charset="+ Constants.CHARSET);

            PrintWriter out = response.getWriter();
            out.println(text);
            out.flush();
            out.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}
