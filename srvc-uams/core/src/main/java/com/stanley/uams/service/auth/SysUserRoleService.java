package com.stanley.uams.service.auth;

import com.stanley.common.spring.BaseService;
import com.stanley.uams.domain.auth.SysUserRole;
import com.stanley.uams.mapper.master.auth.SysUserRoleMapper;
import com.stanley.utils.ResultBuilderUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
/**
 * 用户角色对应管理
 * @Description
 * @date 2016-05-17
 * @since 1.0 
 * @version 1.0  
 * @author lcw
 */
@Service
public class SysUserRoleService extends BaseService<SysUserRole,SysUserRole> {
	@Resource
	public void setSysUserRoleMapper(SysUserRoleMapper sysUserRoleMapper) {
		super.setBaseMapper(sysUserRoleMapper);
	}

	/**
	 * @Description 根据用户id删除
	 * @date 2017/10/23
	 * @author 13346450@qq.com 童晟
	 * @param
	 * @return
	 */
	public String deleteByUserid(Integer userId) {
		if (null == userId) {
			return ResultBuilderUtil.resultException("2","id不能为空");
		}
		((SysUserRoleMapper)getBaseMapper()).deleteByUserid(userId);
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	/**
	 * @Description 根据多个角色插入多条
	 * @date 2017/10/23
	 * @author 13346450@qq.com 童晟
	 * @param
	 * @return
	 */
	public String insertByUserId(Integer userid,String roleIds)	{
		for (String roleId : roleIds.split(",")) {
			SysUserRole sysUserRole =new SysUserRole();
			sysUserRole.setUserId(userid);
			sysUserRole.setRoleId(Integer.parseInt(roleId));
			getBaseMapper().insert(sysUserRole);
		}
		return ResultBuilderUtil.RESULT_SUCCESS;
	}
	
	/**
	 * @Description 修改用户的角色，因为调用该方法的方法已经在事务里面，所以此处无需加@transactional
	 * @date 2017/10/23
	 * @author 13346450@qq.com 童晟
	 * @param
	 * @return
	 */
	public void updateByUserId(Integer userid,String roleIds){
		this.deleteByUserid(userid);
		this.insertByUserId(userid,roleIds);
	}
}
