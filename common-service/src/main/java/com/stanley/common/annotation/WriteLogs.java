package com.stanley.common.annotation;

import java.lang.annotation.*;

/**
 * @Description 写日志到数据库
 * @date 2017年10月25日
 * @since 1.0   
 * @version 1.0  
 * @author 13346450@qq.com 童晟
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
public @interface WriteLogs {

	String value() default "";

}
