package com.stanley.uams.domain.basic;

/**
 * 组织机构的视图对象
 * @Description
 * @date 2016-04-11
 * @since 1.0 
 * @version 1.0 
 * @author 13346450@qq.com 童晟
 */
public class SysOrganizationVO extends SysOrganization {
	/** 机构类型名 **/
	private String createName;

	/** 创建人名 **/
	private String categoryName;

	/**获取创建人名*/
	public String getCreateName() {
		return createName;
	}
	/**设置创建人名*/
	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
