package com.stanley.uams.gateway;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.stanley.apiutils.exception.RemoteCallException;
import com.stanley.common.spring.BaseGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

/**
 * ${DESCRIPTION}
 *
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/8/7
 **/
@Component
public class TestFeignGateway extends BaseGateway {

    @Resource
    private TestFeignInterface testFeignInterface;

    private Random random = new Random();

    @HystrixCommand(fallbackMethod = "fallback",ignoreExceptions = RemoteCallException.class)
    public String callRemote(String name){
        logger.debug("调用callRemot");
        return testFeignInterface.callRemoteService(name);
    }

    @HystrixCommand(fallbackMethod = "fallback",ignoreExceptions = RemoteCallException.class)
    public String mockCall(String name){
        logger.debug("调mockCall");
        int randomInt= random.nextInt(10) ;
        logger.debug("radom int = {}",randomInt);
        if(randomInt<4){  //模拟调用失败情况
            throw new RuntimeException("mockCall 异常,name="+name);
        }else{
            return "mockCall: 成功,name="+name+"， 随机数="+randomInt;
        }
    }

}
