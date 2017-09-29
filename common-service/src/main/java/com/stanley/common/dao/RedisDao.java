package com.stanley.common.dao;
import java.util.List;

/**
 * redis的增删改查操作的接口
 * @date 2017/8/19
 * @author 13346450@qq.com 童晟
 * @param
 * @return
 */
public interface RedisDao {

	/**
	 * 新增（仅当key不存在时创建）
	 * <br>------------------------------<br>
	 * @param
	 * @return
	 */
	boolean add(String key, String value);
	
	/**
	 * 批量新增 使用pipeline方式
	 * <br>------------------------------<br>
	 * @param list
	 * @return
	 */
	boolean addBatch(List<String[]> list);
	
	/**
	 * 删除
	 * <br>------------------------------<br>
	 * @param key
	 */
	void delete(String key);
	
	/**
	 * 删除多个
	 * <br>------------------------------<br>
	 * @param keys
	 */
	void delete(List<String> keys);
	
	/**
	 * 新增或修改key的值。 不存在则创建，存在则更新
	 * <br>------------------------------<br>
	 * @param
	 * @return 
	 */
	boolean set(String key, String value);

	/**
	 * 通过key获取
	 * <br>------------------------------<br>
	 * @param
	 * @return 
	 */
	String get(String key);

	/**
	 * 增加列表项
	 * <br>------------------------------<br>
	 * @param
	 * @return
	 */
	boolean listAdd(String listKey, byte[] value, boolean isLeft);

	/**
	 * 删除列表项
	 * <br>------------------------------<br>
	 * @param
	 * @return
	 */
	boolean listPop(String listKey, boolean isLeft);

	/**
	 * 删除列表项
	 * <br>------------------------------<br>
	 * @param
	 * @return
	 */
	Long listRemove(String listKey, long count, byte[] value);

	/**
	 * 查询列表项
	 * @param listKey
	 * @param
	 * @return
	 */
	List<byte[]> listQuery(String listKey, long begin, long end);
	
	/**
	 * 获取列表中元素的个数
	 * @param listKey
	 * @return
	 */
	Long listLength(String listKey);
	
	/**
	 * 清空当前数据库的所有key-value
	 * @author 13346450@qq.com 童晟 
	 * @date 2016年8月26日
	 */
	boolean flushDb();
	
	/**
	 * 获取个数
	 * @return
	 * @author 13346450@qq.com 童晟 
	 * @date 2016年8月26日
	 */
	Long dbSize();
	
	/**
	 * 设置过期
	 * @param key
	 * @param seconds
	 * @return
	 * @author 13346450@qq.com 童晟 
	 * @date 2016年8月26日
	 */
	boolean expire(String key, long seconds);
	
}