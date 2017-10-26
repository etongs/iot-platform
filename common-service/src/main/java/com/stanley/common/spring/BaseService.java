package com.stanley.common.spring;

import com.stanley.common.domain.SearchParam;
import com.stanley.common.domain.mybatis.Page;
import com.stanley.utils.ReflectUtil;
import com.stanley.utils.ResultBuilderUtil;
import com.stanley.utils.ShiroSessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公共的服务层基类
 * 注意：@transactional不能继承，如果子类需重写带事务的方法，需在子类的方法头加上@transactional注解
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/8/25
 **/
public class BaseService<T, V>  {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    private BaseMapper<T, V> baseMapper;
    /**
     * @Description 必须在子类中调用该方法设置baseMapper的实现类
     * @date 2017/10/20
     * @author 13346450@qq.com 童晟
     * @param
     * @return
     */
    protected void setBaseMapper(BaseMapper<T, V> baseMapper) {
        this.baseMapper = baseMapper;
    }

    protected BaseMapper<T, V> getBaseMapper() {
        return baseMapper;
    }

    /**
     * @Description 插入记录
     * @date 2017/10/20
     * @author 13346450@qq.com 童晟
     * @param
     * @return
     */
    @Transactional
    public String insert(T entity) {
        ReflectUtil.setAttributeValue(entity, "createId", ShiroSessionUtil.getUserId());
        ReflectUtil.setAttributeValue(entity, "createDt", new Timestamp(System.currentTimeMillis()));
        baseMapper.insert(entity);
        return ResultBuilderUtil.RESULT_SUCCESS;
    }

    /**
     * @Description 删除一条记录
     * @date 2017/10/20
     * @author 13346450@qq.com 童晟
     * @param
     * @return
     */
    @Transactional
    public String delete(Integer idKey) {
        if (null == idKey) {
            return ResultBuilderUtil.resultException("2","主键不能为空");
        }
        baseMapper.deleteByPrimaryKey(idKey);
        return ResultBuilderUtil.RESULT_SUCCESS;
    }

    /**
     * @Description 批量删除
     * @date 2017/10/20
     * @author 13346450@qq.com 童晟
     * @param
     * @return
     */
    @Transactional
    public String deleteBatch(String dataIds) {
        Map<String, Object> map = new HashMap<>();
        map.put("dataListID", Arrays.asList(dataIds.split(",")));
        baseMapper.deleteBatch(map);
        return ResultBuilderUtil.RESULT_SUCCESS;
    }

    /**
     * @Description 修改一条记录
     * @date 2017/10/20
     * @author 13346450@qq.com 童晟
     * @param
     * @return
     */
    @Transactional
    public String update(T entity) {
        ReflectUtil.setAttributeValue(entity, "createId", ShiroSessionUtil.getUserId());
        ReflectUtil.setAttributeValue(entity, "createDt", new Timestamp(System.currentTimeMillis()));
        baseMapper.updateByPrimaryKeySelective(entity);
        return ResultBuilderUtil.RESULT_SUCCESS;
    }

    /**
     * @Description 根据主键查询
     * @date 2017/10/20
     * @author 13346450@qq.com 童晟
     * @param
     * @return
     */
    public T selectByIdkey(Integer idKey) {
        Object t = baseMapper.selectByPrimaryKey(idKey);
        return (T)t;
    }

    /**
     * @Description 分页查询
     * @date 2017/10/20
     * @author 13346450@qq.com 童晟
     * @param
     * @return
     */
    public Page<V> selectPage(SearchParam searchParam, Map<String, Object> conditions) {
        Page<V> page = new Page<>();
        page.setOffset(searchParam.getOffset());
        page.setLimit(searchParam.getLimit());
        Map<String, Object> map = new HashMap<>();
        map.put("sort", searchParam.getSort());
        map.put("order", searchParam.getOrder());
        if(null != conditions){
            for(Map.Entry<String, Object> entry : conditions.entrySet()){
                map.put(entry.getKey(), entry.getValue());
            }
        }
        page.setParams(map);
        page.setRows(baseMapper.selectPage(page));
        return page;
    }

    /**
     * @Description 导出excel
     * @date 2017/10/20
     * @author 13346450@qq.com 童晟
     * @param
     * @return
     */
    public List<V> toExcel(Map<String, Object> conditions) {
        return baseMapper.toExcel(conditions);
    }

    /**
     * @Description 组合条件查询一条
     * @date 2017/10/20
     * @author 13346450@qq.com 童晟
     * @param
     * @return
     */
    public T selectOneBySelective(T entity) {
        Object t = baseMapper.selectOneBySelective(entity);
        return (T)t;
    }

    /**
     * @Description 组合条件查询列表（多条）
     * @date 2017/10/20
     * @author 13346450@qq.com 童晟
     * @param
     * @return
     */
    public List<T> selectAllBySelective(T entity) {
        return baseMapper.selectAllBySelective(entity);
    }

}
