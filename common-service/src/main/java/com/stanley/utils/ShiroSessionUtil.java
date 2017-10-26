package com.stanley.utils;

import com.stanley.common.domain.UserInfoBean;
import org.apache.shiro.SecurityUtils;

/**
 * shiro获取session的工具类
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/10/17
 **/
public class ShiroSessionUtil {

    /**
     * 获取当前用户idkey
     * @return
     * @author 13346450@qq.com 童晟
     * @date 2016年3月31日
     */
    public static Integer getUserId(){
        UserInfoBean userInfoBean = getCurrentUserInfo();
        return (null != userInfoBean)? userInfoBean.getUserId() : null;
    }
    /**
     * 获取当前用户名称
     * @return
     * @author 13346450@qq.com 童晟
     * @date 2016年3月31日
     */
    public static String getUserName(){
        UserInfoBean userInfoBean = getCurrentUserInfo();
        return (null != userInfoBean)? userInfoBean.getNameCn() : null;
    }
    /**
     * 获取当前用户帐号
     * @return
     * @author 13346450@qq.com 童晟
     * @date 2016年3月31日
     */
    public static String getUserAccount(){
        UserInfoBean userInfoBean = getCurrentUserInfo();
        return (null != userInfoBean)? userInfoBean.getAccount() : null;
    }

    /**
     * @Description 获取当前用户角色(多个)
     * @date 2017/8/31
     * @author 13346450@qq.com 童晟
     * @param
     * @return
     */
    public static String getUserRoleIds(){
        UserInfoBean userInfoBean = getCurrentUserInfo();
        return (null != userInfoBean)? userInfoBean.getRoleIds() : null;
    }

    /**
     * @Description 获取当前用户bean
     * @date 2017/8/31
     * @author 13346450@qq.com 童晟
     * @param
     * @return
     */
    public static UserInfoBean getCurrentUserInfo(){
        return (UserInfoBean) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
    }
}
