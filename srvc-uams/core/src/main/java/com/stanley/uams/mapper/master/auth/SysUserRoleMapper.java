package com.stanley.uams.mapper.master.auth;


import com.stanley.uams.domain.auth.SysUserRole;

import java.util.List;
import java.util.Map;

/**
 * 用户角色对应表
 * @Description
 * @date 2016-05-17
 * @since 1.0 
 * @version 1.0 
 * @author lcw
*/
public interface SysUserRoleMapper {
	/**
	 * 根据主键删除一条数据
	 * @param
	 * @return
	 * @author lcw
	 * @date 2016-05-17
	 */
	int deleteByUserid(Integer userId);

	
	/**
	 * 插入记录
	 * @param sysUserRole
	 * @return
	 * @author lcw
	 * @date 2016-05-17
	 */
	int insert(SysUserRole sysUserRole);

	
	/**
	 * 根据主键查询记录
	 * @param idKey
	 * @return
	 * @author lcw
	 * @date 2016-05-17
	 */
	SysUserRole selectByPrimaryKey(Integer idKey);
	
	/**
	 * 根据多个用户批量删除（ 采用in() ）
	 * @param map
	 * @author lcw
	 * @date 2016-05-17
	 */
	void deleteBatch(Map<String, Object> map);

	/**
	 * 根据bean属性查找多条记录
	 * @param sysUserRole
	 * @return
	 * @author lcw
	 * @date 2016-05-17
	 */
	List<SysUserRole> selectAllBySelective(SysUserRole sysUserRole);

	/**
	 * 根据bean唯一属性查找一条记录
	 * @param sysUserRole
	 * @return
	 * @author lcw
	 * @date 2016-05-17
	 */
	SysUserRole selectOneBySelective(SysUserRole sysUserRole);

}
