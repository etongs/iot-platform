package com.stanley.common.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ${DESCRIPTION}
 *
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/8/9
 **/
public class BaseGateway {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @Description 公共的降级函数
     * @date 2017/8/9
     * @author 13346450@qq.com 童晟
     * @param
     * @return
     */
    protected String fallback(String str){
        return "网关调用出现异常，降级处理fallback";
    }

    protected String fallback(Integer str){
        return "网关调用出现异常，降级处理fallback";
    }

    protected String fallback(){
        return "网关调用出现异常，降级处理fallback";
    }



}
