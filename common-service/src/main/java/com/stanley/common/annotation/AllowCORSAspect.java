package com.stanley.common.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 允许跨域CORS的POST提交
 * @Description
 * @date 2016年8月23日     
 * @since 1.0   
 * @version 1.0  
 * @author 13346450@qq.com 童晟
 */
@Component
@Aspect
public class AllowCORSAspect {
	protected Logger log = LoggerFactory.getLogger(this.getClass());

	@Value("${cors.allow.url}")
	private String allowUrl;
	
	/**
	 * 切入点为有AllowCORS注解的方法
	 * @author 13346450@qq.com 童晟 
	 * @date 2016年8月24日
	 * 用execution这句也可以@Pointcut("execution(* com.eacha.controller.background.business.content.*Controller.*(..))")
    */
    @Pointcut("@annotation(com.stanley.common.annotation.AllowCORS)")
    public void controllerCORSAspect() {}
	
    /**
     * 前置通知，用于设置response的Access-Control-Allow-Origin 
     * 跨域允许访问的范围（域名）  *表示不限制 
     * @param joinPoint
     * @author 13346450@qq.com 童晟 
     * @date 2016年8月24日
     */
    @Before("controllerCORSAspect()")
    public void doBefore(JoinPoint joinPoint) {
    	log.debug("CORS前置通知，请求的方法  {}", joinPoint.getTarget().getClass().getName() + "."  
                        + joinPoint.getSignature().getName() + "()");
    	HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    	if(allowUrl.indexOf(",")==-1){
    		response.addHeader("Access-Control-Allow-Origin", allowUrl);
    	}else{
	    	HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	    	String origin=request.getHeader("Origin");
	    	log.debug("请求的域名:{}",origin);    	
    		Boolean isAllow=false;
    		for(String domain : allowUrl.split(",")){
    			if(origin.equals(domain))
    			{
    				isAllow=true;
    				break;
    			}
    		}
    		if(isAllow)
    		{
    			response.addHeader("Access-Control-Allow-Origin",origin);
    		}
    	}
    }

}
