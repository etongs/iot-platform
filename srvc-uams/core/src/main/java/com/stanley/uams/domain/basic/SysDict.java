package com.stanley.uams.domain.basic;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.stanley.utils.JsonDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Module SysDict
 * @date 2016-04-18
 * @since 1.0 
 * @version 1.0 
 * @author 13346450@qq.com 童晟
 */
public class SysDict implements Serializable{

	private static final long serialVersionUID = 1L;

	/**主键*/
	private Integer idKey;
	/**字典id*/
	private String dictTypeId;
	/**字典名称*/
	private String dictTypeNm;
	/**字典值*/
	private String dictValue;
	/**备注*/
	private String remark;
	/**创建人*/
	private Integer createId;
	/**创建时间*/
	private Timestamp createDt;

	/**获取主键*/
	public Integer getIdKey() {
		return idKey;
	}
	/**设置主键*/
	public void setIdKey(Integer idKey) {
		this.idKey = idKey;
	}
	/**获取字典id*/
	public String getDictTypeId() {
		return dictTypeId;
	}
	/**设置字典id*/
	public void setDictTypeId(String dictTypeId) {
		this.dictTypeId = dictTypeId;
	}
	/**获取字典名称*/
	public String getDictTypeNm() {
		return dictTypeNm;
	}
	/**设置字典名称*/
	public void setDictTypeNm(String dictTypeNm) {
		this.dictTypeNm = dictTypeNm;
	}
	/**获取字典值*/
	public String getDictValue() {
		return dictValue;
	}
	/**设置字典值*/
	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}
	/**获取备注*/
	public String getRemark() {
		return remark;
	}
	/**设置备注*/
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**获取创建人*/
	public Integer getCreateId() {
		return createId;
	}
	/**设置创建人*/
	public void setCreateId(Integer createId) {
		this.createId = createId;
	}
	/**获取创建时间*/
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Timestamp getCreateDt() {
		return createDt;
	}
	/**设置创建时间*/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}
}
