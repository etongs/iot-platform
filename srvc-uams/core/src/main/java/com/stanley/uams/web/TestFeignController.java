package com.stanley.uams.web;

import com.stanley.uams.service.TestFeignService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ${DESCRIPTION}
 *
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/8/7
 **/
@RestController
public class TestFeignController {

    @Resource
    private TestFeignService testFeignService;

    @RequestMapping("/testFeign")
    public String hi(String name){
        return testFeignService.hi(name);
    }

    @RequestMapping("/testHystrix")
    public String hu(String name){
        return testFeignService.callMock(name);
    }


}
