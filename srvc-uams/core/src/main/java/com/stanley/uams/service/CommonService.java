package com.stanley.uams.service;

import com.stanley.common.domain.NavigateMenu;
import com.stanley.common.spring.BaseService;
import com.stanley.uams.domain.auth.SysMenu;
import com.stanley.uams.domain.auth.SysUser;
import com.stanley.uams.mapper.master.auth.SysMenuMapper;
import com.stanley.uams.shiro.MyShiroRealm;
import com.stanley.uams.shiro.SessionRedisDAO;
import com.stanley.utils.Constants;
import com.stanley.utils.ShiroSessionUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 权限系统公共服务层
 *
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/9/7
 **/
@Service
public class CommonService extends BaseService {

    @Resource
    private SysMenuMapper sysMenuMapper;
    @Resource
    private SessionRedisDAO sessionRedisDAO;

    /**
     * @Description 获取当前用户有权限的菜单
     * @date 2017/8/31
     * @author 13346450@qq.com 童晟
     * @param
     * @return
     */
    public List<NavigateMenu> listMenuAll(Integer rootId) {
        List<SysMenu> list = listTreeMenu(ShiroSessionUtil.getUserRoleIds(),rootId);
        List<NavigateMenu> menuList = new ArrayList<>();
        if(list.isEmpty())
            return menuList;

        if(rootId == Constants.TREE_ROOT_ID) {//跟节点需添加首页
            NavigateMenu index = new NavigateMenu();
            index.setId("0");
            index.setIcon("icon-cog");
            index.setText("主页");
            index.setUrl("");
            NavigateMenu indexChild = new NavigateMenu();
            indexChild.setId("00");
            indexChild.setIcon("icon-home");
            indexChild.setText("主页");
            indexChild.setUrl("mainframe/mainIndex_html");
            indexChild.setClose(false);
            index.setMenus(new ArrayList<NavigateMenu>(){ { add(indexChild); } });
            menuList.add(index);
        }
        for(SysMenu sysMenu : list) {
            NavigateMenu nMenu = new NavigateMenu();
            nMenu.setId(String.valueOf(sysMenu.getIdKey()));
            nMenu.setIcon(sysMenu.getIconAddress());
            nMenu.setText(sysMenu.getCdNm());
            nMenu.setUrl(sysMenu.getLinkAddress());
            List<NavigateMenu> children = listMenuAll(sysMenu.getIdKey());
            if(!children.isEmpty())
                nMenu.setMenus(children);

            menuList.add(nMenu);
        }
        return menuList;
    }

    private List<SysMenu> listTreeMenu(String roleIds, Integer nodeId){
        List<SysMenu> destList = new ArrayList<SysMenu>();
        String[] roleArray = roleIds.split(",");
        for(String roleId : roleArray){
            List<SysMenu> list = sysMenuMapper.getPermissionTreeMenus(nodeId,Integer.valueOf(roleId));
            if (!list.isEmpty()){
                for(SysMenu sysMenu : list){
                    if(!destList.contains(sysMenu)){
                        destList.add(sysMenu);
                    }
                }
            }
        }
        return destList;
    }

    /**
     * @Description 清除shiro的权限信息缓存redis
     * @date 2017/10/11
     * @author 13346450@qq.com 童晟
     * @param
     * @return
     */
    public void clearShiroCachedAuthorizationInfo(List<Integer> userIds){
        //获取所有活跃的session
        Collection<Session> sessions = sessionRedisDAO.getActiveSessions();
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
                    //比较用户ID，符合即加入集合
                    if (userIds.contains(user.getIdKey())) {
                        list.add(spc);
                        log.debug("需要移除权限缓存的用户帐号：{},{}",user.getAccount(),user.getNameCn());
                    }
                }
            }
        });
        if(list.isEmpty())
            return;
        RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        MyShiroRealm realm = (MyShiroRealm) securityManager.getRealms().iterator().next();
        realm.clearCachedAuthorizationInfo(list);
    }


}
