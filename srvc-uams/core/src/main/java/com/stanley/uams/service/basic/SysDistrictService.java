package com.stanley.uams.service.basic;

import com.stanley.common.domain.ZtreeModel;
import com.stanley.common.spring.BaseService;
import com.stanley.uams.domain.basic.SysDistrict;
import com.stanley.uams.mapper.master.basic.SysDistrictMapper;
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
 * 行政区域管理
 * @Description
 * @date 2016-04-13
 * @since 1.0 
 * @version 1.0  
 * @author 13346450@qq.com 童晟
 */
@Service
public class SysDistrictService extends BaseService {
	//private final static String FUNC_MENU ="行政区域表";

	@Resource
	private SysDistrictMapper sysDistrictMapper;

	@Transactional
	public String insert(SysDistrict sysDistrict) {
		sysDistrict.setCreateId(getUserId());
		sysDistrict.setCreateDt(new Timestamp(System.currentTimeMillis()));
		sysDistrict.setLastMark(true);
		sysDistrict.setTreeLevel(sysDistrictMapper.selectByPrimaryKey(
			sysDistrict.getParentId()).getTreeLevel()+1);
		sysDistrictMapper.insert(sysDistrict);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idKey", sysDistrict.getParentId());
		map.put("lastMark", 0);
		sysDistrictMapper.updateLastMarkAndLevel(map);
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_CREATE, "idKey:" +sysDistrict.getIdKey());
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("idKey", sysDistrict.getIdKey());
		returnMap.put("parentId", sysDistrict.getParentId());
		returnMap.put("cdNm", sysDistrict.getCdNm());
		returnMap.put("pos", this.calcOrder(sysDistrict.getIdKey(), sysDistrict.getParentId()));
		return ResultBuilderUtil.resultSuccessWithValue(returnMap);
	}

	@Transactional
	public String delete(Integer idKey) {
		if (null == idKey) {
			return ResultBuilderUtil.resultException("2","id不能为空");
		}
		Integer parentId = sysDistrictMapper.selectByPrimaryKey(idKey).getParentId();
		sysDistrictMapper.deleteByPrimaryKey(idKey);
		if(!haveChildren(parentId)){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("idKey", parentId);
			map.put("lastMark", 1);
			sysDistrictMapper.updateLastMarkAndLevel(map);
		}
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_DELETE, "idKey:" + idKey);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("idKey", idKey);
		return ResultBuilderUtil.resultSuccessWithValue(returnMap);
	}

	@Transactional
	public String update(SysDistrict sysDistrict) {
		sysDistrict.setCreateId(getUserId());
		sysDistrict.setCreateDt(new Timestamp(System.currentTimeMillis()));
		sysDistrictMapper.updateByPrimaryKeySelective(sysDistrict);
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_UPDATE, "idKey:" +sysDistrict.getIdKey());
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("idKey", sysDistrict.getIdKey());
		returnMap.put("parentId", sysDistrict.getParentId());
		returnMap.put("cdNm", sysDistrict.getCdNm());
		return ResultBuilderUtil.resultSuccessWithValue(returnMap);
	}

	public SysDistrict selectByIdkey(Integer idKey) {
		return sysDistrictMapper.selectByPrimaryKey(idKey);
	}

	@Transactional
	public String updateParentIdByIdKey(Integer idKey, Integer oldParentId, Integer targetId) {
		SysDistrict targetMenu = sysDistrictMapper.selectByPrimaryKey(targetId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idKey", idKey);
		map.put("parentId", targetId);
		map.put("treeLevel", targetMenu.getTreeLevel()+1);
		sysDistrictMapper.updateParentIdByIdKey(map);
		map = new HashMap<String, Object>();
		map.put("idKey", targetId);
		map.put("lastMark", 0);
		sysDistrictMapper.updateLastMarkAndLevel(map);
		if(!haveChildren(oldParentId)){
			map = new HashMap<String, Object>();
			map.put("idKey", oldParentId);
			map.put("lastMark", 1);
			sysDistrictMapper.updateLastMarkAndLevel(map);
		}
		List<SysDistrict> sysDistrictList=sysDistrictMapper.selectChildrenByParentId(idKey);
		for(SysDistrict district:sysDistrictList)
		{
			updateParentIdByIdKey(district.getIdKey(),idKey,idKey);
		}
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_TREEDRAG,"idKey:" + idKey+ ",oldParentId:" + oldParentId + ",newParentId:" + targetId);
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	public boolean haveChildren(Integer parentId) {
		List<SysDistrict> list = sysDistrictMapper.selectChildrenByParentId(parentId);
		if(null != list && list.size() > 0){
			return true;
		}else{
			return false;
		}
	}

	public List<SysDistrict> selectChildrenByParentId(Integer parentId) {
		return sysDistrictMapper.selectChildrenByParentId(parentId);
	}	
	
	public List<SysDistrict> selectDistrictSamelevel(Integer districtId) {
		return sysDistrictMapper.selectChildrenByParentId(
							sysDistrictMapper.selectByPrimaryKey(districtId).getParentId());
	}

	public List<ZtreeModel> loadChildrenNodes(Integer nodeId) {
		List<ZtreeModel> children = new ArrayList<ZtreeModel>();
		if(null == nodeId) nodeId = Constants.TREE_ROOT_ID;
		SysDistrict sysDistrict = sysDistrictMapper.selectByPrimaryKey(nodeId);
		if(null != sysDistrict){
			children = TreeUtil.convertList2Children(sysDistrictMapper.selectChildrenByParentId(nodeId));
		}
		return children;
	}
	
	/**
	 * 计算新节点插入的位置
	 * @Description
	 * @date 2017年4月13日
	 * @author 童晟  13346450@qq.com
	 * @param @param idKey
	 * @param @param parentId
	 * @param @return
	 * @return int
	 */
	private int calcOrder(Integer idKey, Integer parentId){
		List<SysDistrict> list = sysDistrictMapper.selectChildrenByParentId(parentId);
		int pos = -1;
		if(list.isEmpty()) 
			return pos;
		
		for(SysDistrict node:list){
			pos++;
			if(node.getIdKey().equals(idKey))
				break;
		}
		return pos;
	}

}
