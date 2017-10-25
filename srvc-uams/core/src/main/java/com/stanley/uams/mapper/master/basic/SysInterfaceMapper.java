package com.stanley.uams.mapper.master.basic;

import com.stanley.common.spring.BaseMapper;
import com.stanley.uams.domain.basic.SysInterface;
import com.stanley.uams.domain.basic.SysInterfaceVO;

import java.util.Map;

/**
 * 外部接口配置表
 * @Description
 * @date 2016-10-19
 * @since 1.0 
 * @version 1.0 
 * @author lcw
*/
public interface SysInterfaceMapper extends BaseMapper<SysInterface, SysInterfaceVO> {
	/**
	 * 根据多个主键批量改变状态（ 采用in() ）
	 * @param map
	 * @author lcw
	 * @date 2016-10-19
	 */
	void updateStatusBatch(Map<String, Object> map);

}
