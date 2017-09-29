package com.stanley.uams.domain.auth;

import com.stanley.common.domain.TreeStructure;

/**
 * @Module SysResource
 * @date 2016-04-13
 * @since 1.0 
 * @version 1.0 
 * @author 13346450@qq.com 童晟
 */
public class SysResource extends TreeStructure {

	/**资源表达式*/
	private String expression;
	/**
	 * 资源地址
	 */
	private String url;
	/**
	 * 类型，1-菜单 2-按钮
	 */
	private Integer resType;

	/**获取资源表达式*/
	public String getExpression() {
		return expression;
	}
	/**设置资源表达式*/
	public void setExpression(String expression) {
		this.expression = expression;
	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getResType() {
		return resType;
	}

	public void setResType(Integer resType) {
		this.resType = resType;
	}
}
