package com.stanley.uams.mapper.master.basic;

import com.stanley.common.spring.BaseMapper;
import com.stanley.uams.domain.basic.SysOpenApi;
import com.stanley.uams.domain.basic.SysOpenApiVO;
/**
 * 开放接口接入表
 * @Description
 * @date 2017-01-06
 * @since 1.0 
 * @version 1.0 
 * @author 13346450@qq.com 童晟
*/
public interface SysOpenApiMapper extends BaseMapper<SysOpenApi, SysOpenApiVO> {
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
