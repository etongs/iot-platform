package com.stanley.uams.mapper.master.auth;

import com.stanley.uams.domain.auth.SysResource;

import java.util.List;
import java.util.Map;
/**
 * 用户角色对应表 ，此功能比较特殊，没有domain，所以未继承BaseMapper
 * @Description
 * @date 2016-05-17
 * @since 1.0 
 * @version 1.0 
 * @author lcw
*/
public interface SysPermissionMapper {
	
	/**
	 * 根据用户角色id删除记录
	 * @param roleId
	 * @return
	 * @author lcw
	 * @date 2016-07-14
	 */
	int deleteByRoleId(Integer roleId);
	
	
	/**
	 * 根据用户角色id和资源id批量插入数据
	 * @param
	 * @return
	 * @author lcw
	 * @date 2016-07-14
	 */
	void insert(Map<String, Object> map);

	/**
	 * 根据角色id查权限的表达式
	 * @param roleId
	 * @return
	 * @author 13346450@qq.com 童晟 
	 * @date 2016年7月20日
	 */
	List<SysResource> selectResourcesByRole(Integer roleId);
	
	/**
	 * 查询所有资源节点和权限
	 * @Description
	 * @date 2017年4月14日
	 * @author 童晟  13346450@qq.com
	 * @param @param roleId
	 * @param @return
	 * @return List<Map<String,Integer>>
	 */
	List<Map<String, Object>> selectAll(Map<String, Integer> map);
	
}
