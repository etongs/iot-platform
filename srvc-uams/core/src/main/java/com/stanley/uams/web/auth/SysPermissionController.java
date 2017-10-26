package com.stanley.uams.web.auth;

import com.stanley.common.annotation.WriteLogs;
import com.stanley.common.domain.SearchParam;
import com.stanley.common.spring.BaseController;
import com.stanley.uams.service.auth.SysPermissionService;
import com.stanley.utils.Constants;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 用户角色权限对应管理
 * @Description
 * @date 2016-05-17
 * @since 1.0 
 * @version 1.0  
 * @author lcw
 */
@RestController
@RequestMapping("/system/permission/*")
public class SysPermissionController extends BaseController {

	@Resource
	private SysPermissionService sysPermissionService;
	
	/**
	 * 根据用户角色id保存
	 * @Description
	 * @date 2017年4月17日
	 * @author 童晟  13346450@qq.com
	 * @param @param response
	 * @param @param searchParam
	 * @return void
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@RequiresPermissions("system:SysPermission:update")
	@WriteLogs(Constants.OPERITION_UPDATE)
	public String save(SearchParam searchParam){
		return sysPermissionService.update(searchParam.getSearchId(),searchParam.getCheckedIds());
	}
	
	/**
	 * 查询所有资源节点和权限
	 * @Description
	 * @date 2017年4月14日
	 * @author 童晟  13346450@qq.com
	 * @param @param response
	 * @param @param roleId
	 * @return void
	 */
	@RequestMapping(value = "queryAll/{roleId}")
	@RequiresPermissions("system:SysPermission:select")
	public List<Map<String, Object>> queryAll(@PathVariable Integer roleId){
		return sysPermissionService.queryAll(roleId,1);
	}
	

}
