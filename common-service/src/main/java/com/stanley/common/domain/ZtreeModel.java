package com.stanley.common.domain;

import java.io.Serializable;

/**
 * zTree的节点模型
 * @ClassName: zTree
 * @Description
 * @date 2017年3月28日
 * @since 1.0 
 * @version 1.0  
 * @author 童晟  13346450@qq.com
 */
public class ZtreeModel implements Serializable{
	private static final long serialVersionUID = -443797747805868886L;
	
	private Integer id;
	private String name;
	private Boolean isParent;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}
	
	
}
