package com.stanley.common.spring;

import com.stanley.common.domain.TreeStructure;

import java.util.List;
import java.util.Map;
/**
 * 树形结构功能的mapper基类，所有树形结构需继承该接口
 *
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/10/24
 **/
public interface BaseTreeMapper<T extends TreeStructure> {
    /**
     * 根据主键删除一条数据
     * @param idKey
     * @return
     * @author 13346450@qq.com 童晟
     * @date 2016-04-13
     */
    int deleteByPrimaryKey(Integer idKey);

    /**
     * 插入记录
     * @param entity
     * @return
     * @author 13346450@qq.com 童晟
     * @date 2016-04-13
     */
    int insert(T entity);

    /**
     * 根据主键更新记录（选择性更新）
     * @param entity
     * @return
     * @author 13346450@qq.com 童晟
     * @date 2016-04-13
     */
    int updateByPrimaryKeySelective(T entity);

    /**
     * 根据主键查询记录
     * @param idKey
     * @return
     * @author 13346450@qq.com 童晟
     * @date 2016-04-13
     */
    T selectByPrimaryKey(Integer idKey);

    /**
     * 根据父节点查询下一级子节点
     * @param parentId
     * @return
     * @author 13346450@qq.com 童晟
     * @date 2016-04-13
     */
    List<T> selectChildrenByParentId(Integer parentId);

    /**
     * 更新指定idKey的parentId值
     * @param map
     * @author 13346450@qq.com 童晟
     * @date 2016-04-13
     */
    void updateParentIdByIdKey(Map<String, Object> map);

    /**
     * 更新本节点的last_mark、tree_level
     * @param map
     * @author 13346450@qq.com 童晟
     * @date 2016-04-13
     */
    void updateLastMarkAndLevel(Map<String, Object> map);

    /**
     * 查询所有节点
     * return
     * @author 13346450@qq.com 童晟
     * @date 2016-04-13
     */
    List<T> selectAll();

}
