package com.stanley.common.annotation;

import java.lang.annotation.*;

/**
 * @Description 跨域设置
 * @date 2016年4月12日     
 * @since 1.0   
 * @version 1.0  
 * @author 13346450@qq.com 童晟
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
public @interface AllowCORS {

	String value() default "";

}
