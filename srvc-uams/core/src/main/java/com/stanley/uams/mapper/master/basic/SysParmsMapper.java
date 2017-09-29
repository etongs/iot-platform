package com.stanley.uams.mapper.master.basic;

import com.stanley.common.domain.mybatis.Page;
import com.stanley.uams.domain.basic.SysParms;
import com.stanley.uams.domain.basic.SysParmsVO;

import java.util.List;
import java.util.Map;

/**
 * 系统参数表
 * @Description
 * @date 2016-08-09
 * @since 1.0 
 * @version 1.0 
 * @author 13346450@qq.com 童晟
*/
public interface SysParmsMapper {
	/**
	 * 根据主键删除一条数据
	 * @param idKey
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-08-09
	 */
	int deleteByPrimaryKey(Integer idKey);

	/**
	 * 根据多个主键批量删除（ 采用in() ）
	 * @param map
	 * @author 13346450@qq.com 童晟
	 * @date 2016-08-09
	 */
	void deleteBatch(Map<String, Object> map);

	/**
	 * 插入记录
	 * @param sysParms
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-08-09
	 */
	int insert(SysParms sysParms);

	/**
	 * 根据主键更新记录（选择性更新）
	 * @param sysParms
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-08-09
	 */
	int updateByPrimaryKeySelective(SysParms sysParms);

	/**
	 * 根据主键查询记录
	 * @param idKey
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-08-09
	 */
	SysParms selectByPrimaryKey(Integer idKey);

	/**
	 * 分页查询
	 * @param page
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-08-09
	 */
	List<SysParmsVO> selectPage(Page<SysParmsVO> page);

	/**
	 * 导出excel
	 * @param map
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-08-09
	 */
	List<SysParmsVO> toExcel(Map<String, Object> map);

	/**
	 * 根据bean属性查找多条记录
	 * @param sysParms
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-08-09
	 */
	List<SysParms> selectAllBySelective(SysParms sysParms);

	/**
	 * 根据bean唯一属性查找一条记录
	 * @param sysParms
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-08-09
	 */
	SysParms selectOneBySelective(SysParms sysParms);

	/**
	 * 前端用get获取数据
	 * @param
	 * @date 2016年8月25日
	 */
	List<SysParms> selectByParmKey(String keywords, String description);
	
	
	/**
	 * 根据parmKey 查找数据
	 * @param parmKey
	 * @return
	 * @author lcw
	 * @date 2016年12月23日
	 */
	SysParms selectOneByParmKey(String parmKey);
	
	
	
}
