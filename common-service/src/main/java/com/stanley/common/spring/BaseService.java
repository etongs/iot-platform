package com.stanley.common.spring;

import com.stanley.common.domain.UserInfoBean;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 公共的服务层基类
 *
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/8/25
 **/
public abstract class BaseService {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取当前用户idkey
     * @return
     * @author 13346450@qq.com 童晟
     * @date 2016年3月31日
     */
    protected Integer getUserId(){
        UserInfoBean userInfoBean = getCurrentUserInfo();
        return (null != userInfoBean)? userInfoBean.getUserId() : null;
    }
    /**
     * 获取当前用户名称
     * @return
     * @author 13346450@qq.com 童晟
     * @date 2016年3月31日
     */
    protected String getUserName(){
        UserInfoBean userInfoBean = getCurrentUserInfo();
        return (null != userInfoBean)? userInfoBean.getNameCn() : null;
    }
    /**
     * 获取当前用户帐号
     * @return
     * @author 13346450@qq.com 童晟
     * @date 2016年3月31日
     */
    protected String getUserAccount(){
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
    protected String getUserRoleIds(){
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
    protected UserInfoBean getCurrentUserInfo(){
        return (UserInfoBean) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
    }

}
