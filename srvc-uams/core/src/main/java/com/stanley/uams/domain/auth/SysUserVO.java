package com.stanley.uams.domain.auth;

/**
 * 用户的视图对象
 * @Description
 * @date 2016-04-11
 * @since 1.0 
 * @version 1.0 
 * @author 13346450@qq.com 童晟
 */
public class SysUserVO extends SysUser {
	/** 创建人名 **/
	private String createName;
	/** 创建组织机构名 **/
	private String orgName;
	
	/**设置创建人名*/
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	/**获取创建组织机构名*/
	public String getOrgName() {
		return orgName;
	}
	
	/**设置创建组织机构名*/
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	/**获取创建人名*/
	public String getCreateName() {
		return createName;
	}

}
