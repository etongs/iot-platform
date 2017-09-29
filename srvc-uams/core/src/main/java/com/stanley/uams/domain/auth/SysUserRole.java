package com.stanley.uams.domain.auth;

import java.io.Serializable;

/**
 * @Module SysUserRole
 * @date 2016-05-17
 * @since 1.0 
 * @version 1.0 
 * @author lcw
 */
public class SysUserRole implements Serializable{

	private static final long serialVersionUID = 1L;

	/**用户id*/
	private Integer userId;
	/**角色id*/
	private Integer roleId;

	/**获取用户id*/
	public Integer getUserId() {
		return userId;
	}
	/**设置用户id*/
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**获取角色id*/
	public Integer getRoleId() {
		return roleId;
	}
	/**设置角色id*/
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
}
