package com.stanley.uams.service.auth;

import com.stanley.common.spring.BaseTreeService;
import com.stanley.uams.domain.auth.SysResource;
import com.stanley.uams.mapper.master.auth.SysResourceMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 资源表(树形结构管理
 * @Description
 * @date 2016-04-13
 * @since 1.0 
 * @version 1.0  
 * @author 13346450@qq.com 童晟
 */
@Service
public class SysResourceService extends BaseTreeService<SysResource> {
	@Resource
	public void setSysResourceMapper(SysResourceMapper sysResourceMapper) {
		super.setBaseTreeMapper(sysResourceMapper);
	}

	/**
	 * @Description 菜单维护时选择关联的资源
	 * @date 2017/10/24
	 * @author 13346450@qq.com 童晟
	 * @param
	 * @return
	 */public List<Map<String, Object>> loadAllChildrenExceptButton(Integer parentId) {
		List<Map<String, Object>> finalList = new ArrayList<>();
		List<SysResource> dataList = ((SysResourceMapper)getBaseTreeMapper()).selectAllExceptButton(parentId);
		if(dataList.isEmpty())
			return finalList;
		
		dataList.forEach(resource -> {
			Map<String, Object> map = new HashMap<>();
			map.put("idKey", resource.getIdKey());
			map.put("textValue", this.calcTreeTextValue(resource.getTreeLevel())+String.valueOf(resource.getCdNm()));
			finalList.add(map);
			List<Map<String, Object>> children = loadAllChildrenExceptButton(resource.getIdKey());
			if(!children.isEmpty()){
				children.forEach(n -> finalList.add(n));
			}
		});
		return finalList;
	}
	
	/**
	 * 追加前缀
	 * @Description
	 * @date 2017年4月11日
	 * @author 童晟  13346450@qq.com
	 * @param @param level
	 * @param @return
	 * @return String
	 */
	private String calcTreeTextValue(int level){
		String retValue = "";
		if(1 == level) 
			return "★ ";
		
		for(int i=1; i<level; i++){
			retValue += "&nbsp;|----";
		}
		return retValue;
	}
	
}
