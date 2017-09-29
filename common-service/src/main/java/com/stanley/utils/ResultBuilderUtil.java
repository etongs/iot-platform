package com.stanley.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.stanley.common.domain.JsonResultBean;
import com.stanley.common.domain.mybatis.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回值拼装类
 * @Description
 * @date 2016年3月25日     
 * @since 1.0   
 * @version 1.0  
 * @author 13346450@qq.com 童晟
 */
public class ResultBuilderUtil {
	/**
	 * 返回成功  {\"status\":1,\"message\":\"操作成功\"}"
	 */
	public final static String RESULT_SUCCESS = "{\"status\":1,\"message\":\"操作成功\"}";
	/**
	 * 校验成功 {\"valid\":true}
	 */
	public final static String VALIDATE_SUCCESS = "{\"valid\":true}";
	/**
	 * 校验失败 {\"valid\":false}
	 */
	public final static String VALIDATE_ERROR = "{\"valid\":false}";
	/**
	 * 返回session为空  "{\"status\":101,\"message\":\"未登录,或登录超时\",\"data\":{}}"
	 */
	public final static String RESULT_ERROR_SESSION_IS_NULL = "{\"status\":101,\"message\":\"未登录,或登录超时\",\"data\":{}}";
	/**
	 * 返回参数缺少  "{\"status\":102,\"message\":\"参数缺少或错误\",\"data\":{}}"
	 */
	public final static String RESULT_ERROR_MISSING_PARAMETER = "{\"status\":102,\"message\":\"参数缺少或错误\",\"data\":{}}";
	/**
	 * 返回无此cmd命令  "{\"status\":103,\"message\":\"无此cmd命令\",\"data\":{}}"
	 */
	public final static String RESULT_ERROR_INVALID_CMD = "{\"status\":103,\"message\":\"无此cmd命令\",\"data\":{}}";
	
	/**
	 * 过滤器，将null转化成""
	 */
	private static ValueFilter filter = new ValueFilter() {
	    @Override
	    public Object process(Object obj, String s, Object v) {
	    if(v==null)
	        return "";
	    return v;
	    }
	};

	/**
	 * 短信验证返回Json 格式："{\"status\":"+ status +"}"
	 * @param status
	 * @return
	 */
	public static String smsVerifyJson(String status)
	{
		return "{\"status\":"+ status +"}";
	}
	
	/**
	 * 执行正常，但返回异常数据信息
	 * @param status
	 * @param message
	 * @return
	 * @author 13346450@qq.com 童晟 
	 * @date 2016年3月25日
	 */
	public static String resultException(String status, String message){
		 return "{\"status\":"+status+",\"message\":\""+message+"\"}";
	}
	/**
	 * 直接把实体解析成json
	 * @Description
	 * @date 2017年4月14日
	 * @author 童晟  13346450@qq.com
	 * @param @param obj
	 * @param @return
	 * @return String
	 */
	public static String resultSuccessWithValue(Object obj) {
		return JSON.toJSONString(obj, filter, SerializerFeature.WriteDateUseDateFormat);
	}
	/**
	 * 返回包含返回值的字段值，采用fastjson组装
	 * @param map Map<String, Object>
	 * @return
	 * @author 13346450@qq.com 童晟 
	 * @date 2016年3月30日
	 */
	public static String resultSuccessWithValue(Map<String, Object> map) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("status", 1);
		returnMap.put("message", "操作成功");
		returnMap.put("data", map);
		return JSON.toJSONString(returnMap, filter, SerializerFeature.WriteDateUseDateFormat);
	}
	/**
	 * 返回包含返回值的字段值，采用fastjson组装
	 * @param list List<Map<String, Object>>
	 * @return
	 * @author 13346450@qq.com 童晟 
	 * @date 2016年4月28日
	 */
	public static String resultSuccessWithValue(List<Map<String, Object>> list) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("status", 1);
		returnMap.put("message", "操作成功");
		returnMap.put("data", list);
		return JSON.toJSONString(returnMap, filter, SerializerFeature.WriteDateUseDateFormat);
	}
	
	/**
	 * 返回包含返回值的字段值，采用fastjson组装
	 * @param
	 * @return
	 * @author 13346450@qq.com 童晟 
	 * @date 2016年4月28日
	 */
	public static String resultSuccessWithValue(Page<?> page) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("status", 1);
		returnMap.put("message", "操作成功");
		returnMap.put("data", page);
		return JSON.toJSONString(returnMap, filter, SerializerFeature.WriteDateUseDateFormat);
	}

	/**
	 * bean序列化成json串，用默认过滤器
	* @title      jsonBuild
	* @author  chen qige    
	* @date      2015年3月31日 
	*  @param jsonRs
	*  @return
	 */
	public static String jsonBuild(JsonResultBean jsonRs) {
		return JSON.toJSONString(jsonRs, filter, SerializerFeature.WriteDateUseDateFormat);
	}
	
	/**
	 * bean序列化成json串，制定过滤器
	 * @param jsonRs
	 * @param filters
	 * @return
	 */
	public static String jsonBuild(JsonResultBean jsonRs, PropertyPreFilterHaveNull filters)
	{
		return JSON.toJSONString(jsonRs, filters);
	}
	
	/**
	 * json转化成callback函数的参数传到前端，跨域时调用
	 * @param callback
	 * @param json
	 * @return
	 * @author 13346450@qq.com 童晟 
	 * @date 2016年5月23日
	 */
	public static String jsonTojsonp(String callback,String json){
		return callback+"("+json+")";
	}
	
	/**
	 * openApi返回包含返回值的字段值，采用fastjson组装
	 * @param map Map<String, Object>
	 * @return
	 * @author 13346450@qq.com 童晟 
	 * @date 2016年3月30日
	 */
	public static String openApiSuccess(Map<String, Object> map) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("code", 1);
		returnMap.put("message", "执行成功");
		if(null != map)
			returnMap.put("data", map);
		return JSON.toJSONString(returnMap, filter, SerializerFeature.WriteDateUseDateFormat);
	}
	
	/**
	 * openApi接口调用失败
	 * @param errorMsg
	 * @return
	 * @author 13346450@qq.com 童晟 
	 * @date 2017年1月4日
	 */
	public static String openApiError(String errorMsg) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("code", 0);
		returnMap.put("errorMsg", "执行失败！   错误提示："+errorMsg);
		return JSON.toJSONString(returnMap, filter);
	}
	
}
