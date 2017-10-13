# 物联网云平台基础架构


#####本系统采用SpringBoot、SpringCloud基础框架搭建  

具有以下模块

###一、shiro权限管理
1. Shiro 初始权限动态加载  
2. Shiro Redis 的集成，权限修改后，可实时清空缓存  
3. Shiro Ajax请求未登录（过期）的解决方案，重写UserFilter过滤器，增加ajax全局错误事件  
4. Shiro Ajax请求权限不足的解决方案，增加全局异常UnauthorizedException、AuthorizationException的处理  
5. Shiro thymeleaf的标签封装  
6. 用户密码加密传输  
7. 验证码校验  
8. 记住我功能  
9. 登录失败次数限制(redis)
10. 在线用户列表显示，强制下线功能  
11. 提供用户-角色-资源-权限的维护功能  
 

