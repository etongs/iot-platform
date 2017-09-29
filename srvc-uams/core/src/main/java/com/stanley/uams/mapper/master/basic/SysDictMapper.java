package com.stanley.uams.mapper.master.basic;

import com.stanley.common.domain.mybatis.Page;
import com.stanley.uams.domain.basic.SysDict;
import com.stanley.uams.domain.basic.SysDictVO;

import java.util.List;
import java.util.Map;

/**
 * 数据字典表
 * @Description
 * @date 2016-04-18
 * @since 1.0 
 * @version 1.0 
 * @author 13346450@qq.com 童晟
*/
public interface SysDictMapper {
	/**
	 * 根据主键删除一条数据
	 * @param idKey
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-18
	 */
	int deleteByPrimaryKey(Integer idKey);

	/**
	 * 根据多个主键批量删除（ 采用in() ）
	 * @param map
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-18
	 */
	void deleteBatch(Map<String, Object> map);

	/**
	 * 插入记录
	 * @param sysDict
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-18
	 */
	int insert(SysDict sysDict);

	/**
	 * 根据主键更新记录（选择性更新）
	 * @param sysDict
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-18
	 */
	int updateByPrimaryKeySelective(SysDict sysDict);

	/**
	 * 根据主键查询记录
	 * @param idKey
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-18
	 */
	SysDict selectByPrimaryKey(Integer idKey);

	/**
	 * 分页查询
	 * @param page
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-18
	 */
	List<SysDict> selectPage(Page<SysDict> page);

	/**
	 * 导出excel
	 * @param map
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-18
	 */
	List<SysDict> toExcel(Map<String, Object> map);

	/**
	 * 根据bean属性查找多条记录
	 * @param sysDict
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-18
	 */
	List<SysDict> selectAllBySelective(SysDict sysDict);

	/**
	 * 根据bean唯一属性查找一条记录
	 * @param sysDict
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-18
	 */
	SysDict selectOneBySelective(SysDict sysDict);
	
	/**
	 * 查找字典名称列所有不同的值
	 * @param
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-18
	 */
	List<Map<String,Object>> selectAllByDistinct();

	/**
	 * 获取马约翰头部
	 * @param sysDict
	 * @return
	 * @author 1447638927@qq.com 周振平
	 * @date 2016年11月9日
	 */
	List<SysDictVO> selectAllBySelectiveAno(SysDict sysDict);

}
