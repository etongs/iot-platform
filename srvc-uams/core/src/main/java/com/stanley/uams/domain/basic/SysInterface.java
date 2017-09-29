package com.stanley.uams.domain.basic;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.stanley.utils.JsonDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Module SysInterface
 * @date 2016-10-19
 * @since 1.0 
 * @version 1.0 
 * @author lcw
 */
public class SysInterface implements Serializable{

	private static final long serialVersionUID = 1L;

	/**主键*/
	private Integer idKey;
	/**接口名称*/
	private String infName;
	/**接口地址*/
	private String infUrl;
	/**接口参数(json格式)*/
	private String infParms;
	/**加密公式*/
	private String encryptExpression;
	/**加密算法字符集*/
	private String encryptCharset;
	/**密码的key值*/
	private String passwdKey;
	/**http请求方式(get,post)*/
	private String httpMethod;
	/**接收到的数据格式(json,xml)*/
	private String receivedDataFormat;
	/**收到数据后的实现类bean名称*/
	private String implBeanName;
	/**计划任务状态(1-启动，0-停止)*/
	private Boolean scheduledStatus;
	/**上次执行时间*/
	private Timestamp lastExeTime;
	/**创建用户id*/
	private Integer createId;
	/**创建日期*/
	private Timestamp createDt;
	/**排序码*/
	private Integer orderCd;

	/**获取主键*/
	public Integer getIdKey() {
		return idKey;
	}
	/**设置主键*/
	public void setIdKey(Integer idKey) {
		this.idKey = idKey;
	}
	/**获取接口名称*/
	public String getInfName() {
		return infName;
	}
	/**设置接口名称*/
	public void setInfName(String infName) {
		this.infName = infName;
	}
	/**获取接口地址*/
	public String getInfUrl() {
		return infUrl;
	}
	/**设置接口地址*/
	public void setInfUrl(String infUrl) {
		this.infUrl = infUrl;
	}
	/**获取接口参数(json格式)*/
	public String getInfParms() {
		return infParms;
	}
	/**设置接口参数(json格式)*/
	public void setInfParms(String infParms) {
		this.infParms = infParms;
	}
	/**获取加密公式*/
	public String getEncryptExpression() {
		return encryptExpression;
	}
	/**设置加密公式*/
	public void setEncryptExpression(String encryptExpression) {
		this.encryptExpression = encryptExpression;
	}
	/**获取加密算法字符集*/
	public String getEncryptCharset() {
		return encryptCharset;
	}
	/**设置加密算法字符集*/
	public void setEncryptCharset(String encryptCharset) {
		this.encryptCharset = encryptCharset;
	}
	/**获取密码的key值*/
	public String getPasswdKey() {
		return passwdKey;
	}
	/**设置密码的key值*/
	public void setPasswdKey(String passwdKey) {
		this.passwdKey = passwdKey;
	}
	/**获取http请求方式(get,post)*/
	public String getHttpMethod() {
		return httpMethod;
	}
	/**设置http请求方式(get,post)*/
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
	/**获取接收到的数据格式(json,xml)*/
	public String getReceivedDataFormat() {
		return receivedDataFormat;
	}
	/**设置接收到的数据格式(json,xml)*/
	public void setReceivedDataFormat(String receivedDataFormat) {
		this.receivedDataFormat = receivedDataFormat;
	}
	/**获取收到数据后的实现类bean名称*/
	public String getImplBeanName() {
		return implBeanName;
	}
	/**设置收到数据后的实现类bean名称*/
	public void setImplBeanName(String implBeanName) {
		this.implBeanName = implBeanName;
	}
	/**获取计划任务状态(1-启动，0-停止)*/
	public Boolean getScheduledStatus() {
		return scheduledStatus;
	}
	/**设置计划任务状态(1-启动，0-停止)*/
	public void setScheduledStatus(Boolean scheduledStatus) {
		this.scheduledStatus = scheduledStatus;
	}
	/**获取上次执行时间*/
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Timestamp getLastExeTime() {
		return lastExeTime;
	}
	/**设置上次执行时间*/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setLastExeTime(Timestamp lastExeTime) {
		this.lastExeTime = lastExeTime;
	}
	/**获取创建用户id*/
	public Integer getCreateId() {
		return createId;
	}
	/**设置创建用户id*/
	public void setCreateId(Integer createId) {
		this.createId = createId;
	}
	/**获取创建日期*/
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Timestamp getCreateDt() {
		return createDt;
	}
	/**设置创建日期*/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}
	/**获取排序码*/
	public Integer getOrderCd() {
		return orderCd;
	}
	/**设置排序码*/
	public void setOrderCd(Integer orderCd) {
		this.orderCd = orderCd;
	}
}
