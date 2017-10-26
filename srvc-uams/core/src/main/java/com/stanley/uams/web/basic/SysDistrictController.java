package com.stanley.uams.web.basic;

import com.stanley.common.annotation.WriteLogs;
import com.stanley.common.domain.ZtreeModel;
import com.stanley.common.spring.BaseController;
import com.stanley.uams.domain.basic.SysDistrict;
import com.stanley.uams.service.basic.SysDistrictService;
import com.stanley.utils.Constants;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 行政区域管理
 * @Description
 * @date 2016-04-13
 * @since 1.0 
 * @version 1.0  
 * @author 13346450@qq.com 童晟
 */
@RestController
@RequestMapping("/system/district/*")
public class SysDistrictController extends BaseController {

	@Resource
	private SysDistrictService sysDistrictService;

	/**
	 * 新增保存
	 * @param sysDistrict
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-13
	 */
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@RequiresPermissions("system:SysDistrict:insert")
	@WriteLogs(Constants.OPERITION_INSERT)
	public String insert(SysDistrict sysDistrict){
		return sysDistrictService.insert(sysDistrict);
	}

	/**
	 * 删除单条
	 * @param idKey
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-13
	 */
	@RequestMapping(value="delete/{idKey}")
	@RequiresPermissions("system:SysDistrict:delete")
	@WriteLogs(Constants.OPERITION_DELETE)
	public String delete(@PathVariable Integer idKey){
		return sysDistrictService.delete(idKey);
	}

	/**
	 * 修改保存
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-13
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@RequiresPermissions("system:SysDistrict:update")
	@WriteLogs(Constants.OPERITION_UPDATE)
	public String update(SysDistrict sysDistrict){
		return sysDistrictService.update(sysDistrict);
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
	@RequiresPermissions("system:SysDistrict:update")
	@WriteLogs(Constants.OPERITION_TREE_DRAG)
	public String saveDragAndDrop(@RequestParam Integer sourceId,
			@RequestParam Integer parentId,	@RequestParam Integer targetId) {
		return sysDistrictService.updateParentIdByIdKey(sourceId, parentId, targetId);
	}

	/**
	 * 根据父节点查找子节点
	 * @param idKey
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-13
	 */
	@RequestMapping(value = "selectChildrenByParentId/{idKey}")
	@RequiresPermissions("system:SysDistrict:select")
	public List<SysDistrict> selectChildrenByParentId(@PathVariable Integer idKey) {
		return sysDistrictService.selectChildrenByParentId(idKey);
	}
	
	/**
	 * 根据主键查找记录
	 * @param idKey
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-13
	 */
	@RequestMapping(value = "selectByIdKey/{idKey}")
	@RequiresPermissions("system:SysDistrict:select")
	public SysDistrict selectByidKey(@PathVariable Integer idKey) {
		return sysDistrictService.selectByIdkey(idKey);
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
	@RequiresPermissions("system:SysDistrict:select")
	public List<ZtreeModel> loadChildrenNodes(Integer id) {
		return sysDistrictService.loadChildrenNodes(id);
	}

	/**
	 * 加载一条数据
	 * @Description
	 * @date 2017年4月1日
	 * @author 童晟  13346450@qq.com
	 * @param @param response
	 * @param @param sysMenu
	 * @return void
	 */
	@RequestMapping(value = "loadOne/{idKey}")
	@RequiresPermissions("system:SysDistrict:select")
	public SysDistrict loadOne(@PathVariable Integer idKey){
		return sysDistrictService.selectByIdkey(idKey);
	}
	
}
