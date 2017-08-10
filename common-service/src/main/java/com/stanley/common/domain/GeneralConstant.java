package com.stanley.common.domain;

import java.io.Serializable;

/**
 * 通用的常量bean
 * @Description
 * @date 2016年4月14日     
 * @since 1.0   
 * @version 1.0  
 * @author 13346450@qq.com 童晟
 */
public class GeneralConstant implements Serializable {
	private static final long serialVersionUID = 1784193792179798936L;
	
	private Integer idKey;	    //主键、值(整形)
	private String  idKeyString;	//主键、值(字符形)
	private String  textValue;	//显示列
	
	public GeneralConstant(Integer idKey, String idKeyString, String textValue) {
		this.idKey = idKey;
		this.idKeyString = idKeyString;
		this.textValue = textValue;
	}
	
	public Integer getIdKey() {
		return idKey;
	}
	public void setIdKey(Integer idKey) {
		this.idKey = idKey;
	}
	public String getIdKeyString() {
		return idKeyString;
	}
	public void setIdKeyString(String idKeyString) {
		this.idKeyString = idKeyString;
	}
	public String getTextValue() {
		return textValue;
	}
	public void setTextValue(String textValue) {
		this.textValue = textValue;
	}

}
