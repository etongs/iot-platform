package com.stanley.uams.service;

import com.stanley.common.domain.UserInfoBean;
import com.stanley.common.spring.BaseService;
import com.stanley.uams.domain.auth.SysUser;
import com.stanley.uams.domain.basic.SysOrganization;
import com.stanley.uams.service.auth.SysRoleService;
import com.stanley.uams.service.auth.SysUserService;
import com.stanley.uams.service.basic.SysOrganizationService;
import com.stanley.utils.ResultBuilderUtil;
import com.stanley.utils.StringUtils;
import com.stanley.utils.vcode.Captcha;
import com.stanley.utils.vcode.GifCaptcha;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

import static com.stanley.utils.Constants.SHIRO_LOGIN_COUNT;

/**
 * 登录后的主页服务层
 *
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/8/26
 **/
@Service
public class HomeService extends BaseService {

    @Resource(name = "redisTemplate")
    private StringRedisTemplate redisTemplate;
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysOrganizationService sysOrganizationService;
    @Resource
    private SysRoleService sysRoleService;
    @Value("${shiro.sessionTimeout}")
    private long sessionTimeout;

    /**
     * @Description 调用shiro验证，如果MyShiroRealm认证通过，则不会进入此方法
     * @date 2017/8/31
     * @author 13346450@qq.com 童晟
     * @param
     * @return
     */
    public String checkLogin(Model model,HttpServletRequest request, String username, String password, Boolean rememberMe){
        if (StringUtils.isNull(username) || StringUtils.isNull(password)) {
            model.addAttribute("msg", "您还未登录，请在此登录。");
            return "mainframe/login";
        }
        String captcha = WebUtils.getCleanParam(request, "captcha");
        if(StringUtils.isNull(captcha)){
            model.addAttribute("msg", "验证码不能为空。");
            return "mainframe/login";
        }
        if(!this.checkValidateCode(request,captcha).equals(ResultBuilderUtil.RESULT_SUCCESS)){
            model.addAttribute("msg", "验证码错误");
            return "mainframe/login";
        }
        //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
        //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
        //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
        }catch(UnknownAccountException uae){
            log.info("对用户[{}]进行登录验证，验证未通过，账户不存在",username);
            model.addAttribute("msg", "账户不存在，请重新输入");
        }catch(IncorrectCredentialsException ice){
            log.info("对用户[{}]进行登录验证...验证未通过，错误的凭证(密码)",username);
            model.addAttribute("msg", "密码错误，请重新输入");
        }catch(LockedAccountException lae){
            log.info("对用户[{}]进行登录验证...验证未通过，账户已锁定",username);
            model.addAttribute("msg", "账户已锁定");
        }catch(ExcessiveAttemptsException eae){
            log.info("对用户[{}]进行登录验证...验证未通过，错误次数过多",username);
            model.addAttribute("msg", "用户名或密码错误次数过多");
        }catch(DisabledAccountException dae){
            log.info("对用户[{}]进行登录验证...验证未通过，错误次数过多",username);
            model.addAttribute("msg", dae.getMessage());
        }catch(AuthenticationException ae){
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            log.info("对用户[{}]进行登录验证...验证未通过，堆栈轨迹如下",username);
            ae.printStackTrace();
            model.addAttribute("msg", "用户名或密码不正确");
        }
        //验证是否登录成功
        if(currentUser.isAuthenticated()){
            log.info("用户[" + username + "]登录认证通过");
            return "redirect:home";
        }else{
            token.clear();
            model.addAttribute("loginacc", username);
            return "mainframe/login";
        }
    }

    /**
     * 设置当前登录信息
     * 已登录成功后，设置session等
     * session失效时间在此处设置，实测有效
     * @param model
     * @return
     */
    public String home(Model model){
        SysUser sysUser = (SysUser)SecurityUtils.getSubject().getPrincipal();
        sysUserService.updateLastLoginTime(sysUser.getIdKey());
        redisTemplate.expire(SHIRO_LOGIN_COUNT + sysUser.getAccount(), 1, TimeUnit.SECONDS);
        UserInfoBean userInfoBean = this.generateUserInfo(sysUser);
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("userInfo", userInfoBean);
        session.setTimeout(sessionTimeout);
        model.addAttribute("userInfo",userInfoBean);
        return "mainframe/mainPage";
    }

    /**
     * @Description 调用shiro登出
     * @date 2017/8/31
     * @author 13346450@qq.com 童晟
     * @param
     * @return
     */
    public String logout(Model model){
        model.addAttribute("msg", "您已安全退出。");
        SecurityUtils.getSubject().logout();
        return "redirect:/";
    }

    /**
     * @Description 踢出登录
     * @date 2017/10/10
     * @author 13346450@qq.com 童晟
     * @param
     * @return
     */
    public String kickout(Model model){
        model.addAttribute("msg", "您的帐号已在另一地方登录，此登录被强制退出。");
        return "mainframe/kickout";
    }


    /**
     * @Description 获取验证码
     * @date 2017/9/25
     * @author 13346450@qq.com 童晟
     * @param
     * @return
     */
    public void getGifCode(HttpServletResponse response, HttpSession session){
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");
            Captcha captcha = new GifCaptcha(146,33,4);
            captcha.out(response.getOutputStream());
            session.setAttribute("valid_code",captcha.text().toLowerCase());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取验证码异常：{}",e.getMessage());
        }
    }

    /**
     * @Description 验证码校验
     * @date 2017/9/25
     * @author 13346450@qq.com 童晟
     * @param
     * @return
     */
    public String checkValidateCode(HttpServletRequest request, String validateCode) {
        /* 说明： comment by ts 2017-9-25
           由于shiro接管了http request/response/session对象（分别扩展了这三个接口），
           所以HttpSession等同于SecurityUtils.getSubject().getSession()，
           即httpSession对象就是shiro的session对象，两个为同一个引用
         */
        String sessionValidateCode = (String)request.getSession().getAttribute("valid_code");
        //本地开发时用固定码
        if( ( request.getRequestURL().substring(0,16).equalsIgnoreCase("http://localhost") ||
                request.getRequestURL().substring(0,16).equalsIgnoreCase("http://127.0.0.1") )
            && "xxxx".equals(validateCode))
            return ResultBuilderUtil.RESULT_SUCCESS;

        if(sessionValidateCode==null||(!sessionValidateCode.equalsIgnoreCase(validateCode)))
            return ResultBuilderUtil.resultException("2", "验证码错误");
        else
            return ResultBuilderUtil.RESULT_SUCCESS;
    }

    /**
     * @Description 设置当前用户的session信息
     * @date 2017/8/31
     * @author 13346450@qq.com 童晟
     * @param
     * @return
     */
    private UserInfoBean generateUserInfo(SysUser sysUser) {
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.setUserId(sysUser.getIdKey());
        userInfoBean.setAccount(sysUser.getAccount());
        userInfoBean.setOrgId(sysUser.getOrgId());
        userInfoBean.setNameCn(sysUser.getNameCn());
        userInfoBean.setIsModify(sysUser.getIsModify());
        SysOrganization org = sysOrganizationService.selectByIdkey(sysUser.getOrgId());
        if (null != org) {
            userInfoBean.setOrgName(org.getCdNm());
            userInfoBean.setProvinceId(org.getProvinceId());
            userInfoBean.setCityId(org.getCityId());
            userInfoBean.setDistrictId(org.getDistrictId());
        }
        userInfoBean.setRoleIds(sysUser.getRoleIds());
        userInfoBean.setRoleName(this.roleId2Name(sysUser.getRoleIds()));
        return userInfoBean;
    }

    /**
     * 取出角色名
     * @param roleIds
     * @return
     * @author 13346450@qq.com 童晟
     * @date 2016年5月20日
     */
    private String roleId2Name(String roleIds){
        String[] roleArray = roleIds.split(",");
        StringBuilder returnStr = new StringBuilder();
        for(String roleId : roleArray){
            returnStr.append(","+sysRoleService.selectByIdkey(Integer.parseInt(roleId)).getRoleName());
        }
        return returnStr.substring(1);
    }

}
