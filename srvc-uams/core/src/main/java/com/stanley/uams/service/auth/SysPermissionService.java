package com.stanley.uams.service.auth;


import com.stanley.common.spring.BaseService;
import com.stanley.uams.mapper.master.auth.SysPermissionMapper;
import com.stanley.uams.service.ShiroService;
import com.stanley.utils.ResultBuilderUtil;
import com.stanley.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;

/**
 * 用户角色对应管理
 * @Description
 * @date 2016-05-17
 * @since 1.0 
 * @version 1.0  
 * @author lcw
 */
@Service
public class SysPermissionService extends BaseService{

	@Resource
	private SysPermissionMapper sysPermissionMapper;
	@Resource
	private ShiroService shiroService;

	@Transactional
	public String update(Integer roleId,String dataIds) {
		sysPermissionMapper.deleteByRoleId(roleId);
		if(!StringUtils.isNull(dataIds))
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("roleId", roleId);
			map.put("createId", getUserId());
			map.put("createDt", new Timestamp(System.currentTimeMillis()));
			map.put("dataListID", Arrays.asList(dataIds.split(",")));
			sysPermissionMapper.insert(map);
		}
		shiroService.clearUserAuthByUserId(roleId);
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	public List<Map<String,Object>> selectResourcesByRoleId(Integer roleId) {
		return sysPermissionMapper.selectResourcesByRoleId(roleId);
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

}
