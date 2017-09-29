package com.stanley.common.domain;

import java.util.Date;
import java.util.List;

/**
 *说明：
 *@创建：作者:Administrator		创建时间：Apr 29, 2009
 *@修改历史：
 *		[序号](Administrator	Apr 29, 2009)<修改说明>
 */
public class UserInfoBean implements java.io.Serializable {
	
	private static final long serialVersionUID = 840423237564713299L;
	
	private Integer userId;		//用户idkey
	private String account;	    //帐号
	private String nameCn;	    //姓名
	private Integer   orgId;	//机构id
	private String orgName;	    //机构名称
	private String roleIds;	//角色名(多个)
	private String roleName;	//角色名(多个)
	private Boolean isModify;   //是否首次登录已修改密码
	private Date   loginDate;   //登录日期
	private Integer provinceId; //当前用户的组织机构所在省id
	private Integer cityId;     //当前用户的组织机构所在市id
	private Integer districtId; //当前用户的组织机构所在区id
	private  Boolean rememberme;//记住登录状态
	
	private List<String> permissionList; //权限集合
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getNameCn() {
		return nameCn;
	}
	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public Boolean getIsModify() {
		return isModify;
	}
	public void setIsModify(Boolean isModify) {
		this.isModify = isModify;
	}
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}
	public List<String> getPermissionList() {
		return permissionList;
	}
	public void setPermissionList(List<String> permissionList) {
		this.permissionList = permissionList;
	}
	public Boolean getRememberme() {
		return rememberme;
	}
	public void setRememberme(Boolean rememberme) {
		this.rememberme = rememberme;
	}
	
}
