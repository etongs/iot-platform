package com.stanley.utils;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.alibaba.fastjson.serializer.ValueFilter;

/**
 * fastJson的属性过滤器,将null转化为空""
* @ClassName: PropertyPreFilterHaveNull 
* @Description: TODO
* @author cqg
* @date 2015年5月21日 下午2:34:46 
*
 */
public class PropertyPreFilterHaveNull extends SimplePropertyPreFilter implements ValueFilter {
	/**
	 * 选择要序列化的类和[字段]
	 * @param clazz
	 * @param properties
	 */
	public PropertyPreFilterHaveNull(Class<?> clazz, String... properties) {
		super(clazz, properties);
	}

	@Override
	public Object process(Object object, String name, Object value) {
		if(value==null){
			return "";
		}
		return value;
	}
}
