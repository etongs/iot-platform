package com.stanley.uams.mapper.master.basic;

import com.stanley.common.domain.mybatis.Page;
import com.stanley.uams.domain.basic.SysInterface;
import com.stanley.uams.domain.basic.SysInterfaceVO;

import java.util.List;
import java.util.Map;

/**
 * 外部接口配置表
 * @Description
 * @date 2016-10-19
 * @since 1.0 
 * @version 1.0 
 * @author lcw
*/
public interface SysInterfaceMapper {
	/**
	 * 根据主键删除一条数据
	 * @param idKey
	 * @return
	 * @author lcw
	 * @date 2016-10-19
	 */
	int deleteByPrimaryKey(Integer idKey);

	/**
	 * 根据多个主键批量删除（ 采用in() ）
	 * @param map
	 * @author lcw
	 * @date 2016-10-19
	 */
	void deleteBatch(Map<String, Object> map);

	/**
	 * 插入记录
	 * @param sysInterface
	 * @return
	 * @author lcw
	 * @date 2016-10-19
	 */
	int insert(SysInterface sysInterface);

	/**
	 * 根据主键更新记录（选择性更新）
	 * @param sysInterface
	 * @return
	 * @author lcw
	 * @date 2016-10-19
	 */
	int updateByPrimaryKeySelective(SysInterface sysInterface);
	
	/**
	 * 根据多个主键批量改变状态（ 采用in() ）
	 * @param map
	 * @author lcw
	 * @date 2016-10-19
	 */
	void updateStatusBatch(Map<String, Object> map);


	/**
	 * 根据主键查询记录
	 * @param idKey
	 * @return
	 * @author lcw
	 * @date 2016-10-19
	 */
	SysInterface selectByPrimaryKey(Integer idKey);

	/**
	 * 分页查询
	 * @param page
	 * @return
	 * @author lcw
	 * @date 2016-10-19
	 */
	List<SysInterfaceVO> selectPage(Page<SysInterfaceVO> page);

	/**
	 * 导出excel
	 * @param map
	 * @return
	 * @author lcw
	 * @date 2016-10-19
	 */
	List<SysInterfaceVO> toExcel(Map<String, Object> map);

	/**
	 * 根据bean属性查找多条记录
	 * @param sysInterface
	 * @return
	 * @author lcw
	 * @date 2016-10-19
	 */
	List<SysInterface> selectAllBySelective(SysInterface sysInterface);

	/**
	 * 根据bean唯一属性查找一条记录
	 * @param sysInterface
	 * @return
	 * @author lcw
	 * @date 2016-10-19
	 */
	SysInterface selectOneBySelective(SysInterface sysInterface);

}
