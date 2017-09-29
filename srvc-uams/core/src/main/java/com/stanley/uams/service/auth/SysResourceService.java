package com.stanley.uams.service.auth;

import com.stanley.common.domain.ZtreeModel;
import com.stanley.common.spring.BaseService;
import com.stanley.uams.domain.auth.SysResource;
import com.stanley.uams.mapper.master.auth.SysResourceMapper;
import com.stanley.utils.Constants;
import com.stanley.utils.ResultBuilderUtil;
import com.stanley.utils.TreeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
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
public class SysResourceService extends BaseService {
	private final static String FUNC_MENU ="资源表(树形结构)";

	@Resource
	private SysResourceMapper sysResourceMapper;

	@Transactional
	public String insert(SysResource sysResource) {
		sysResource.setCreateId(getUserId());
		sysResource.setCreateDt(new Timestamp(System.currentTimeMillis()));
		sysResource.setLastMark(true);
		sysResource.setTreeLevel(sysResourceMapper.selectByPrimaryKey(
			sysResource.getParentId()).getTreeLevel()+1);
		sysResourceMapper.insert(sysResource);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idKey", sysResource.getParentId());
		map.put("lastMark", 0);
		sysResourceMapper.updateLastMarkAndLevel(map);
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_CREATE, "idKey:" +sysResource.getIdKey());
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("idKey", sysResource.getIdKey());
		returnMap.put("parentId", sysResource.getParentId());
		returnMap.put("cdNm", sysResource.getCdNm());
		returnMap.put("pos", this.calcOrder(sysResource.getIdKey(), sysResource.getParentId()));
		return ResultBuilderUtil.resultSuccessWithValue(returnMap);
	}

	@Transactional
	public String delete(Integer idKey) {
		if (null == idKey) {
			return ResultBuilderUtil.resultException("2","id不能为空");
		}
		Integer parentId = sysResourceMapper.selectByPrimaryKey(idKey).getParentId();
		sysResourceMapper.deleteByPrimaryKey(idKey);
		if(!haveChildren(parentId)){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("idKey", parentId);
			map.put("lastMark", 1);
			sysResourceMapper.updateLastMarkAndLevel(map);
		}
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_DELETE, "idKey:" + idKey);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("idKey", idKey);
		return ResultBuilderUtil.resultSuccessWithValue(returnMap);
	}

	@Transactional
	public String update(SysResource sysResource) {
		sysResource.setCreateId(getUserId());
		sysResource.setCreateDt(new Timestamp(System.currentTimeMillis()));
		sysResourceMapper.updateByPrimaryKeySelective(sysResource);
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_UPDATE, "idKey:" +sysResource.getIdKey());
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("idKey", sysResource.getIdKey());
		returnMap.put("parentId", sysResource.getParentId());
		returnMap.put("cdNm", sysResource.getCdNm());
		return ResultBuilderUtil.resultSuccessWithValue(returnMap);
	}

	public SysResource selectByIdkey(Integer idKey) {
		return sysResourceMapper.selectByPrimaryKey(idKey);
	}

	@Transactional
	public String updateParentIdByIdKey(Integer idKey, Integer oldParentId, Integer targetId) {
		SysResource targetMenu = sysResourceMapper.selectByPrimaryKey(targetId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idKey", idKey);
		map.put("parentId", targetId);
		map.put("treeLevel", targetMenu.getTreeLevel()+1);
		sysResourceMapper.updateParentIdByIdKey(map);
		map = new HashMap<String, Object>();
		map.put("idKey", targetId);
		map.put("lastMark", 0);
		sysResourceMapper.updateLastMarkAndLevel(map);
		if(!haveChildren(oldParentId)){
			map = new HashMap<String, Object>();
			map.put("idKey", oldParentId);
			map.put("lastMark", 1);
			sysResourceMapper.updateLastMarkAndLevel(map);
		}
		List<SysResource> resourceList=sysResourceMapper.selectChildrenByParentId(idKey);
		for(SysResource resource:resourceList)
		{
			updateParentIdByIdKey(resource.getIdKey(),idKey,idKey);
		}
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_TREEDRAG,"idKey:" + idKey+ ",oldParentId:" + oldParentId + ",newParentId:" + targetId);
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	public boolean haveChildren(Integer parentId) {
		List<SysResource> list = sysResourceMapper.selectChildrenByParentId(parentId);
		if(null != list && list.size() > 0){
			return true;
		}else{
			return false;
		}
	}

	public List<SysResource> selectChildrenByParentId(Integer parentId) {
		return sysResourceMapper.selectChildrenByParentId(parentId);
	}

	public List<SysResource> selectAll() {
		return sysResourceMapper.selectAll();
	}
	
	public List<Map<String, Object>> loadAllChildrenExceptButton(Integer parentId) {
		List<Map<String, Object>> finalList = new ArrayList<Map<String, Object>>();
		List<SysResource> dataList = sysResourceMapper.selectAllExceptButton(parentId); 
		if(dataList.isEmpty())
			return finalList;
		
		dataList.forEach(resouce -> {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("idKey", resouce.getIdKey());
			map.put("textValue", this.calcTreeTextValue(resouce.getTreeLevel())+String.valueOf(resouce.getCdNm()));
			finalList.add(map);
			List<Map<String, Object>> children = loadAllChildrenExceptButton(resouce.getIdKey());
			if(!children.isEmpty()){
				children.forEach(n -> finalList.add(n));
			}
		});
		return finalList;
	}
	
	public List<ZtreeModel> loadChildrenNodes(Integer nodeId) {
		List<ZtreeModel> children = new ArrayList<ZtreeModel>();
		if(null == nodeId) nodeId = Constants.TREE_ROOT_ID;
		SysResource sysResource = sysResourceMapper.selectByPrimaryKey(nodeId);
		if(null != sysResource){
			children = TreeUtil.convertList2Children(sysResourceMapper.selectChildrenByParentId(nodeId));
		}
		return children;
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
	
	/**
	 * 计算新节点插入的位置
	 * @Description
	 * @date 2017年4月6日
	 * @author 童晟  13346450@qq.com
	 * @param idKey
	 * @param parentId
	 * @param @return
	 * @return int
	 */
	private int calcOrder(Integer idKey, Integer parentId){
		List<SysResource> list = sysResourceMapper.selectChildrenByParentId(parentId);
		int pos = -1;
		if(list.isEmpty()) 
			return pos;
		
		for(SysResource node:list){
			pos++;
			if(node.getIdKey().equals(idKey))
				break;
		}
		return pos;
	}
	
}
