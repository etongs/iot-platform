package com.stanley.uams.mapper.master.basic;

import com.stanley.uams.domain.basic.SysDistrict;

import java.util.List;
import java.util.Map;

/**
 * 行政区域表
 * @Description
 * @date 2016-04-13
 * @since 1.0 
 * @version 1.0 
 * @author 13346450@qq.com 童晟
*/
public interface SysDistrictMapper {
	/**
	 * 根据主键删除一条数据
	 * @param idKey
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-13
	 */
	int deleteByPrimaryKey(Integer idKey);

	/**
	 * 插入记录
	 * @param sysDistrict
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-13
	 */
	int insert(SysDistrict sysDistrict);

	/**
	 * 根据主键更新记录（选择性更新）
	 * @param sysDistrict
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-13
	 */
	int updateByPrimaryKeySelective(SysDistrict sysDistrict);

	/**
	 * 根据主键查询记录
	 * @param idKey
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-13
	 */
	SysDistrict selectByPrimaryKey(Integer idKey);

	/**
	 * 根据父节点查询下一级子节点
	 * @param parentId
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-13
	 */
	List<SysDistrict> selectChildrenByParentId(Integer parentId);

	/**
	 * 更新指定idKey的parentId值
	 * @param map
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-13
	 */
	void updateParentIdByIdKey(Map<String, Object> map);

	/**
	 * 更新本节点的last_mark、tree_level
	 * @param map
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-13
	 */
	void updateLastMarkAndLevel(Map<String, Object> map);

	/**
	 * 查询所有节点
	 * return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-13
	 */
	List<SysDistrict> selectAll();

}
