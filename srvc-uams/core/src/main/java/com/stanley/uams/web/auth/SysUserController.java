package com.stanley.uams.web.auth;

import com.stanley.common.annotation.WriteLogs;
import com.stanley.common.domain.SearchParam;
import com.stanley.common.domain.mybatis.Page;
import com.stanley.common.spring.BaseController;
import com.stanley.uams.domain.auth.SysUser;
import com.stanley.uams.domain.auth.SysUserOnline;
import com.stanley.uams.domain.auth.SysUserVO;
import com.stanley.uams.service.auth.SysUserService;
import com.stanley.utils.Constants;
import com.stanley.utils.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户管理
 * @Description
 * @date 2016-04-11
 * @since 1.0 
 * @version 1.0  
 * @author 13346450@qq.com 童晟
 */
@RestController
@RequestMapping("/system/user/*")
public class SysUserController extends BaseController {

	@Resource
	private SysUserService sysUserService;

	/**
	 * 新增保存
	 * @param sysUser
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-11
	 */
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@RequiresPermissions("system:SysUser:insert")
	@WriteLogs(Constants.OPERITION_INSERT)
	public String insert(SysUser sysUser){
		return sysUserService.insert(sysUser);
	}

	/**
	 * 删除单条
	 * @param idKey
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-11
	 */
	@RequestMapping(value="delete/{idKey}")
	@RequiresPermissions("system:SysUser:delete")
	@WriteLogs(Constants.OPERITION_DELETE)
	public String delete(@PathVariable Integer idKey){
		return sysUserService.delete(idKey);
	}

	/**
	 * 批量删除
	 * @param searchParam
	* @author 13346450@qq.com 童晟
	 * @date 2016-04-11
	 */
	@RequestMapping(value="deleteBatch", method = RequestMethod.POST)
	@RequiresPermissions("system:SysUser:delete")
	@WriteLogs(Constants.OPERITION_DELETE_BATCH)
	public String deleteBatch(SearchParam searchParam){
		return sysUserService.deleteBatch(searchParam.getCheckedIds());
	}

	/**
	 * 修改保存
	 * @param sysUser
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-11
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@RequiresPermissions("system:SysUser:update")
	@WriteLogs(Constants.OPERITION_UPDATE)
	public String update(SysUser sysUser){
		return sysUserService.update(sysUser);
	}

	/**
	 * 分页查询
	 * @param searchParam
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-11
	 */
	@RequestMapping(value = "listPage")
	@RequiresPermissions("system:SysUser:select")
	public Page<SysUserVO> listPage(SearchParam searchParam){
		return sysUserService.selectPage(searchParam);
	}

	/**
	 * 导出excel
	 * @param response
	 * @param searchParam
	 * return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-11
	 */
	@RequestMapping(value = "toExcel")
	@RequiresPermissions("system:SysUser:select")
	public void toExcel(HttpServletResponse response, SearchParam searchParam){
		ExcelUtil.outputExcel(response, "用户数据", sysUserService.toExcel(searchParam));
	}

	/**
	 * 修改自己的密码，不需要指定权限，能登录就可以改自己的密码
	 * @param userPwd
	 * @param surePwd
	 * @author 13346450@qq.com 童晟 
	 * @date 2016年4月11日
	 */
	@RequestMapping(value = "modifyPwd", method = RequestMethod.POST)
	@WriteLogs("修改自己密码")
	public String modifyPwd(String userPwd, String surePwd) {
		return sysUserService.modifyPwd(userPwd,surePwd);
	}
	
	/**
	 * 初始化密码
	 * @author 13346450@qq.com 童晟
	 * @date 2016年4月11日
	 */
	@RequestMapping(value = "initializePwd")
	@RequiresPermissions("system:SysUser:initializePwd")
	@WriteLogs("初始化密码")
	public String initializePwd(SearchParam searchParam){
		return sysUserService.initializePwd(searchParam.getCheckedIds());
	}
	
	/**
	 * 检查用户名是否已存在
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-08
	 */
	@RequestMapping(value = "validate", method = RequestMethod.GET)
	@RequiresPermissions("system:SysUser:select")
	public String validate(SysUser sysUser){
		return sysUserService.validate(sysUser);
	}
	
	/**
	 * 验证旧密码是否正确
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-08
	 */
	@RequestMapping(value = "validOldPasswd", method = RequestMethod.GET)
	public String validOldPasswd(String userPwd){
		return sysUserService.validOldPasswd(userPwd);
	}
	
	/**
	 * 查询自己的信息，不需特别权限
	 * @Description
	 * @date 2017年5月8日
	 * @author 童晟  13346450@qq.com
	 * @param @param response
	 * @param @param userPwd
	 * @return void
	 */
	@RequestMapping(value = "queryMyself", method = RequestMethod.GET)
	public SysUser queryMyself(String userPwd){
		return sysUserService.queryMyself();
	}
	
	/**
	 * 修改自己的个人资料，无需特别权限
	 * @Description
	 * @date 2017年5月9日
	 * @author 童晟  13346450@qq.com
	 * @param @param response
	 * @param @param sysUser
	 * @return void
	 */
	@RequestMapping(value = "modifyMyself", method = RequestMethod.POST)
	@WriteLogs(Constants.OPERITION_UPDATE)
	public String modifyMyself(SysUser sysUser){
		return sysUserService.update(sysUser);
	}


	/**
	 * 列出在线用户
	 * @param searchParam
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-11
	 */
	@RequestMapping(value = "listOnline")
	@RequiresPermissions("system:OnlineUser:select")
	public Page<SysUserOnline> listOnline(SearchParam searchParam){
		return sysUserService.selectOnline(searchParam);
	}

	/**
	 * @Description 强制下线，必须是系统管理员角色才行
	 * @date 2017/10/13
	 * @author 13346450@qq.com 童晟
	 * @param sessionId
	 * @return java.lang.String
	 */
	@RequestMapping(value = "offline/{sessionId}", method = RequestMethod.GET)
	@RequiresPermissions("system:OnlineUser:offline")
	@RequiresRoles("系统管理员")
	@WriteLogs("强制下线")
	public String offline(@PathVariable String sessionId){
		return sysUserService.offline(sessionId);
	}

}
