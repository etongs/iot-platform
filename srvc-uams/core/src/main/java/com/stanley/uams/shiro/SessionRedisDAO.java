package com.stanley.uams.shiro;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.stanley.common.dao.RedisManager;
import com.stanley.utils.Constants;
import com.stanley.utils.SerializeUtil;
import com.stanley.utils.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description redis 针对shiro session的增删改查操作
 * @date 2017/10/9
 * @author 13346450@qq.com 童晟
 */
public class SessionRedisDAO extends AbstractSessionDAO {

	protected Logger log = LoggerFactory.getLogger(this.getClass());
	private RedisManager redisManager;
	private final String keyPrefix = "shiro-session:";
	
	@Override
	public void update(Session session) throws UnknownSessionException {
		this.saveSession(session);
	}
	
	/**
	 * save session
	 * @param session
	 * @throws UnknownSessionException
	 */
	private void saveSession(Session session) throws UnknownSessionException{
		if(session == null || session.getId() == null){
			log.error("session or session id is null");
			return;
		}
		redisManager.set(getByteKey(session.getId()), SerializeUtil.serialize(session));
	}

	@Override
	public void delete(Session session) {
		if(session == null || session.getId() == null){
			log.error("session or session id is null");
			return;
		}
		redisManager.del(getByteKey(session.getId()));
	}

	/**
	 * 设置session过期
	 * @param sessionId
	 */
	public void expire(String sessionId){
		if(!StringUtils.isNull(sessionId))
			redisManager.setExpire(getByteKey(sessionId),1);
	}

	@Override
	public Collection<Session> getActiveSessions() {
		Set<Session> sessions = new HashSet<Session>();
		Set<byte[]> keys = redisManager.keys(this.keyPrefix + "*");
		if(keys != null && keys.size()>0){
			for(byte[] key:keys){
				Session s = (Session)SerializeUtil.deserialize(redisManager.get(key));
				sessions.add(s);
			}
		}
		return sessions;
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = this.generateSessionId(session);  
        this.assignSessionId(session, sessionId);
        this.saveSession(session);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		if(sessionId == null){
			log.error("session id is null");
			return null;
		}
		Session s = (Session)SerializeUtil.deserialize(redisManager.get(getByteKey(sessionId)));
		return s;
	}
	
	/**
	 * 获得byte[]型的key
	 * @return
	 */
	private byte[] getByteKey(Serializable sessionId){
		String preKey = this.keyPrefix + sessionId;
		byte[] bytes = null;
		try {
			bytes = preKey.getBytes(Constants.CHARSET);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return bytes;
	}


	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
	}

}
