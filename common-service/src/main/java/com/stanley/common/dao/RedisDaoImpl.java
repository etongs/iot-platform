package com.stanley.common.dao;

import com.stanley.common.configuration.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.List;

/**
 * redis的数据操作实现类
 * @Description
 * @date 2016年6月13日     
 * @since 1.0   
 * @version 1.0  
 * @author 13346450@qq.com 童晟
 */
@Repository
public class RedisDaoImpl implements RedisDao {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/* 必须用Resource，不能用Autowired。 @Autowired按byType自动注入，@Resource默认按 byName自动注入。 */
	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * @Description 此时已完成redisConfig的装配，redisPool已创建完成，所以在此时注入到mybatisRedisCache中
	 * @date 2017/8/29
	 * @author 13346450@qq.com 童晟
	 * @param
	 * @return
	 */
	public RedisDaoImpl(){
		MybatisRedisCache.setJedisPool();
		RedisManager.setJedisPool();
	}

	/**
	 * 获取 RedisSerializer
	 */
	private RedisSerializer<String> getRedisSerializer() {
		return redisTemplate.getStringSerializer();
	}

	@Override
	public boolean add(final String key, final String value) {
		boolean result = redisTemplate.execute((RedisConnection connection) -> {
				return connection.setNX(getRedisSerializer().serialize(key), getRedisSerializer().serialize(value));
			}
		);
		return result;
	}

	@Override
	public boolean addBatch(final List<String[]> list) {
		Assert.notEmpty(list,"集合list 不能为空，至少要有一个元素。");
		boolean result = redisTemplate.execute((RedisConnection connection) -> {
				RedisSerializer<String> serializer = getRedisSerializer();
				list.forEach(strArray -> connection.setNX(serializer.serialize(strArray[0]), serializer.serialize(strArray[1])));
				return true;
			},false, true
		);
		return result;
	}

	@Override
	public void delete(String key) {
		redisTemplate.delete(key);
	}

	@Override
	public void delete(List<String> keys) {
		redisTemplate.delete(keys);
	}

	@Override
	public boolean set(final String key, final String value) {
		boolean result = redisTemplate.execute((RedisConnection connection) -> {
				connection.set(getRedisSerializer().serialize(key), getRedisSerializer().serialize(value));
				return true;
			}
		);
		return result;
	}

	@Override
	public String get(final String key) {
		String result = redisTemplate.execute((RedisConnection connection) -> {
				byte[] value = connection.get(getRedisSerializer().serialize(key));
				if (value == null) {
					return null;
				}
				return getRedisSerializer().deserialize(value);
			}
		);
		return result;
	}

	@Override
	public boolean listAdd(final String listKey, final byte[] value, final boolean isLeft) {
		boolean result = redisTemplate.execute((RedisConnection connection) -> {
				if(isLeft)
					connection.lPush(getRedisSerializer().serialize(listKey), value);
				else
					connection.rPush(getRedisSerializer().serialize(listKey), value);
				return true;
			}
		);
		return result;
	}

	@Override
	public boolean listPop(final String listKey, final boolean isLeft) {
		boolean result = redisTemplate.execute((RedisConnection connection) -> {
				if(isLeft)
					connection.lPop(getRedisSerializer().serialize(listKey));
				else
					connection.rPop(getRedisSerializer().serialize(listKey));
				return true;
			}
		);
		return result;
	}

	@Override
	public Long listRemove(final String listKey, final long count, final byte[] value) {
		Long result = redisTemplate.execute((RedisConnection connection) -> {
				return connection.lRem(getRedisSerializer().serialize(listKey), count, value);
			}
		);
		return result;
	}

	@Override
	public List<byte[]> listQuery(final String listKey, final long begin, final long end) {
		List<byte[]> result = redisTemplate.execute((RedisConnection connection) -> {
				return connection.lRange(getRedisSerializer().serialize(listKey), begin, end);
			}
		);
		return result;
	}

	@Override
	public Long listLength(final String listKey) {
		Long result = redisTemplate.execute((RedisConnection connection) -> {
				return connection.lLen(getRedisSerializer().serialize(listKey));
			}
		);
		return result;
	}

	@Override
	public boolean flushDb() {
		boolean result = redisTemplate.execute((RedisConnection connection) -> {
				connection.flushDb();
				return true;
			}
		);
		return result;
	}

	@Override
	public Long dbSize() {
		Long result = redisTemplate.execute((RedisConnection connection) -> {
				return connection.dbSize();
			}
		);
		return result;
	}

	@Override
	public boolean expire(final String key, final long seconds) {
		boolean result = redisTemplate.execute((RedisConnection connection) -> {
				return connection.expire(getRedisSerializer().serialize(key), seconds);
			}
		);
		return result;
	}

}
