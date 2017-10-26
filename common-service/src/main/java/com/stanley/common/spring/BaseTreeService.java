package com.stanley.common.spring;

import com.stanley.common.domain.TreeStructure;
import com.stanley.common.domain.ZtreeModel;
import com.stanley.utils.Constants;
import com.stanley.utils.ResultBuilderUtil;
import com.stanley.utils.ShiroSessionUtil;
import com.stanley.utils.TreeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公共的树形结构服务层基类
 * 注意：@transactional不能继承，如果子类需重写带事务的方法，需在子类的方法头加上@transactional注解
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/8/25
 **/
public class BaseTreeService<T extends TreeStructure>  {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    private BaseTreeMapper<T> baseTreeMapper;
    /**
     * @Description 必须在子类中调用该方法设置baseMapper的实现类
     * @date 2017/10/20
     * @author 13346450@qq.com 童晟
     * @param
     * @return
     */
    protected void setBaseTreeMapper(BaseTreeMapper<T> baseTreeMapper) {
        this.baseTreeMapper = baseTreeMapper;
    }

    protected BaseTreeMapper<T> getBaseTreeMapper() {
        return baseTreeMapper;
    }

    /**
     * @Description 插入节点
     * @date 2017/10/20
     * @author 13346450@qq.com 童晟
     * @param
     * @return
     */
    @Transactional
    public String insert(T entity) {
        entity.setCreateId(ShiroSessionUtil.getUserId());
        entity.setCreateDt(new Timestamp(System.currentTimeMillis()));
        entity.setLastMark(Boolean.TRUE);
        entity.setTreeLevel(baseTreeMapper.selectByPrimaryKey(
                entity.getParentId()).getTreeLevel()+1);
        baseTreeMapper.insert(entity);
        Map<String, Object> map = new HashMap<>();
        map.put("idKey", entity.getParentId());
        map.put("lastMark", 0);
        baseTreeMapper.updateLastMarkAndLevel(map);
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("idKey", entity.getIdKey());
        returnMap.put("parentId", entity.getParentId());
        returnMap.put("cdNm", entity.getCdNm());
        returnMap.put("pos", this.calcOrder(entity.getIdKey(), entity.getParentId()));
        return ResultBuilderUtil.resultSuccessWithValue(returnMap);
    }

    /**
     * 删除节点
     * @param idKey
     * @return
     */
    @Transactional
    public String delete(Integer idKey) {
        if (null == idKey) {
            return ResultBuilderUtil.resultException("2","主键不能为空");
        }
        Integer parentId = baseTreeMapper.selectByPrimaryKey(idKey).getParentId();
        baseTreeMapper.deleteByPrimaryKey(idKey);
        if(!haveChildren(parentId)){
            Map<String, Object> map = new HashMap<>();
            map.put("idKey", parentId);
            map.put("lastMark", 1);
            baseTreeMapper.updateLastMarkAndLevel(map);
        }
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("idKey", idKey);
        return ResultBuilderUtil.resultSuccessWithValue(returnMap);
    }

    /**
     * 修改节点
     * @param entity
     * @return
     */
    @Transactional
    public String update(T entity) {
        entity.setCreateId(ShiroSessionUtil.getUserId());
        entity.setCreateDt(new Timestamp(System.currentTimeMillis()));
        baseTreeMapper.updateByPrimaryKeySelective(entity);
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("idKey", entity.getIdKey());
        returnMap.put("parentId", entity.getParentId());
        returnMap.put("cdNm", entity.getCdNm());
        return ResultBuilderUtil.resultSuccessWithValue(returnMap);
    }

    /**
     * 查询
     * @param idKey
     * @return
     */
    public T selectByIdkey(Integer idKey) {
        return baseTreeMapper.selectByPrimaryKey(idKey);
    }

    /**
     * 拖拽节点时的更新
     * @param idKey
     * @param oldParentId
     * @param targetId
     * @return
     */
    @Transactional
    public String updateParentIdByIdKey(Integer idKey, Integer oldParentId, Integer targetId) {
        T targetMenu = baseTreeMapper.selectByPrimaryKey(targetId);
        Map<String, Object> map = new HashMap<>();
        map.put("idKey", idKey);
        map.put("parentId", targetId);
        map.put("treeLevel", targetMenu.getTreeLevel()+1);
        baseTreeMapper.updateParentIdByIdKey(map);
        map = new HashMap<>();
        map.put("idKey", targetId);
        map.put("lastMark", 0);
        baseTreeMapper.updateLastMarkAndLevel(map);
        if(!haveChildren(oldParentId)){
            map = new HashMap<>();
            map.put("idKey", oldParentId);
            map.put("lastMark", 1);
            baseTreeMapper.updateLastMarkAndLevel(map);
        }
        List<T> entityList=baseTreeMapper.selectChildrenByParentId(idKey);
        for(T ent : entityList) {
            updateParentIdByIdKey(ent.getIdKey(),idKey,idKey);
        }
        return ResultBuilderUtil.RESULT_SUCCESS;
    }

    /**
     * @Description 查询子节点
     * @date 2017/10/24
     * @author 13346450@qq.com 童晟
     * @param
     * @return
     */
    public List<T> selectChildrenByParentId(Integer parentId) {
        return baseTreeMapper.selectChildrenByParentId(parentId);
    }

    /**
     * @Description 查询同级的节点
     * @date 2017/10/24
     * @author 13346450@qq.com 童晟
     * @param
     * @return
     */
    public List<T> selectSameLevelNodes(Integer nodeId) {
        return baseTreeMapper.selectChildrenByParentId(
                baseTreeMapper.selectByPrimaryKey(nodeId).getParentId());
    }

    /**
     * @Description 加载子节点到Ztree，只一级
     * @date 2017/10/24
     * @author 13346450@qq.com 童晟
     * @param
     * @return
     */
    public List<ZtreeModel> loadChildrenNodes(Integer nodeId) {
        List<ZtreeModel> children = new ArrayList<>();
        if(null == nodeId) nodeId = Constants.TREE_ROOT_ID;
        T entity = baseTreeMapper.selectByPrimaryKey(nodeId);
        if(null != entity){
            children = TreeUtil.convertList2Children(baseTreeMapper.selectChildrenByParentId(nodeId));
        }
        return children;
    }

    /**
     * @Description 是否有子节点
     * @date 2017/10/24
     * @author 13346450@qq.com 童晟
     * @param
     * @return
     */
    protected boolean haveChildren(Integer parentId) {
        List<T> list = baseTreeMapper.selectChildrenByParentId(parentId);
        return !list.isEmpty();
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
        List<T> list = baseTreeMapper.selectChildrenByParentId(parentId);
        int pos = -1;
        if(list.isEmpty())
            return pos;

        for(T node:list){
            pos++;
            if(node.getIdKey().equals(idKey))
                break;
        }
        return pos;
    }

}
