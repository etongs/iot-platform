package com.stanley.uams.service.auth;

import com.stanley.common.spring.BaseTreeService;
import com.stanley.uams.domain.auth.SysMenu;
import com.stanley.uams.mapper.master.auth.SysMenuMapper;
import com.stanley.utils.ResultBuilderUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
/**
 * 菜单管理
 * @Description
 * @date 2016-04-07
 * @since 1.0 
 * @version 1.0  
 * @author 13346450@qq.com 童晟
 */
@Service
public class SysMenuService extends BaseTreeService<SysMenu> {
	@Resource
	public void setSysMenuMapper(SysMenuMapper sysMenuMapper) {
		super.setBaseTreeMapper(sysMenuMapper);
	}

	public String validate(SysMenu sysMenu) {
		String result = "";
		if(null == sysMenu.getIdKey()){//新增时
			result = (null == ((SysMenuMapper)getBaseTreeMapper()).selectOneBySelective(sysMenu)) ?
					ResultBuilderUtil.VALIDATE_SUCCESS:ResultBuilderUtil.VALIDATE_ERROR;
		}else{//修改时
			result = (null == ((SysMenuMapper)getBaseTreeMapper()).checkExistMarker(sysMenu)) ?
					ResultBuilderUtil.VALIDATE_SUCCESS:ResultBuilderUtil.VALIDATE_ERROR;
		}
		return result;
	}
	
}
