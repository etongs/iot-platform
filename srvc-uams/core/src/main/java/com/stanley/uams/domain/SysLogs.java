package com.stanley.uams.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.stanley.utils.JsonDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Module SysLogs
 * @date 2016-04-13
 * @since 1.0 
 * @version 1.0 
 * @author 13346450@qq.com 童晟
 */
public class SysLogs implements Serializable{

	private static final long serialVersionUID = 1L;

	/**主键*/
	private Integer idKey;
	/**操作的菜单*/
	private String funcMenuNm;
	/**操作的动作*/
	private String funcOperNm;
	/**操作的用户id*/
	private Integer operId;
	/**操作的用户名*/
	private String operNm;
	/**操作的备注*/
	private String operRemark;
	/**操作的日期*/
	private Timestamp operDt;

	/**获取主键*/
	public Integer getIdKey() {
		return idKey;
	}
	/**设置主键*/
	public void setIdKey(Integer idKey) {
		this.idKey = idKey;
	}
	/**获取操作的菜单*/
	public String getFuncMenuNm() {
		return funcMenuNm;
	}
	/**设置操作的菜单*/
	public void setFuncMenuNm(String funcMenuNm) {
		this.funcMenuNm = funcMenuNm;
	}
	/**获取操作的动作*/
	public String getFuncOperNm() {
		return funcOperNm;
	}
	/**设置操作的动作*/
	public void setFuncOperNm(String funcOperNm) {
		this.funcOperNm = funcOperNm;
	}
	/**获取操作的用户id*/
	public Integer getOperId() {
		return operId;
	}
	/**设置操作的用户id*/
	public void setOperId(Integer operId) {
		this.operId = operId;
	}
	/**获取操作的用户名*/
	public String getOperNm() {
		return operNm;
	}
	/**设置操作的用户名*/
	public void setOperNm(String operNm) {
		this.operNm = operNm;
	}
	/**获取操作的备注*/
	public String getOperRemark() {
		return operRemark;
	}
	/**设置操作的备注*/
	public void setOperRemark(String operRemark) {
		this.operRemark = operRemark;
	}
	/**获取操作的日期*/
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Timestamp getOperDt() {
		return operDt;
	}
	/**设置操作的日期*/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setOperDt(Timestamp operDt) {
		this.operDt = operDt;
	}
}
