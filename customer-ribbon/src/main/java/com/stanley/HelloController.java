package com.stanley;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

/**
 * ${DESCRIPTION}
 *
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/8/3
 **/
@RestController
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${server.port}")
    private String port;
    @Value("${spring.application.name}")
    private String myname;

    @RequestMapping("/hi")
    public String index(@RequestParam String name){
        return "服务提供者，参数值="+name+",我的服务名="+myname+", 我的端口="+port;
    }


    @RequestMapping("hytest")
    @HystrixCommand(fallbackMethod = "hiErrors")
    public String hyTest(String name){
        int randomInt= new Random().nextInt(10) ;
        logger.debug("radom int = {}",randomInt);
        if(randomInt<4)
            throw new RuntimeException("hytest 异常,name="+name);
        else
            return "我是"+myname+"服务,名字="+name+",端口："+port+"..  Hystrix的customer-ribbon测试";
    }

    @RequestMapping("hytest2")
    @HystrixCommand(fallbackMethod = "hiErrors")
    public String hyTest2(String name){
        int randomInt= new Random().nextInt(10) ;
        logger.debug("radom2 int = {}",randomInt);
        if(randomInt<4)
            throw new RuntimeException("hytest2 异常,name="+name);
        else
            return "我是"+myname+"服务2,名字2="+name+",端口2："+port+"..  Hystrix的customer-ribbon测试2";
    }


    public String hiErrors(String name){
        return "Hystrix的customer-ribbon测试。 出错了， 降级处理"+name;
    }

}
