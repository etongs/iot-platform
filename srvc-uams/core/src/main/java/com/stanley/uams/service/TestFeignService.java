package com.stanley.uams.service;

import com.stanley.uams.gateway.TestFeignGateway;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ${DESCRIPTION}
 *
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/8/7
 **/
@Service
public class TestFeignService {

    @Resource
    private TestFeignGateway testFeignGateway;


    public String hi(String name){
        return testFeignGateway.callRemote(name);
    }

    public String callMock(String name){
        return testFeignGateway.mockCall(name);
    }


}
