package com.stanley.uams.shiro;

import com.stanley.uams.domain.auth.SysResource;
import com.stanley.uams.domain.auth.SysUser;
import com.stanley.uams.service.auth.SysPermissionService;
import com.stanley.uams.service.auth.SysRoleService;
import com.stanley.uams.service.auth.SysUserService;
import com.stanley.utils.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.stanley.utils.Constants.LOGIN_FAILED_MAXTIMES;
import static com.stanley.utils.Constants.SHIRO_IS_LOCK;
import static com.stanley.utils.Constants.SHIRO_LOGIN_COUNT;

/**
 * @Description shiro 授权、认证处理中心
 * @date 2017/8/25
 * @author 13346450@qq.com 童晟
 * @param
 * @return
 */
public class MyShiroRealm extends AuthorizingRealm {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysPermissionService sysPermissionService;
    @Resource
    private SysRoleService sysRoleService;
    @Resource(name = "redisTemplate")
    private StringRedisTemplate redisTemplate;

    /**
     * @Description 授权资源，把该用户对应角色的权限表达式取出来放到SimpleAuthorizationInfo
     * @date 2017/8/25
     * @author 13346450@qq.com 童晟
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        List<String> permissionList = new ArrayList<>();
        String[] roleArray = sysUserService.selectByIdkey(user.getIdKey()).getRoleIds().split(",");
        List<String> roleList = new ArrayList<>();
        for(String roleId : roleArray){
            roleList.add(sysRoleService.selectByIdkey(Integer.parseInt(roleId)).getRoleName());
            List<SysResource> list = sysPermissionService.selectResourcesByRoleId(Integer.parseInt(roleId));
            if (!list.isEmpty()){
                list.forEach(sysResource -> {
                    String expression = sysResource.getExpression();
                    if(!StringUtils.isNull(expression)){
                        if(!permissionList.contains(expression)){
                            permissionList.add(expression);
                        }
                    }
                });
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roleList);
        info.addStringPermissions(permissionList);
        log.debug("当前用户({}),拥有的权限(角色组)：{} , (资源组)：{}",user.getAccount(),
                String.join(",",roleList),
                permissionList.stream().collect(Collectors.joining(",")));
        return info;
    }

    /**
     * @Description 登录认证
     * 点击登录按钮时执行，注意这里不是验证登录的地方，而是获取用户凭证，验证逻辑在框架里会比对密码是否正确
     * 在checkLogin中的Subject.login()调用此方法
     * @date 2017/8/25
     * @author 13346450@qq.com 童晟
     * @param
     * @return org.apache.shiro.authc.AuthenticationInfo
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取基于用户名和密码的令牌，实际上authenticationToken是从LoginController里面currentUser.login(token)传过来的,两个token的引用都是一样的
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String name = (String) token.getPrincipal();
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        //先判断有无锁定
        if ("LOCK".equals(opsForValue.get(SHIRO_IS_LOCK+name))){
            throw new DisabledAccountException("密码输入错误超过"+LOGIN_FAILED_MAXTIMES+"次，帐号被锁定2小时！");
        }
        //计数大于5时，设置用户被锁定2小时
        opsForValue.increment(SHIRO_LOGIN_COUNT+name, 1);
        //因为登录验证失败时，该方法会执行两次，所以乘以2
        if(Integer.parseInt(opsForValue.get(SHIRO_LOGIN_COUNT+name)) > LOGIN_FAILED_MAXTIMES*2){
            opsForValue.set(SHIRO_IS_LOCK+name, "LOCK");
            redisTemplate.expire(SHIRO_IS_LOCK+name, 2, TimeUnit.HOURS);
        }
        SysUser condition = new SysUser();
        condition.setAccount(name);
        SysUser sysUser = sysUserService.selectOneBySelective(condition);
        if(null == sysUser){
            throw new UnknownAccountException();
        }
        if (sysUser.getIsLocked()){
            throw new DisabledAccountException("此帐号已经设置为禁止登录！");
        }
        //此处无需比对密码，比对的逻辑Shiro会做，我们只需返回一个和令牌相关的正确的验证信息
        //第二个参数：密码是从数据库中取到的密文，而不是明文
        return new SimpleAuthenticationInfo(
                sysUser, sysUser.getPasswd(), ByteSource.Util.bytes(name), getName());
    }

    /**
     * 清空指定用户的权限缓存
     */
    public void clearCachedAuthorizationInfo(List<SimplePrincipalCollection> list) {
        list.forEach(simplePrincipalCollection -> super.clearCachedAuthorizationInfo(simplePrincipalCollection));
        log.debug("角色对应的{}个登录用户的权限缓存已清空",list.size());
    }

}
