package com.stanley.uams.service;

import com.stanley.common.domain.NavigateMenu;
import com.stanley.common.spring.BaseService;
import com.stanley.uams.domain.auth.SysMenu;
import com.stanley.uams.mapper.master.auth.SysMenuMapper;
import com.stanley.utils.Constants;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    /**
     * @Description 获取当前用户有权限的菜单
     * @date 2017/8/31
     * @author 13346450@qq.com 童晟
     * @param
     * @return
     */
    public List<NavigateMenu> listMenuAll(Integer rootId) {
        List<SysMenu> list = listTreeMenu(getUserRoleIds(),rootId);
        List<NavigateMenu> menuList = new ArrayList<NavigateMenu>();
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
}
