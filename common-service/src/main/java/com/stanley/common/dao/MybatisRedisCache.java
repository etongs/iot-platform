package com.stanley.common.dao;

import com.stanley.common.configuration.SpringContextUtil;
import com.stanley.utils.Constants;
import com.stanley.utils.SerializeUtil;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.UnsupportedEncodingException;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * mybatis的二级缓存redis的实现类
 * <br>
 * <em>说明：新创建一个redis连接，连接池用spring已实例化的bean：poolConfig，这样可保证在redis服务中断后，自动重新连接</em>
 * @ClassName: MybatisRedisCache
 * @Description
 * @date 2017年5月19日
 * @since 1.0 
 * @version 1.0  
 * @author  13346450@qq.com 童晟 
 */
public class MybatisRedisCache implements Cache {
	private Logger log = LoggerFactory.getLogger(this.getClass());
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();   
    private String id;
    private static JedisPool jedisPool;
    private static final int DB_INDEX = 0;
    private final String COMMON_CACHE_KEY = "MYBATIS_CACHE:";

    public MybatisRedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("必须传入ID");
        }
        this.id = id;
        log.debug(">>>>>>>>>>>>>>>  Mybatis Cache for Redis 启动: id={}  <<<<<<<<<<<<<<<<",id);
    }

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
                +"jedisPool 已注入到mybatisRedisCache  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }

    private String getKey(Object key) {
        StringBuilder accum = new StringBuilder();
        try {
            accum.append(COMMON_CACHE_KEY);
            accum.append(this.id).append(":");
            accum.append(DigestUtils.md5DigestAsHex(String.valueOf(key).getBytes(Constants.CHARSET)));
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return accum.toString();
    }
    
    private String getKeys() {
        return COMMON_CACHE_KEY + this.id + ":*";
    }

	@Override
	public void clear() {
		Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(DB_INDEX);
            // 如果有删除操作，会影响到整个表中的数据，因此要清空一个mapper的缓存（一个mapper的不同数据操作对应不同的key）
            Set<byte[]> keys = jedis.keys(getKeys().getBytes(Constants.CHARSET));
            log.debug("写操作，清空对应Mapper缓存======>" + keys.size());
            for (byte[] key : keys) {
                jedis.del(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public Object getObject(Object key) {
		Jedis jedis = null;
        Object value = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(DB_INDEX);
            value = SerializeUtil.deserialize(jedis.get(getKey(key).getBytes(Constants.CHARSET)));
            if(null != value)
            	log.debug("从缓存中取数  >>>>>>>>>>>>> {}" , this.id);
            // getSize();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return value;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		log.debug("MybatisRedisCache>>>>>> 获取读写锁");
		return readWriteLock;
	}

	@Override
	public int getSize() {
		Jedis jedis = null;
        int result = 0;
        try {
            jedis = jedisPool.getResource();
            jedis.select(DB_INDEX);//Protocol.DEFAULT_DATABASE
            Set<byte[]> keys = jedis.keys(getKeys().getBytes(Constants.CHARSET));
            if (null != keys && !keys.isEmpty()) {
                result = keys.size();
            }
            log.debug(this.id + "---->>>>总缓存数:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return result;
	}

	@Override
	public void putObject(Object key, Object value) {
		Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(DB_INDEX);
            jedis.set(getKey(key).getBytes(Constants.CHARSET), SerializeUtil.serialize(value));
            log.debug("添加到缓存 >>>>>>>> {}",  this.id);
            // getSize();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
	}

	@Override
	public Object removeObject(Object key) {
		Jedis jedis = null;
        Object value = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(DB_INDEX);
            value = jedis.del(getKey(key).getBytes(Constants.CHARSET));
            log.debug("LRU算法从缓存中移除 <<<<<<" + this.id);
            // getSize();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return value;
	}

}
