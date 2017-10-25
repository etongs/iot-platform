package com.stanley.uams.mapper.master.auth;

import com.stanley.common.spring.BaseTreeMapper;
import com.stanley.uams.domain.auth.SysResource;

import java.util.List;
import java.util.Map;

/**
 * 资源表(树形结构)
 * @Description
 * @date 2016-04-13
 * @since 1.0 
 * @version 1.0 
 * @author 13346450@qq.com 童晟
*/
public interface SysResourceMapper extends BaseTreeMapper<SysResource> {
	/**
	 * 查询除按钮外的所有节点
	 * @Description
	 * @date 2017年4月7日
	 * @author 童晟  13346450@qq.com
	 * @param @return
	 * @return List<SysResource>
	 */
	List<SysResource> selectAllExceptButton(Integer parentId);

}
