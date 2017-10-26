package com.stanley.uams.web.basic;


import com.stanley.common.annotation.WriteLogs;
import com.stanley.common.domain.SearchParam;
import com.stanley.common.domain.mybatis.Page;
import com.stanley.common.spring.BaseController;
import com.stanley.uams.domain.basic.SysInterface;
import com.stanley.uams.domain.basic.SysInterfaceVO;
import com.stanley.uams.service.basic.SysInterfaceService;
import com.stanley.utils.Constants;
import com.stanley.utils.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 外部接口配置管理
 * @Description
 * @date 2016-10-19
 * @since 1.0 
 * @version 1.0  
 * @author 13346450@qq.com 童晟
 */
@RestController
@RequestMapping("/system/interface/*")
public class SysInterfaceController extends BaseController {

	@Resource
	private SysInterfaceService sysInterfaceService;

	/**
	 * 新增保存
	 * @param sysInterface
	 * @author ts
	 * @date 2016-10-19
	 */
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@RequiresPermissions("system:SysInterface:insert")
	@WriteLogs(Constants.OPERITION_INSERT)
	public String insert(SysInterface sysInterface){
		return sysInterfaceService.insert(sysInterface);
	}

	/**
	 * 删除单条
	 * @param idKey
	 * @author ts
	 * @date 2016-10-19
	 */
	@RequestMapping(value="delete/{idKey}")
	@RequiresPermissions("system:SysInterface:delete")
	@WriteLogs(Constants.OPERITION_DELETE)
	public String delete(@PathVariable Integer idKey){
		return sysInterfaceService.delete(idKey);
	}

	/**
	 * 批量删除
	 * @author ts
	 * @date 2016-10-19
	 */
	@RequestMapping(value="deleteBatch")
	@RequiresPermissions("system:SysInterface:delete")
	@WriteLogs(Constants.OPERITION_DELETE_BATCH)
	public String deleteBatch(SearchParam searchParam){
		return sysInterfaceService.deleteBatch(searchParam.getCheckedIds());
	}

	/**
	 * 修改保存
	 * @author ts
	 * @date 2016-10-19
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@RequiresPermissions("system:SysInterface:update")
	@WriteLogs(Constants.OPERITION_UPDATE)
	public String update(SysInterface sysInterface){
		return sysInterfaceService.update(sysInterface);
	}

	/**
	 * 分页查询
	 * @param searchParam
	 * @return
	 * @author ts
	 * @date 2016-10-19
	 */
	@RequestMapping(value = "listPage")
	@RequiresPermissions("system:SysInterface:select")
	public Page<SysInterfaceVO> listPage(SearchParam searchParam){
		return sysInterfaceService.selectPage( searchParam);
	}

	/**
	 * 导出excel
	 * @param response
	 * @param searchParam
	 * return
	 * @author ts
	 * @date 2016-10-19
	 */
	@RequestMapping(value = "toExcel")
	@RequiresPermissions("system:SysInterface:select")
	public void toExcel(HttpServletResponse response, SearchParam searchParam){
		ExcelUtil.outputExcel(response, "外部接口配置数据", sysInterfaceService.toExcel(searchParam));
	}

	/**
	 * 运行接口
	 * @param searchParam
	 * @author ts
	 * @date 2016年10月20日
	 */
	@RequestMapping(value = "runInterface")
	@RequiresPermissions("system:SysInterface:implement")
	public String  runInterface(SearchParam searchParam){
		return sysInterfaceService.runInterface(searchParam.getCheckedIds());
	}
	
	
	/**
	 * 批量改变接口任务状态
	 * @param searchParam
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-15
	 */
	@RequestMapping(value="isStartInterfaceStatus")
	@RequiresPermissions("system:SysInterface:implement")
	public String isStartInterfaceStatus(SearchParam searchParam){
		return sysInterfaceService.isStartInterfaceStatus(searchParam.getCheckedIds(),searchParam.isEnabled());
	}

	/**
	 * 获取最大排序码
	 * @author lcw
	 * @date 2016年11月24日
	 */
	@RequestMapping(value = "getNextSeqNo", method = RequestMethod.GET)
	@RequiresPermissions("system:SysInterface:select")
	public String getNextSeqNo(){
		return null;//sysInterfaceService.getNextSeqNo();
	}
	

}
