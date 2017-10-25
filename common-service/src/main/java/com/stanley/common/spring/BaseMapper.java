package com.stanley.common.spring;

import com.stanley.common.domain.mybatis.Page;

import java.util.List;
import java.util.Map;

/**
 * 通用的mapper接口，所有mapper的基类
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/10/19
 **/
public interface BaseMapper<T, V> {
    /**
     * 根据主键删除一条数据
     * @param idKey
     * @return
     * @author 13346450@qq.com 童晟
     * @date 2016-04-08
     */
    int deleteByPrimaryKey(Integer idKey);

    /**
     * 根据多个主键批量删除（ 采用in() ）
     * @param map
     * @author 13346450@qq.com 童晟
     * @date 2016-04-08
     */
    void deleteBatch(Map<String, Object> map);

    /**
     * 插入记录
     * @param entity
     * @return
     * @author 13346450@qq.com 童晟
     * @date 2016-04-08
     */
    int insert(T entity);

    /**
     * 根据主键更新记录（选择性更新）
     * @param entity
     * @return
     * @author 13346450@qq.com 童晟
     * @date 2016-04-08
     */
    int updateByPrimaryKeySelective(T entity);

    /**
     * 根据主键查询记录
     * @param idKey
     * @return
     * @author 13346450@qq.com 童晟
     * @date 2016-04-08
     */
    T selectByPrimaryKey(Integer idKey);

    /**
     * 分页查询
     * @param page
     * @return
     * @author 13346450@qq.com 童晟
     * @date 2016-04-08
     */
    List<V> selectPage(Page<V> page);

    /**
     * 导出excel
     * @param map
     * @return
     * @author 13346450@qq.com 童晟
     * @date 2016-04-08
     */
    List<V> toExcel(Map<String, Object> map);

    /**
     * 根据bean属性查找多条记录
     * @param entity
     * @return
     * @author 13346450@qq.com 童晟
     * @date 2016-04-08
     */
    List<T> selectAllBySelective(T entity);

    /**
     * 根据bean唯一属性查找一条记录
     * @param entity
     * @return
     * @author 13346450@qq.com 童晟
     * @date 2016-04-08
     */
    T selectOneBySelective(T entity);

}
