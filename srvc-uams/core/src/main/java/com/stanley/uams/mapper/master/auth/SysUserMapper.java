package com.stanley.uams.mapper.master.auth;


import com.stanley.common.domain.mybatis.Page;
import com.stanley.uams.domain.auth.SysUser;
import com.stanley.uams.domain.auth.SysUserVO;

import java.util.List;
import java.util.Map;

/**
 * 用户表
 * @Description
 * @date 2016-04-11
 * @since 1.0 
 * @version 1.0 
 * @author 13346450@qq.com 童晟
*/
public interface SysUserMapper {
	/**
	 * 根据主键删除一条数据
	 * @param idKey
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-11
	 */
	int deleteByPrimaryKey(Integer idKey);

	/**
	 * 根据多个主键批量删除（ 采用in() ）
	 * @param map
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-11
	 */
	void deleteBatch(Map<String, Object> map);

	/**
	 * 插入记录
	 * @param sysUser
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-11
	 */
	int insert(SysUser sysUser);

	/**
	 * 根据主键更新记录（选择性更新）
	 * @param sysUser
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-11
	 */
	int updateByPrimaryKeySelective(SysUser sysUser);

	/**
	 * 根据主键查询记录
	 * @param idKey
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-11
	 */
	SysUser selectByPrimaryKey(Integer idKey);

	/**
	 * 分页查询
	 * @param page
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-11
	 */
	List<SysUserVO> selectPage(Page<SysUserVO> page);

	/**
	 * 导出excel
	 * @param map
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-11
	 */
	List<SysUserVO> toExcel(Map<String, Object> map);

	/**
	 * 根据bean属性查找多条记录
	 * @param sysUser
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-11
	 */
	List<SysUser> selectAllBySelective(SysUser sysUser);

	/**
	 * 根据bean唯一属性查找一条记录
	 * @param sysUser
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-11
	 */
	SysUser selectOneBySelective(SysUser sysUser);

	/**
	 * 检查除本条外是否存在相同帐号
	 * @Description
	 * @date 2017年3月16日
	 * @author 童晟  13346450@qq.com
	 * @param @param sysUser
	 * @param @return
	 * @return SysUser
	 */
	SysUser checkExistAccount(SysUser sysUser);
	
}
