package com.stanley.uams.mapper.master.auth;

import com.stanley.common.spring.BaseMapper;
import com.stanley.uams.domain.auth.SysRole;
import com.stanley.uams.domain.auth.SysRoleVO;

/**
 * 角色的dao
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/10/19
 **/
public interface SysRoleMapper extends BaseMapper<SysRole, SysRoleVO> {
    /**
     * 修改记录时，判断角色名是否存在（除本身外）
     * @Description
     * @date 2017年3月3日
     * @author 童晟  13346450@qq.com
     * @param @param sysRole
     * @param @return
     * @return SysRole
     */
    SysRole checkExistRolename(SysRole sysRole);
}
