package com.stanley.uams.web.auth;

import com.stanley.common.annotation.WriteLogs;
import com.stanley.common.domain.ZtreeModel;
import com.stanley.common.spring.BaseController;
import com.stanley.uams.domain.auth.SysMenu;
import com.stanley.uams.service.auth.SysMenuService;
import com.stanley.utils.Constants;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单管理
 * @Description
 * @date 2016-04-07
 * @since 1.0 
 * @version 1.0  
 * @author 13346450@qq.com 童晟
 */
@RestController
@RequestMapping("/system/menu/*")
public class SysMenuController extends BaseController {

	@Resource
	private SysMenuService sysMenuService;

	/**
	 * 新增保存
	 * @param sysMenu
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-07
	 */
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@RequiresPermissions("system:SysMenu:insert")
	@WriteLogs(Constants.OPERITION_INSERT)
	public String insert(SysMenu sysMenu){
		return sysMenuService.insert(sysMenu);
	}

	/**
	 * 删除单条
	 * @param idKey
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-07
	 */
	@RequestMapping(value="delete/{idKey}")
	@RequiresPermissions("system:SysMenu:delete")
	@WriteLogs(Constants.OPERITION_DELETE)
	public String delete(@PathVariable Integer idKey){
		return sysMenuService.delete(idKey);
	}

	/**
	 * 修改保存
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-07
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@RequiresPermissions("system:SysMenu:update")
	@WriteLogs(Constants.OPERITION_UPDATE)
	public String update(SysMenu sysMenu){
		return sysMenuService.update(sysMenu);
	}


	/**
	 * 节点拖拽后的保存
	 * @param sourceId
	 * @param parentId
	 * @param targetId
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-07
	 */
	@RequestMapping(value = "saveDragAndDrop")
	@RequiresPermissions("system:SysMenu:update")
	@WriteLogs(Constants.OPERITION_TREE_DRAG)
	public String  saveDragAndDrop(@RequestParam Integer sourceId,
			@RequestParam Integer parentId,	@RequestParam Integer targetId) {
		return sysMenuService.updateParentIdByIdKey(sourceId, parentId, targetId);
	}

	/**
	 * 根据给定的nodeId 异步加载下一级子节点（只一级）
	 * @Description
	 * @date 2017年3月30日
	 * @author 童晟  13346450@qq.com
	 * @param @param request
	 * @param @param nodeId
	 * @param @return
	 * @return ZtreeModel
	 */
	@RequestMapping(value = "loadChildrenNodes")
	@RequiresPermissions("system:SysMenu:select")
	public List<ZtreeModel> loadChildrenNodes(Integer id) {
		return sysMenuService.loadChildrenNodes(id);
	}

	/**
	 * 加载一条数据，并返回json而不是jsp页面
	 * @Description
	 * @date 2017年4月1日
	 * @author 童晟  13346450@qq.com
	 * @param @param sysMenu
	 * @return void
	 */
	@RequestMapping(value = "loadOne/{idKey}")
	@RequiresPermissions("system:SysMenu:select")
	public SysMenu loadOne(@PathVariable Integer idKey){
		return sysMenuService.selectByIdkey(idKey);
	}
	
	/**
	 * 检查是否已存在
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-08
	 */
	@RequestMapping(value = "validate", method = RequestMethod.GET)
	@RequiresPermissions("system:SysMenu:select")
	public String validate(SysMenu sysMenu){
		return sysMenuService.validate(sysMenu);
	}
	
}
