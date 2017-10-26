package com.stanley.uams.web.auth;

import com.stanley.common.annotation.WriteLogs;
import com.stanley.common.domain.ZtreeModel;
import com.stanley.common.spring.BaseController;
import com.stanley.uams.domain.auth.SysResource;
import com.stanley.uams.service.auth.SysResourceService;
import com.stanley.utils.Constants;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 资源表 树形结构管理
 * @Description
 * @date 2016-04-13
 * @since 1.0 
 * @version 1.0  
 * @author 13346450@qq.com 童晟
 */
@RestController
@RequestMapping("/system/resource/*")
public class SysResourceController extends BaseController {

	@Resource
	private SysResourceService sysResourceService;

	/**
	 * 新增保存
	 * @param sysResource
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-13
	 */
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@RequiresPermissions("system:SysResource:insert")
	@WriteLogs(Constants.OPERITION_INSERT)
	public String insert(SysResource sysResource){
		return sysResourceService.insert(sysResource);
	}

	/**
	 * 删除单条
	 * @param idKey
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-13
	 */
	@RequestMapping(value="delete/{idKey}")
	@RequiresPermissions("system:SysResource:delete")
	@WriteLogs(Constants.OPERITION_DELETE)
	public String delete(@PathVariable Integer idKey){
		return sysResourceService.delete(idKey);
	}

	/**
	 * 修改保存
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-13
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@RequiresPermissions("system:SysResource:update")
	@WriteLogs(Constants.OPERITION_UPDATE)
	public String update(SysResource sysResource){
		return sysResourceService.update(sysResource);
	}

	/**
	 * 节点拖拽后的保存
	 * @param sourceId
	 * @param parentId
	 * @param targetId
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-13
	 */
	@RequestMapping(value = "saveDragAndDrop")
	@RequiresPermissions("system:SysResource:update")
	@WriteLogs(Constants.OPERITION_TREE_DRAG)
	public String saveDragAndDrop(@RequestParam Integer sourceId,
			@RequestParam Integer parentId,	@RequestParam Integer targetId) {
		return sysResourceService.updateParentIdByIdKey(sourceId, parentId, targetId);
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
	@RequiresPermissions("system:SysResource:select")
	public List<ZtreeModel> loadChildrenNodes(Integer id) {
		return sysResourceService.loadChildrenNodes(id);
	}

	/**
	 * 加载一条数据，并返回json而不是jsp页面
	 * @Description
	 * @date 2017年4月1日
	 * @author 童晟  13346450@qq.com
	 * @param @param response
	 * @param @param sysMenu
	 * @return void
	 */
	@RequestMapping(value = "loadOne/{idKey}")
	@RequiresPermissions("system:SysResource:select")
	public SysResource loadOne(@PathVariable Integer idKey){
		return sysResourceService.selectByIdkey(idKey);
	}

}
