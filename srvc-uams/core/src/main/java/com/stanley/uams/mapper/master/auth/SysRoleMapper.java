package com.stanley.uams.mapper.master.auth;

import com.stanley.common.domain.mybatis.Page;
import com.stanley.uams.domain.auth.SysRole;
import com.stanley.uams.domain.auth.SysRoleVO;

import java.util.List;
import java.util.Map;

/**
 * 角色表
 * @Description
 * @date 2016-04-08
 * @since 1.0 
 * @version 1.0 
 * @author 13346450@qq.com 童晟
*/
public interface SysRoleMapper {
	/**
	 * 根据主键删除一条数据
	 * @param idKey
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-08
	 */
	int deleteByPrimaryKey(Integer idKey);

	/**
	 * 根据多个主键批量删除（ 采用in() ）
	 * @param map
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-08
	 */
	void deleteBatch(Map<String, Object> map);

	/**
	 * 插入记录
	 * @param sysRole
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-08
	 */
	int insert(SysRole sysRole);

	/**
	 * 根据主键更新记录（选择性更新）
	 * @param sysRole
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-08
	 */
	int updateByPrimaryKeySelective(SysRole sysRole);

	/**
	 * 根据主键查询记录
	 * @param idKey
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-08
	 */
	SysRole selectByPrimaryKey(Integer idKey);

	/**
	 * 分页查询
	 * @param page
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-08
	 */
	List<SysRoleVO> selectPage(Page<SysRoleVO> page);

	/**
	 * 导出excel
	 * @param map
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-08
	 */
	List<SysRoleVO> toExcel(Map<String, Object> map);

	/**
	 * 根据bean属性查找多条记录
	 * @param sysRole
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-08
	 */
	List<SysRole> selectAllBySelective(SysRole sysRole);

	/**
	 * 根据bean唯一属性查找一条记录
	 * @param sysRole
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-08
	 */
	SysRole selectOneBySelective(SysRole sysRole);

	/**
	 * 修改记录时，判断角色名是否存在（除本身外）
	 * @Description
	 * @date 2017年3月3日
	 * @author 童晟  13346450@qq.com
	 * @param @param sysRole
	 * @param @return
	 * @return SysRole
	 */
	SysRole checkExistRolename(SysRole sysRole);
	
}
