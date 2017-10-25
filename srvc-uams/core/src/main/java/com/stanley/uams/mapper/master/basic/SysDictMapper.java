package com.stanley.uams.mapper.master.basic;

import com.stanley.common.spring.BaseMapper;
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
public interface SysDictMapper extends BaseMapper<SysDict, SysDictVO> {
	/**
	 * 查找字典名称列所有不同的值
	 * @param
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-18
	 */
	List<Map<String,Object>> selectAllByDistinct();

}
