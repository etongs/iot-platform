package com.stanley.uams.domain.basic;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.stanley.utils.JsonDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Module SysParms
 * @date 2016-08-09
 * @since 1.0 
 * @version 1.0 
 * @author 13346450@qq.com 童晟
 */
public class SysParms implements Serializable{

	private static final long serialVersionUID = 1L;

	/**主键(系统参数表)*/
	private Integer idKey;
	/**参数key*/
	private String parmKey;
	/**参数值*/
	private String parmValue;
	/**备注*/
	private String remarks;
	/**最后修改时间*/
	private Timestamp createDt;

	/**获取主键(系统参数表)*/
	public Integer getIdKey() {
		return idKey;
	}
	/**设置主键(系统参数表)*/
	public void setIdKey(Integer idKey) {
		this.idKey = idKey;
	}
	/**获取参数key*/
	public String getParmKey() {
		return parmKey;
	}
	/**设置参数key*/
	public void setParmKey(String parmKey) {
		this.parmKey = parmKey;
	}
	/**获取参数值*/
	public String getParmValue() {
		return parmValue;
	}
	/**设置参数值*/
	public void setParmValue(String parmValue) {
		this.parmValue = parmValue;
	}
	/**获取备注*/
	public String getRemarks() {
		return remarks;
	}
	/**设置备注*/
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**获取最后修改时间*/
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Timestamp getCreateDt() {
		return createDt;
	}
	/**设置最后修改时间*/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}
}
