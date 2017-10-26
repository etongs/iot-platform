package com.stanley.uams.web.auth;

import com.stanley.common.annotation.WriteLogs;
import com.stanley.common.spring.BaseController;
import com.stanley.uams.domain.auth.SysUserRole;
import com.stanley.uams.service.auth.SysUserRoleService;
import com.stanley.utils.Constants;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户角色对应管理
 * @Description
 * @date 2016-05-17
 * @since 1.0 
 * @version 1.0  
 * @author lcw
 */
@Controller
@RequestMapping("/system/userRole/*")
public class SysUserRoleController extends BaseController {

	@Resource
	private SysUserRoleService sysUserRoleService;
	/**
	 * 新增保存
	 * @param sysUserRole
	 * @author lcw
	 * @date 2016-05-17
	 */
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@RequiresPermissions("system:SysUserRole:insert")
	@WriteLogs(Constants.OPERITION_INSERT)
	public String insert(SysUserRole sysUserRole){
		return sysUserRoleService.insert(sysUserRole);
	}

	/**
	 * 删除单条
	 * @param userId
	 * @author lcw
	 * @date 2016-05-17
	 */
	@RequestMapping(value="delete/{idKey}")
	@RequiresPermissions("system:SysUserRole:delete")
	@WriteLogs(Constants.OPERITION_DELETE)
	public String delete(@PathVariable Integer userId){
		return sysUserRoleService.deleteByUserid(userId);
	}
	/**
	 * 查询所有角色
	 * return
	 * @author lcw
	 * @date 2016-05-17
	 */
	@RequestMapping(value = "listAll")
	@RequiresPermissions("system:SysUserRole:select")
	public List<SysUserRole> listAll(){
		return sysUserRoleService.selectAllBySelective(null);
	}

}
