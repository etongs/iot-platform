package com.stanley.common.configuration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * thymeleaf 页面访问地址转换
 *
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/8/29
 **/
@Controller
public class PagesConvertController {

    @RequestMapping(value="{func1}/{pre}_html")
    public String thymeleafHandle(HttpServletRequest request,
                            @PathVariable("func1") String func1,
                            @PathVariable("pre")  String pre) {
        Enumeration enu=request.getParameterNames();
        while(enu.hasMoreElements()){
            String name=(String)enu.nextElement();
            String value=request.getParameter(name);
            request.setAttribute(name, value);
        }
        return func1+"/"+pre;
    }

    @RequestMapping(value="{func1}/{func2}/{pre}_html")
    public String jspHandle(HttpServletRequest request,
                            @PathVariable("func1") String func1,
                            @PathVariable("func2") String func2,
                            @PathVariable("pre")  String pre) {
        Enumeration enu=request.getParameterNames();
        while(enu.hasMoreElements()){
            String name=(String)enu.nextElement();
            String value=request.getParameter(name);
            request.setAttribute(name, value);
        }
        return func1+"/"+func2+"/"+pre;
    }

}
