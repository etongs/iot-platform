package com.stanley.uams.mapper.master.auth;

import com.stanley.uams.domain.auth.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 菜单表
 * @Description
 * @date 2016-04-07
 * @since 1.0 
 * @version 1.0 
 *@author 13346450@qq.com 童晟
*/
public interface SysMenuMapper {
	/**
	 * 根据主键删除一条数据
	 * @param idKey
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-07
	 */
	int deleteByPrimaryKey(Integer idKey);

	/**
	 * 插入记录
	 * @param sysMenu
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-07
	 */
	int insert(SysMenu sysMenu);

	/**
	 * 根据主键更新记录（选择性更新）
	 * @param sysMenu
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-07
	 */
	int updateByPrimaryKeySelective(SysMenu sysMenu);

	/**
	 * 根据主键查询记录
	 * @param idKey
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-07
	 */
	SysMenu selectByPrimaryKey(Integer idKey);

	/**
	 * 根据父节点查询下一级子节点
	 * @param parentId
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-07
	 */
	List<SysMenu> selectChildrenByParentId(Integer parentId);

	/**
	 * 更新指定idKey的parentId值
	 * @param map
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-07
	 */
	void updateParentIdByIdKey(Map<String, Object> map);

	/**
	 * 更新本节点的last_mark、tree_level
	 * @param map
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-07
	 */
	void updateLastMarkAndLevel(Map<String, Object> map);

	/**
	 * 查询所有节点
	 * return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-07
	 */
	List<SysMenu> selectAll();
	
	/**
	 * 根据指定条件查询
	 * @Description
	 * @date 2017年4月6日
	 * @author 童晟  13346450@qq.com
	 * @param @param sysMenu
	 * @param @return
	 * @return SysMenu
	 */
	SysMenu selectOneBySelective(SysMenu sysMenu);
	
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
}
