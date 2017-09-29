package com.stanley.uams.initialize;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.stanley.uams.service.MyShiroRealm;
import com.stanley.utils.Constants;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

/**
 * Created by stanley on 2017/4/23.
 */
@Configuration
public class ShiroConfig {

    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * ShiroDialect，为了在thymeleaf里使用shiro的标签的bean
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
     Filter Chain定义说明
     1、一个URL可以配置多个Filter，使用逗号分隔
     2、当设置多个过滤器时，全部验证通过，才视为通过
     3、部分过滤器可指定参数，如perms，roles
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager,
                                             @Value("${shiro.filterChainDefinition.anon}") String shiroFilterAnon){
        ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 配置登录的url和登录成功的url
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/home");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        //过滤链定义，从上向下顺序执行，一般将 /**放在最为下边
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        Arrays.asList(shiroFilterAnon.split(",")).forEach(s -> filterChainDefinitionMap.put(s, "anon"));
        //验证码生成
        filterChainDefinitionMap.put("/getGifCode", "anon");
        //验证码校验
        filterChainDefinitionMap.put("/checkValidateCode/*", "anon");
        filterChainDefinitionMap.put("/logout", "logout");
        /* written by ts 2017-9-7
         * perms:授权过滤器, perms括号里面可以用url或者权限表达式
         * 如果用url，则控制器方法无需加注解；如果用表达式，方法头则必须加@requiresPermission
         * List<SysResource> list = sysResourceService.selectAll();
         * list.forEach(sysResource -> {
         *           if (StringUtils.isNotEmpty(sysResource.getUrl())) {
         *               String permission = "perms[" + sysResource.getUrl()+ "]";
         *               filterChainDefinitionMap.put(sysResource.getUrl(),permission);
         *           }
         *       }
         * );
         * filterChainDefinitionMap.put("/listMyMenu/1", "perms[/listMyMenu/1]");
         * 此处不设置，表示默认情况下只要登录后就可以访问
         * 需要权限控制的自行增加@RequiresPermissions("xxxxxxxxx")
         */
        filterChainDefinitionMap.put("/druid/**","roles[系统管理员]");
        //authc:所有url都需要认证(登录)才能使用
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(@Qualifier("sessionManager") DefaultWebSessionManager sessionManager,
                                           RedisCacheManager redisCacheManager,
                                           @Qualifier("rememberMeManager") CookieRememberMeManager rememberMeManager){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm.
        securityManager.setRealm(myShiroRealm());
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(redisCacheManager);
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager);
        //注入记住我管理器
        securityManager.setRememberMeManager(rememberMeManager);
        return securityManager;
    }

    @Bean
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     *  所以我们需要修改下doGetAuthenticationInfo中的代码;
     * ）
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(Constants.SHIRO_ENCRYPT_ALGORITHM);
        hashedCredentialsMatcher.setHashIterations(Constants.SHIRO_ENCRYPT_TIMES);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }

    /**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     * @return
     */
    @Bean
    public RedisManager redisManager(@Value("${spring.redis.host}") String host,
                                     @Value("${spring.redis.port}") int port,
                                     @Value("${spring.redis.password}") String password,
                                     @Value("${spring.redis.timeout}") int timeout) {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        redisManager.setExpire(1800);// 配置缓存过期时间
        redisManager.setTimeout(timeout);
        redisManager.setPassword(password);
        return redisManager;
    }

    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     * @return
     */
    @Bean
    public RedisCacheManager redisCacheManager(RedisManager redisManager) {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager);
        return redisCacheManager;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO(RedisManager redisManager) {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager);
        return redisSessionDAO;
    }

    /**
     * shiro session的管理
     */
    @Bean(name = "sessionManager")
    public DefaultWebSessionManager sessionManager(RedisSessionDAO redisSessionDAO) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO);
        return sessionManager;
    }

    /**
     * 记住我cookie生效时间30天
     * cookie对象;
     * @return
     */
    public SimpleCookie rememberMeCookie(){
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setMaxAge(30*24*3600);
        return simpleCookie;
    }
    /**
     * cookie管理对象;记住我功能
     * @return
     */
    @Bean(name = "rememberMeManager")
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }

}
