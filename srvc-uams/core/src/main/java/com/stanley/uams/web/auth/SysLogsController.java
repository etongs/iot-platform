package com.stanley.uams.web.auth;

import com.stanley.common.domain.SearchParam;
import com.stanley.common.domain.mybatis.Page;
import com.stanley.common.spring.BaseController;
import com.stanley.uams.domain.auth.SysLogsVO;
import com.stanley.uams.service.auth.SysLogsService;
import com.stanley.utils.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统日志管理
 * @Description
 * @date 2016-04-13
 * @since 1.0 
 * @version 1.0  
 * @author 13346450@qq.com 童晟
 */
@RestController
@RequestMapping("/system/logs/*")
public class SysLogsController extends BaseController {

	@Resource
	private SysLogsService sysLogsService;

	/**
	 * 删除单条
	 * @param
	 * @param idKey
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-13
	 */
	@RequestMapping(value="delete/{idKey}")
	@RequiresPermissions("system:SysLogs:delete")
	public String delete(@PathVariable Integer idKey){
		return sysLogsService.delete(idKey);
	}

	/**
	 * 批量删除
	 * @param response
	* @author 13346450@qq.com 童晟
	 * @date 2016-04-13
	 */
	@RequestMapping(value="deleteBatch")
	@RequiresPermissions("system:SysLogs:delete")
	public String deleteBatch(HttpServletResponse response, SearchParam searchParam){
		return sysLogsService.deleteBatch(searchParam.getCheckedIds());
	}

	/**
	 * 分页查询
	 * @param searchParam
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-13
	 */
	@RequestMapping(value = "listPage")
	@RequiresPermissions("system:SysLogs:select")
	public Page<SysLogsVO> listPage(SearchParam searchParam){
		return sysLogsService.selectPage( searchParam);
	}

	/**
	 * 导出excel
	 * @param response
	 * @param searchParam
	 * return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-13
	 */
	@RequestMapping(value = "toExcel")
	@RequiresPermissions("system:SysLogs:select")
	public void toExcel(HttpServletResponse response, SearchParam searchParam){
		ExcelUtil.outputExcel(response, "系统日志数据", sysLogsService.toExcel(searchParam));
	}

}
