package com.stanley.uams.api;

/**
 * 权限服务对外的静态常量
 *
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/8/4
 **/
public interface UamsUrl {

    /**
     * 服务主机名
     */
    String SERVICE_HOSTNAME = "http://UAMS";
    /**
     * 获取用户权限列表
     */
    String AUTHORITY_URL = "/getAuthority";
    /**
     * 获取用户信息
     */
    String USER_INFO_URL = "/getUserInfo";

    static String buildUrl(String url){
        return SERVICE_HOSTNAME + url;
    }

}
