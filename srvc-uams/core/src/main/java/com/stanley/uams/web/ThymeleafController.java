package com.stanley.uams.web;

import com.stanley.uams.service.SysLogsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * ${DESCRIPTION}
 *
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/8/7
 **/
@Controller
public class ThymeleafController {

    @Resource
    private SysLogsService sysLogsService;

    @RequestMapping("/thymeleafTest")
    public String hello(ModelMap modelMap){
        modelMap.put("name","测试");
        modelMap.put("logsin",sysLogsService.getOne(44947));
        return "hello_thy";
    }

}
