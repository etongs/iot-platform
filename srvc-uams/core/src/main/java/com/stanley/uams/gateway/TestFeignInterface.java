package com.stanley.uams.gateway;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ${DESCRIPTION}
 *
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/8/7
 **/
@FeignClient(value = "customer-ribbon")
public interface TestFeignInterface {

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    String callRemoteService(@RequestParam(value = "name") String name);

}
