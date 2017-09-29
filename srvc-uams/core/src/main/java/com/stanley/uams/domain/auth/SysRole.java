package com.stanley.uams.domain.auth;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.stanley.utils.JsonDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Module SysRole
 * @date 2016-04-08
 * @since 1.0 
 * @version 1.0 
 * @author 13346450@qq.com 童晟
 */
public class SysRole implements Serializable{

	private static final long serialVersionUID = 1L;

	/**主键(角色表)*/
	private Integer idKey;
	/**角色名*/
	private String roleName;
	/**角色标识*/
	private String roleMarker;
	/**备注*/
	private String roleRemark;
	/**创建人*/
	private Integer createId;
	/**创建时间*/
	private Timestamp createDt;

	/**获取主键(角色表)*/
	public Integer getIdKey() {
		return idKey;
	}
	/**设置主键(角色表)*/
	public void setIdKey(Integer idKey) {
		this.idKey = idKey;
	}
	/**获取角色名*/
	public String getRoleName() {
		return roleName;
	}
	/**设置角色名*/
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**获取角色标识*/
	public String getRoleMarker() {
		return roleMarker;
	}
	/**设置角色标识*/
	public void setRoleMarker(String roleMarker) {
		this.roleMarker = roleMarker;
	}
	/**获取备注*/
	public String getRoleRemark() {
		return roleRemark;
	}
	/**设置备注*/
	public void setRoleRemark(String roleRemark) {
		this.roleRemark = roleRemark;
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
