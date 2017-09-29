package com.stanley.common.domain;

import java.io.Serializable;

/**
 *  @author zgj
 *	日期：2015年3月27日下午4:59:23
 *  描述：返回json的结果集基类
 */
public class JsonResultBean implements Serializable {
	
	private static final long serialVersionUID = -8871220520233872955L;
	
	/**
	 * 返回状态
	 */
	private Integer status;
	/**
	 * 状态信息
	 */
	private String messages;
	/**
	 * 数据集
	 */
	private Object data;
	
	/**
	 * 初始化值
	 * @param status
	 * @param messages
	 * @param data
	 */
	public JsonResultBean(Integer status, String messages, Object data) {
		super();
		this.status = status;
		this.messages = messages;
		this.data = data;
	}
	
	public Integer getStatus()
	{
		return status;
	}
	public void setStatus(Integer status)
	{
		this.status = status;
	}
	public String getMessages()
	{
		return messages;
	}
	public void setMessages(String messages)
	{
		this.messages = messages;
	}
	public Object getData()
	{
		return data;
	}
	public void setData(Object data)
	{
		this.data = data;
	}
}
