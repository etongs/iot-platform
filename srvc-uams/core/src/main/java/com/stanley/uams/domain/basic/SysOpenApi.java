package com.stanley.uams.domain.basic;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.stanley.utils.JsonDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Module SysOpenApi
 * @date 2017-01-06
 * @since 1.0 
 * @version 1.0 
 * @author 13346450@qq.com 童晟
 */
public class SysOpenApi implements Serializable{

	private static final long serialVersionUID = 1L;

	/**主键*/
	private Integer idKey;
	/**接入的用户名称*/
	private String userName;
	/**接入的用户唯一标识*/
	private String userCode;
	/**接入的用户私钥*/
	private String privateKey;
	/**最近一次提交的时间戳*/
	private Long timestampVal;
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
	/**获取接入的用户名称*/
	public String getUserName() {
		return userName;
	}
	/**设置接入的用户名称*/
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**获取接入的用户唯一标识*/
	public String getUserCode() {
		return userCode;
	}
	/**设置接入的用户唯一标识*/
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	/**获取接入的用户私钥*/
	public String getPrivateKey() {
		return privateKey;
	}
	/**设置接入的用户私钥*/
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	public Long getTimestampVal() {
		return timestampVal;
	}
	public void setTimestampVal(Long timestampVal) {
		this.timestampVal = timestampVal;
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
