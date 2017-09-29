package com.stanley.uams.service.auth;

import com.stanley.common.domain.SearchParam;
import com.stanley.common.spring.BaseService;
import com.stanley.uams.domain.auth.SysUserRole;
import com.stanley.uams.mapper.master.auth.SysUserRoleMapper;
import com.stanley.utils.ResultBuilderUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户角色对应管理
 * @Description
 * @date 2016-05-17
 * @since 1.0 
 * @version 1.0  
 * @author lcw
 */
@Service
public class SysUserRoleService extends BaseService {
	private final static String FUNC_MENU ="用户角色对应表";

	@Resource
	private SysUserRoleMapper sysUserRoleMapper;

	@Transactional
	public String insert(SysUserRole sysUserRole) {
		sysUserRoleMapper.insert(sysUserRole);
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_CREATE, "idKey:" +sysUserRole.getUserId());
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	public String deleteByUserid(Integer userId) {
		if (null == userId) {
			return ResultBuilderUtil.resultException("2","id不能为空");
		}
		sysUserRoleMapper.deleteByUserid(userId);
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_DELETE, "idKey:" + userId);
		return ResultBuilderUtil.RESULT_SUCCESS;
	}
	
	public String deleteBatch(String dataIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataListID", Arrays.asList(dataIds.split(",")));
		sysUserRoleMapper.deleteBatch(map);
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_DELETE, "ids:" + dataIds);
		return ResultBuilderUtil.RESULT_SUCCESS;
	}
	
	public SysUserRole selectByIdkey(Integer idKey) {
		return sysUserRoleMapper.selectByPrimaryKey(idKey);
	}

	public SysUserRole selectOneBySelective(SearchParam searchParam) {
		SysUserRole sysUserRole = new SysUserRole();
		return sysUserRoleMapper.selectOneBySelective(sysUserRole);
	}

	public List<SysUserRole> selectAllBySelective(SysUserRole sysUserRole) {
		return sysUserRoleMapper.selectAllBySelective(sysUserRole);
	}
	
	public String insertByUserId(Integer userid,String roleIds)
	{
		for (String roleId : roleIds.split(",")) {
			SysUserRole sysUserRole =new SysUserRole();
			sysUserRole.setUserId(userid);
			sysUserRole.setRoleId(Integer.parseInt(roleId));
			sysUserRoleMapper.insert(sysUserRole);
		}
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_CREATE, "userid:" +userid);
		return ResultBuilderUtil.RESULT_SUCCESS;
	}
	
	public void updateByUserId(Integer userid,String roleIds)
	{
		this.deleteByUserid(userid);
		this.insertByUserId(userid,roleIds);
	}
}
