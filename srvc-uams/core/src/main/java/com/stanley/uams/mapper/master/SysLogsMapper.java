package com.stanley.uams.mapper.master;

import com.stanley.common.domain.mybatis.Page;
import com.stanley.uams.domain.SysLogs;
import com.stanley.uams.domain.SysLogsVO;

import java.util.List;
import java.util.Map;

/**
 * 系统日志表
 * @Description
 * @date 2016-04-13
 * @since 1.0 
 * @version 1.0 
 * @author 13346450@qq.com 童晟
*/
public interface SysLogsMapper {
	/**
	 * 根据主键删除一条数据
	 * @param idKey
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-13
	 */
	int deleteByPrimaryKey(Integer idKey);

	/**
	 * 根据多个主键批量删除（ 采用in() ）
	 * @param map
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-13
	 */
	void deleteBatch(Map<String, Object> map);

	/**
	 * 插入记录
	 * @param sysLogs
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-13
	 */
	int insert(SysLogs sysLogs);

	/**
	 * 根据主键更新记录（选择性更新）
	 * @param sysLogs
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-13
	 */
	int updateByPrimaryKeySelective(SysLogs sysLogs);

	/**
	 * 根据主键查询记录
	 * @param idKey
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-13
	 */
	SysLogs selectByPrimaryKey(Integer idKey);

	/**
	 * 分页查询
	 * @param page
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-13
	 */
	List<SysLogsVO> selectPage(Page<SysLogsVO> page);

	/**
	 * 导出excel
	 * @param map
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-13
	 */
	List<SysLogsVO> toExcel(Map<String, Object> map);

	/**
	 * 根据bean属性查找多条记录
	 * @param sysLogs
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-13
	 */
	List<SysLogs> selectAllBySelective(SysLogs sysLogs);

	/**
	 * 根据bean唯一属性查找一条记录
	 * @param sysLogs
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-13
	 */
	SysLogs selectOneBySelective(SysLogs sysLogs);

	/**
	 * 查询所有有的不同的操作
	 * @param
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-13
	 */
	List<SysLogs> selectSyslogOperation();

}
