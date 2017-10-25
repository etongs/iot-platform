package com.stanley.uams.mapper.master.auth;

import com.stanley.common.spring.BaseMapper;
import com.stanley.uams.domain.auth.SysUserRole;
/**
 * 用户角色对应表
 * @Description
 * @date 2016-05-17
 * @since 1.0 
 * @version 1.0 
 * @author lcw
*/
public interface SysUserRoleMapper extends BaseMapper<SysUserRole,SysUserRole> {
	/**
	 * 根据userid删除一条数据
	 * @param
	 * @return
	 * @author lcw
	 * @date 2016-05-17
	 */
	int deleteByUserid(Integer userId);

}
