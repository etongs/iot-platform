package com.stanley.common.dao;

import java.util.Set;

import com.stanley.common.configuration.SpringContextUtil;
import com.stanley.utils.Constants;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Description redis管理器，提供基础的增删改查等操作
 * @date 2017/10/9
 * @author 13346450@qq.com 童晟
 */
public class RedisManager {
	// 0 - never expire
	private int expire = 0;
	private static JedisPool jedisPool;
	private static final int DB_INDEX = 0;

	/**
	 * @Description 实例化完后设置jedisPool，因为实例化时jedisPool的bean还未生成，所以要延迟注入
	 * @date 2017/8/29
	 * @author 13346450@qq.com 童晟
	 * @param
	 * @return
	 */
	public static void setJedisPool(){
		if(null == jedisPool){
			jedisPool = (JedisPool) SpringContextUtil.getBean("commonJedisPool");
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  "
				+"jedisPool 已注入到RedisManager  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}
	
	/**
	 * get value from redis
	 * @param key
	 * @return
	 */
	public byte[] get(byte[] key){
		Jedis jedis = null;
		byte[] value = null;
		try{
			jedis = jedisPool.getResource();
			jedis.select(DB_INDEX);
			value = jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return value;
	}
	
	/**
	 * set 
	 * @param key
	 * @param value
	 * @return
	 */
	public void set(byte[] key,byte[] value){
		Jedis jedis = null;
		try{
			jedis = jedisPool.getResource();
			jedis.select(DB_INDEX);
			jedis.set(key,value);
			if(this.expire != 0){
				jedis.expire(key, this.expire);
		 	}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
	}
	
	/**
	 * set 
	 * @param key
	 * @param value
	 * @param expire
	 * @return
	 */
	public void set(byte[] key,byte[] value,int expire){
		Jedis jedis = null;
		try{
			jedis = jedisPool.getResource();
			jedis.select(DB_INDEX);
			jedis.set(key,value);
			if(expire != 0){
				jedis.expire(key, expire);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
	}
	
	/**
	 * del
	 * @param key
	 */
	public void del(byte[] key){
		Jedis jedis = null;
		try{
			jedis = jedisPool.getResource();
			jedis.select(DB_INDEX);
			jedis.del(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
	}
	
	/**
	 * flush
	 */
	public void flushDB(){
		Jedis jedis = null;
		try{
			jedis = jedisPool.getResource();
			jedis.select(DB_INDEX);
			jedis.flushDB();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
	}
	
	/**
	 * size
	 */
	public Long dbSize(){
		Long dbSize = 0L;
		Jedis jedis = null;
		try{
			jedis = jedisPool.getResource();
			jedis.select(DB_INDEX);
			dbSize = jedis.dbSize();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return dbSize;
	}

	/**
	 * keys
	 * @return
	 */
	public Set<byte[]> keys(String pattern){
		Set<byte[]> keys = null;
		Jedis jedis = null;
		try{
			jedis = jedisPool.getResource();
			keys = jedis.keys(pattern.getBytes(Constants.CHARSET));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return keys;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

	public void setExpire(byte[] key, int seconds){
		Jedis jedis = null;
		try{
			jedis = jedisPool.getResource();
			jedis.select(DB_INDEX);
			if(seconds != 0){
				jedis.expire(key, seconds);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
	}

}
