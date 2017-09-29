package com.stanley.uams.service;

import com.stanley.common.spring.BaseService;
import com.stanley.uams.domain.auth.SysUser;
import com.stanley.uams.domain.auth.SysUserRole;
import com.stanley.uams.service.auth.SysUserRoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * shiro的公共服务
 *
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/9/26
 **/
@Service
public class ShiroService extends BaseService {

    @Resource
    private RedisSessionDAO redisSessionDAO;
    @Resource
    private SysUserRoleService sysUserRoleService;

    /**
     * @Description 权限修改后，清除角色对应的用户权限redis缓存
     * @date 2017/9/27
     * @author 13346450@qq.com 童晟
     * @param
     * @return
     */
    public void clearUserAuthByUserId(Integer roleId) {
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setRoleId(roleId);
        List<SysUserRole> userList = sysUserRoleService.selectAllBySelective(sysUserRole);
        if(userList.isEmpty())
            return;
        List<Integer> userIds = userList.stream().flatMap(userRole->Stream.of(userRole.getUserId())).collect(Collectors.toList());
        //获取所有活跃的session
        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
        //定义返回
        List<SimplePrincipalCollection> list = new ArrayList<SimplePrincipalCollection>();
        sessions.forEach( session -> {
            //获取session登录信息。
            Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (null != obj && obj instanceof SimplePrincipalCollection) {
                SimplePrincipalCollection spc = (SimplePrincipalCollection) obj;
                //判断用户，匹配用户ID。
                obj = spc.getPrimaryPrincipal();
                if (null != obj && obj instanceof SysUser) {
                    SysUser user = (SysUser) obj;
                    log.debug("user:" + user.getAccount()+","+user.getNameCn());
                    //比较用户ID，符合即加入集合
                    if (userIds.contains(user.getIdKey())) {
                        list.add(spc);
                    }
                }
            }
        });
        RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        MyShiroRealm realm = (MyShiroRealm) securityManager.getRealms().iterator().next();
        list.forEach(simplePrincipalCollection -> realm.clearCachedAuthorizationInfo(simplePrincipalCollection));
        log.debug("角色对应的当前登录用户的权限缓存已清空");
    }

}
