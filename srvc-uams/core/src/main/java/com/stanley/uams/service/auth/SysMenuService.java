package com.stanley.uams.service.auth;

import com.stanley.common.domain.ZtreeModel;
import com.stanley.common.spring.BaseService;
import com.stanley.uams.domain.auth.SysMenu;
import com.stanley.uams.mapper.master.auth.SysMenuMapper;
import com.stanley.utils.Constants;
import com.stanley.utils.ResultBuilderUtil;
import com.stanley.utils.TreeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单管理
 * @Description
 * @date 2016-04-07
 * @since 1.0 
 * @version 1.0  
 * @author 13346450@qq.com 童晟
 */
@Service
public class SysMenuService extends BaseService {
	//private final static String FUNC_MENU ="菜单表";

	@Resource
	private SysMenuMapper sysMenuMapper;

	@Transactional
	public String insert(SysMenu sysMenu) {
		sysMenu.setCreateId(getUserId());
		sysMenu.setCreateDt(new Timestamp(System.currentTimeMillis()));
		sysMenu.setLastMark(true);
		sysMenu.setTreeLevel(sysMenuMapper.selectByPrimaryKey(
			sysMenu.getParentId()).getTreeLevel()+1);
		sysMenuMapper.insert(sysMenu);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idKey", sysMenu.getParentId());
		map.put("lastMark", 0);
		sysMenuMapper.updateLastMarkAndLevel(map);
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_CREATE, "idKey:" +sysMenu.getIdKey());
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("idKey", sysMenu.getIdKey());
		returnMap.put("parentId", sysMenu.getParentId());
		returnMap.put("cdNm", sysMenu.getCdNm());
		returnMap.put("pos", this.calcOrder(sysMenu.getIdKey(), sysMenu.getParentId()));
		return ResultBuilderUtil.resultSuccessWithValue(returnMap);
	}

	@Transactional
	public String delete(Integer idKey) {
		if (null == idKey) {
			return ResultBuilderUtil.resultException("2","id不能为空");
		}
		Integer parentId = sysMenuMapper.selectByPrimaryKey(idKey).getParentId();
		sysMenuMapper.deleteByPrimaryKey(idKey);
		if(!haveChildren(parentId)){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("idKey", parentId);
			map.put("lastMark", 1);
			sysMenuMapper.updateLastMarkAndLevel(map);
		}
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_DELETE, "idKey:" + idKey);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("idKey", idKey);
		return ResultBuilderUtil.resultSuccessWithValue(returnMap);
	}

	@Transactional
	public String update(SysMenu sysMenu) {
		sysMenu.setCreateId(getUserId());
		sysMenu.setCreateDt(new Timestamp(System.currentTimeMillis()));
		sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_UPDATE, "idKey:" +sysMenu.getIdKey());
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("idKey", sysMenu.getIdKey());
		returnMap.put("parentId", sysMenu.getParentId());
		returnMap.put("cdNm", sysMenu.getCdNm());
		return ResultBuilderUtil.resultSuccessWithValue(returnMap);
	}

	public SysMenu selectByIdkey(Integer idKey) {
		return sysMenuMapper.selectByPrimaryKey(idKey);
	}

	@Transactional
	public String updateParentIdByIdKey(Integer idKey, Integer oldParentId, Integer targetId) {
		SysMenu targetMenu = sysMenuMapper.selectByPrimaryKey(targetId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idKey", idKey);
		map.put("parentId", targetId);
		map.put("treeLevel", targetMenu.getTreeLevel()+1);
		sysMenuMapper.updateParentIdByIdKey(map);
		map = new HashMap<String, Object>();
		map.put("idKey", targetId);
		map.put("lastMark", 0);
		sysMenuMapper.updateLastMarkAndLevel(map);
		if(!haveChildren(oldParentId)){
			map = new HashMap<String, Object>();
			map.put("idKey", oldParentId);
			map.put("lastMark", 1);
			sysMenuMapper.updateLastMarkAndLevel(map);
		}
		List<SysMenu> childrenList=sysMenuMapper.selectChildrenByParentId(idKey);
		for(SysMenu menu:childrenList)
		{
			updateParentIdByIdKey(menu.getIdKey(),idKey,idKey);
		}
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_TREEDRAG,"idKey:" + idKey+ ",oldParentId:" + oldParentId + ",newParentId:" + targetId);
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	/*public SysMenu loadAllTreeNode() {
		return (SysMenu) TreeUtil.populateMenu(sysMenuMapper.selectAll());
	}*/

	public boolean haveChildren(Integer parentId) {
		List<SysMenu> list = sysMenuMapper.selectChildrenByParentId(parentId);
		if(null != list && list.size() > 0){
			return true;
		}else{
			return false;
		}
	}

	public List<ZtreeModel> loadChildrenNodes(Integer nodeId) {
		List<ZtreeModel> children = new ArrayList<ZtreeModel>();
		if(null == nodeId) nodeId = Constants.TREE_ROOT_ID;
		SysMenu sysMenu = sysMenuMapper.selectByPrimaryKey(nodeId);
		if(null != sysMenu){
			children = TreeUtil.convertList2Children(sysMenuMapper.selectChildrenByParentId(nodeId));
		}
		return children;
	}

	public String validate(SysMenu sysMenu) {
		String result = "";
		if(null == sysMenu.getIdKey()){//新增时
			result = (null == sysMenuMapper.selectOneBySelective(sysMenu)) ?
					ResultBuilderUtil.VALIDATE_SUCCESS:ResultBuilderUtil.VALIDATE_ERROR;
		}else{//修改时
			result = (null == sysMenuMapper.checkExistMarker(sysMenu)) ?
					ResultBuilderUtil.VALIDATE_SUCCESS:ResultBuilderUtil.VALIDATE_ERROR;
		}
		return result;
	}
	
	/**
	 * 计算新节点插入的位置
	 * @Description
	 * @date 2017年4月6日
	 * @author 童晟  13346450@qq.com
	 * @param @param idKey
	 * @param @param parentId
	 * @param @return
	 * @return int
	 */
	private int calcOrder(Integer idKey, Integer parentId){
		List<SysMenu> list = sysMenuMapper.selectChildrenByParentId(parentId);
		int pos = -1;
		if(list.isEmpty()) 
			return pos;
		
		for(SysMenu node:list){
			pos++;
			if(node.getIdKey().equals(idKey))
				break;
		}
		return pos;
	}

}
