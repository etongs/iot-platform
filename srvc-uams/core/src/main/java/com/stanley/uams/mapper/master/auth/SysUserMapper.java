package com.stanley.uams.mapper.master.auth;

import com.stanley.common.spring.BaseMapper;
import com.stanley.uams.domain.auth.SysUser;
import com.stanley.uams.domain.auth.SysUserVO;
/**
 * 用户表
 * @Description
 * @date 2016-04-11
 * @since 1.0 
 * @version 1.0 
 * @author 13346450@qq.com 童晟
*/
public interface SysUserMapper extends BaseMapper<SysUser, SysUserVO> {
	/**
	 * 检查除本条外是否存在相同帐号
	 * @Description
	 * @date 2017年3月16日
	 * @author 童晟  13346450@qq.com
	 * @param @param sysUser
	 * @param @return
	 * @return SysUser
	 */
	SysUser checkExistAccount(SysUser sysUser);
	
}
