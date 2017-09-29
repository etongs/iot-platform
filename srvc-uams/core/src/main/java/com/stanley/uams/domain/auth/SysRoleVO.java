package com.stanley.uams.domain.auth;

/**
 * 角色的视图对象
 * @Description
 * @date 2016年4月5日     
 * @since 1.0   
 * @version 1.0  
 * @author 13346450@qq.com 童晟
 */
public class SysRoleVO extends SysRole {
	/** 创建人名 **/
	private String createName;
	/**专题站名称*/
	private String subjectName;

	/**获取人名*/
	public String getCreateName() {
		return createName;
	}
	/**设置人名*/
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	/**
	 * @return the subjectName
	 */
	public String getSubjectName() {
		return subjectName;
	}
	/**
	 * @param subjectName the subjectName to set
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	

}
