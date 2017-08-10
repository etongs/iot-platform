package com.stanley.uams.api.dto;

/*import com.eacha.common.JsonDateSerializer;
import com.eacha.common.JsonDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;*/

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @Module SysUserDto
 * @date 2016-04-11
 * @since 1.0 
 * @version 1.0 
 * @author 13346450@qq.com 童晟
 */
public class SysUserDto implements Serializable{

	private static final long serialVersionUID = 1L;

	/**主键(用户表)*/
	private Integer idKey;
	/**所属组织机构(学校等)*/
	private Integer orgId;
	/**所属角色的id数组(备用)*/
	private String roleIds;
	/**用户帐号(登录用)*/
	private String account;
	/**用户姓名*/
	private String nameCn;
	/**密码*/
	private String passwd;
	/**昵称*/
	private String nickname;
	/**是否锁定(1-是 0-否)*/
	private Boolean isLocked;
	/**是否有修改密码*/
	private Boolean isModify;
	/**性别(男、女)*/
	private String gender;
	/**邮箱*/
	private String email;
	/**生日*/
	private Date birthday;
	/**手机号*/
	private String mobile;
	/**创建人*/
	private Integer createId;
	/**创建时间*/
	private Timestamp createDt;
	/**最近一次登录的时间戳*/
	private Long lastLoginTimestamp;
	/**登录失败次数*/
	private Integer failedLoginTimes;
	
	/**获取主键(用户表)*/
	public Integer getIdKey() {
		return idKey;
	}
	/**设置主键(用户表)*/
	public void setIdKey(Integer idKey) {
		this.idKey = idKey;
	}
	/**获取所属组织机构(学校等)*/
	public Integer getOrgId() {
		return orgId;
	}
	/**设置所属组织机构(学校等)*/
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	/**获取所属角色的id数组(备用)*/
	public String getRoleIds() {
		return roleIds;
	}
	/**设置所属角色的id数组(备用)*/
	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
	/**获取用户帐号(登录用)*/
	public String getAccount() {
		return account;
	}
	/**设置用户帐号(登录用)*/
	public void setAccount(String account) {
		this.account = account;
	}
	/**获取用户姓名*/
	public String getNameCn() {
		return nameCn;
	}
	/**设置用户姓名*/
	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}
	/**获取密码*/
	public String getPasswd() {
		return passwd;
	}
	/**设置密码*/
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	/**获取昵称*/
	public String getNickname() {
		return nickname;
	}
	/**设置昵称*/
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**获取是否锁定(1-是 0-否)*/
	public Boolean getIsLocked() {
		return isLocked;
	}
	/**设置是否锁定(1-是 0-否)*/
	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}
	/**获取是否有修改密码*/
	public Boolean getIsModify() {
		return isModify;
	}
	/**设置是否有修改密码*/
	public void setIsModify(Boolean isModify) {
		this.isModify = isModify;
	}
	/**获取性别(男、女)*/
	public String getGender() {
		return gender;
	}
	/**设置性别(男、女)*/
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**获取邮箱*/
	public String getEmail() {
		return email;
	}
	/**设置邮箱*/
	public void setEmail(String email) {
		this.email = email;
	}
	/**获取生日*/
	//@JsonSerialize(using = JsonDateSerializer.class)
	public Date getBirthday() {
		return birthday;
	}
	/**设置生日*/
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	/**获取手机号*/
	public String getMobile() {
		return mobile;
	}
	/**设置手机号*/
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	//@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Timestamp getCreateDt() {
		return createDt;
	}
	/**设置创建时间*/
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}
	
	public Long getLastLoginTimestamp() {
		return lastLoginTimestamp;
	}
	public void setLastLoginTimestamp(Long lastLoginTimestamp) {
		this.lastLoginTimestamp = lastLoginTimestamp;
	}
	public Integer getFailedLoginTimes() {
		return failedLoginTimes;
	}
	public void setFailedLoginTimes(Integer failedLoginTimes) {
		this.failedLoginTimes = failedLoginTimes;
	}
	
}
