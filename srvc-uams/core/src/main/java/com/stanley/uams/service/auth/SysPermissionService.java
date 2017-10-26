package com.stanley.uams.service.auth;


import com.stanley.uams.domain.auth.SysResource;
import com.stanley.uams.domain.auth.SysUserRole;
import com.stanley.uams.mapper.master.auth.SysPermissionMapper;
import com.stanley.uams.service.CommonService;
import com.stanley.utils.ResultBuilderUtil;
import com.stanley.utils.ShiroSessionUtil;
import com.stanley.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 授权管理，该功能比较特殊，没有domain，也不继承Base
 * @Description
 * @date 2017-10-23
 * @since 1.0 
 * @version 1.0  
 * @author ts
 */
@Service
public class SysPermissionService {
	@Resource
	private SysPermissionMapper sysPermissionMapper;
	@Resource
	private SysUserRoleService sysUserRoleService;
	@Resource
	private CommonService commonService;

	@Transactional
	public String update(Integer roleId,String dataIds) {
		sysPermissionMapper.deleteByRoleId(roleId);
		if(!StringUtils.isNull(dataIds))
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("roleId", roleId);
			map.put("createId", ShiroSessionUtil.getUserId());
			map.put("createDt", new Timestamp(System.currentTimeMillis()));
			map.put("dataListID", Arrays.asList(dataIds.split(",")));
			sysPermissionMapper.insert(map);
		}
		//更新权限缓存
		this.clearUserAuthByUserId(roleId);
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	public List<SysResource> selectResourcesByRoleId(Integer roleId) {
		return sysPermissionMapper.selectResourcesByRole(roleId);
	}

	public List<Map<String, Object>> queryAll(Integer roleId, Integer parentId) {
		Map<String, Integer> parms = new HashMap<String, Integer>();
		parms.put("roleId", roleId);
		parms.put("parentId", parentId);
		List<Map<String, Object>> dataList = sysPermissionMapper.selectAll(parms);
		List<Map<String, Object>> finalList = new ArrayList<Map<String, Object>>();
		if(dataList.isEmpty())
			return finalList;
		
		dataList.forEach(r -> {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", (int)r.get("id_key"));
			map.put("pId", (int)r.get("parent_id"));
			map.put("name", String.valueOf(r.get("cd_nm")));
			map.put("open", !(Boolean)r.get("last_mark"));
			map.put("checked", null!=r.get("role_id"));
			finalList.add(map);
			List<Map<String, Object>> children = this.queryAll(roleId, (int)r.get("id_key"));
			if(!children.isEmpty())
				children.forEach(c -> finalList.add(c));
		});
		return finalList;
	}


	/**
	 * @Description 权限修改后，清除角色对应的用户权限信息缓存redis
	 * @date 2017/9/27
	 * @author 13346450@qq.com 童晟
	 * @param
	 * @return
	 */
	private void clearUserAuthByUserId(Integer roleId) {
		SysUserRole sysUserRole = new SysUserRole();
		sysUserRole.setRoleId(roleId);
		List<SysUserRole> userList = sysUserRoleService.selectAllBySelective(sysUserRole);
		if(userList.isEmpty())
			return;
		List<Integer> userIds = userList.stream().flatMap(userRole-> Stream.of(userRole.getUserId())).collect(Collectors.toList());
		commonService.clearShiroCachedAuthorizationInfo(userIds);
	}

}
