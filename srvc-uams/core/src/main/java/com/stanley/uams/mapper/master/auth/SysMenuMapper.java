package com.stanley.uams.mapper.master.auth;

import com.stanley.common.spring.BaseTreeMapper;
import com.stanley.uams.domain.auth.SysMenu;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 菜单表
 * @Description
 * @date 2016-04-07
 * @since 1.0 
 * @version 1.0 
 *@author 13346450@qq.com 童晟
*/
public interface SysMenuMapper extends BaseTreeMapper<SysMenu> {
	/**
	 * 检查是否存在相同的菜单标识
	 * @Description
	 * @date 2017年4月6日
	 * @author 童晟  13346450@qq.com
	 * @param @param sysMenu
	 * @param @return
	 * @return SysMenu
	 */
	SysMenu checkExistMarker(SysMenu sysMenu);

	/**
	 * @Description 查询有权限的菜单树的子节点
	 * @date 2017/8/31
	 * @author 13346450@qq.com 童晟
	 * @param
	 * @return
	 */
	List<SysMenu> getPermissionTreeMenus(@Param("parentId") Integer parentId,@Param("roleId") Integer roleId);

	/**
	 * @Description 根据条件查询一条
	 * @date 2017/10/24
	 * @author 13346450@qq.com 童晟
	 * @param
	 * @return
	 */
	SysMenu selectOneBySelective(SysMenu sysMenu);
}
