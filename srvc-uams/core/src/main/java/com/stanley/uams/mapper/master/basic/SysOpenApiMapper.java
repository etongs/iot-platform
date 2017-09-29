package com.stanley.uams.mapper.master.basic;

import com.stanley.common.domain.mybatis.Page;
import com.stanley.uams.domain.basic.SysOpenApi;
import com.stanley.uams.domain.basic.SysOpenApiVO;

import java.util.List;
import java.util.Map;

/**
 * 开放接口接入表
 * @Description
 * @date 2017-01-06
 * @since 1.0 
 * @version 1.0 
 * @author 13346450@qq.com 童晟
*/
public interface SysOpenApiMapper {
	/**
	 * 根据主键删除一条数据
	 * @param idKey
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2017-01-06
	 */
	int deleteByPrimaryKey(Integer idKey);

	/**
	 * 根据多个主键批量删除（ 采用in() ）
	 * @param map
	 * @author 13346450@qq.com 童晟
	 * @date 2017-01-06
	 */
	void deleteBatch(Map<String, Object> map);

	/**
	 * 插入记录
	 * @param sysOpenApi
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2017-01-06
	 */
	int insert(SysOpenApi sysOpenApi);

	/**
	 * 根据主键更新记录（选择性更新）
	 * @param sysOpenApi
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2017-01-06
	 */
	int updateByPrimaryKeySelective(SysOpenApi sysOpenApi);

	/**
	 * 根据主键查询记录
	 * @param idKey
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2017-01-06
	 */
	SysOpenApi selectByPrimaryKey(Integer idKey);

	/**
	 * 分页查询
	 * @param page
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2017-01-06
	 */
	List<SysOpenApiVO> selectPage(Page<SysOpenApiVO> page);

	/**
	 * 导出excel
	 * @param map
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2017-01-06
	 */
	List<SysOpenApiVO> toExcel(Map<String, Object> map);

	/**
	 * 根据bean属性查找多条记录
	 * @param sysOpenApi
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2017-01-06
	 */
	List<SysOpenApi> selectAllBySelective(SysOpenApi sysOpenApi);

	/**
	 * 根据bean唯一属性查找一条记录
	 * @param sysOpenApi
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2017-01-06
	 */
	SysOpenApi selectOneBySelective(SysOpenApi sysOpenApi);

	/**
	 * 检查除本条外是否存在相同标识
	 * @Description
	 * @date 2017年3月16日
	 * @author 童晟  13346450@qq.com
	 * @param @param sysUser
	 * @param @return
	 * @return SysUser
	 */
	SysOpenApi checkExistCode(SysOpenApi sysOpenApi);
}
