package com.stanley.uams.mapper.master.basic;

import com.stanley.common.domain.mybatis.Page;
import com.stanley.uams.domain.basic.SysOrganization;
import com.stanley.uams.domain.basic.SysOrganizationVO;

import java.util.List;
import java.util.Map;

/**
 * 组织机构表
 * @Description
 * @date 2016-04-07
 * @since 1.0 
 * @version 1.0 
 *@author 13346450@qq.com 童晟
*/
public interface SysOrganizationMapper {
	/**
	 * 根据主键删除一条数据
	 * @param idKey
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-07
	 */
	int deleteByPrimaryKey(Integer idKey);

	/**
	 * 根据多个主键批量删除（ 采用in() ）
	 * @param map
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-07
	 */
	void deleteBatch(Map<String, Object> map);

	/**
	 * 插入记录
	 * @param sysOrganization
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-07
	 */
	int insert(SysOrganization sysOrganization);

	/**
	 * 根据主键更新记录（选择性更新）
	 * @param sysOrganization
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-07
	 */
	int updateByPrimaryKeySelective(SysOrganization sysOrganization);

	/**
	 * 根据主键查询记录
	 * @param idKey
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-07
	 */
	SysOrganization selectByPrimaryKey(Integer idKey);

	/**
	 * 分页查询
	 * @param page
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-07
	 */
	List<SysOrganizationVO> selectPage(Page<SysOrganizationVO> page);
	
	/**
	 * 导出excel
	 * @param map
	 * @return
	 * @author 13346450@qq.com 童晟 
	 * @date 2016年4月14日
	 */
	List<SysOrganizationVO> toExcel(Map<String, Object> map);

	/**
	 * 根据bean属性查找多条记录
	 * @param sysOrganization
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-07
	 */
	List<SysOrganization> selectAllBySelective(SysOrganization sysOrganization);
	
	/**
	 * 根据bean唯一属性查找一条记录
	 * @param sysOrganization
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-07
	 */
	SysOrganization selectOneBySelective(SysOrganization sysOrganization);

}
