package com.stanley.uams.shiro;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.stanley.common.dao.RedisManager;
import com.stanley.utils.Constants;
import com.stanley.utils.SerializeUtil;
import com.stanley.utils.StringUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description shiro访问权限的缓存（放在redis中）
 * @date 2017/10/9
 * @author 13346450@qq.com 童晟
 * @param
 * @return
 */
public class PermsRedisDAO<K, V> implements Cache<K, V> {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private RedisManager redisManager;
	private String name;
	private final String keyPrefix = "shiro-cache:";

	/**
	 * 通过一个JedisManager实例构造RedisCache
	 */
	public PermsRedisDAO(RedisManager redisManager, String name){
		 if (redisManager == null) {
	         throw new IllegalArgumentException("Cache argument cannot be null.");
	     }
	     this.redisManager = redisManager;
		 this.name = StringUtils.null2Blank(name);
	}

	/**
	 * 获得byte[]型的key
	 * @param key
	 * @return
	 */
	private byte[] getByteKey(K key){
		byte[] bytes = null;
		if(key instanceof String){
			String preKey = this.keyPrefix + this.name + key;
			try {
				bytes = preKey.getBytes(Constants.CHARSET);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}else{
			bytes = SerializeUtil.serialize(key);
		}
		return bytes;
	}


	@Override
	public V get(K key) throws CacheException {
		try {
			if (key == null) {
	            return null;
	        }else{
	        	return (V) SerializeUtil.deserialize(redisManager.get(getByteKey(key)));
	        }
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	@Override
	public V put(K key, V value) throws CacheException {
		log.debug("根据key存储权限缓存 [{}]",key);
		try {
		 	redisManager.set(getByteKey(key), SerializeUtil.serialize(value));
		} catch (Throwable t) {
			throw new CacheException(t);
		}
		return value;
	}

	@Override
	public V remove(K key) throws CacheException {
		log.debug("从redis中删除权限缓存 key [{}]",key);
		V previous = get(key);
		try {
			redisManager.del(getByteKey(key));
        } catch (Throwable t) {
            throw new CacheException(t);
        }
		return previous;
	}

	@Override
	public void clear() throws CacheException {
		log.debug("从redis中删除所有权限缓存");
		try {
			redisManager.flushDB();
        } catch (Throwable t) {
            throw new CacheException(t);
        }
	}

	@Override
	public int size() {
		try {
			Long longSize = new Long(redisManager.dbSize());
            return longSize.intValue();
        } catch (Throwable t) {
            throw new CacheException(t);
        }
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<K> keys() {
		try {
            Set<byte[]> keys = redisManager.keys(this.keyPrefix + "*");
            if (CollectionUtils.isEmpty(keys)) {
            	return Collections.emptySet();
            }else{
            	Set<K> newKeys = new HashSet<K>();
            	for(byte[] key:keys){
            		newKeys.add((K)key);
            	}
            	return newKeys;
            }
        } catch (Throwable t) {
            throw new CacheException(t);
        }
	}

	@Override
	public Collection<V> values() {
		try {
            Set<byte[]> keys = redisManager.keys(this.keyPrefix + "*");
            if (!CollectionUtils.isEmpty(keys)) {
                List<V> values = new ArrayList<V>(keys.size());
                for (byte[] key : keys) {
					V value = get((K)key);
                    if (value != null) {
                        values.add(value);
                    }
                }
                return Collections.unmodifiableList(values);
            } else {
                return Collections.emptyList();
            }
        } catch (Throwable t) {
            throw new CacheException(t);
        }
	}

}
