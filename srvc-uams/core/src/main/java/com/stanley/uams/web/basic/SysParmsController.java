package com.stanley.uams.web.basic;

import com.stanley.common.annotation.WriteLogs;
import com.stanley.common.domain.SearchParam;
import com.stanley.common.domain.mybatis.Page;
import com.stanley.common.spring.BaseController;
import com.stanley.uams.domain.basic.SysParms;
import com.stanley.uams.domain.basic.SysParmsVO;
import com.stanley.uams.service.basic.SysParmsService;
import com.stanley.utils.Constants;
import com.stanley.utils.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 系统参数管理
 * @Description
 * @date 2016-08-09
 * @since 1.0 
 * @version 1.0  
 * @author 13346450@qq.com 童晟
 */
@RestController
@RequestMapping("/system/parms/*")
public class SysParmsController extends BaseController {

	@Resource
	private SysParmsService sysParmsService;

	/**
	 * 新增保存
	 * @param sysParms
	 * @author 13346450@qq.com 童晟
	 * @date 2016-08-09
	 */
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@RequiresPermissions("system:SysParms:insert")
	@WriteLogs(Constants.OPERITION_INSERT)
	public String insert(SysParms sysParms){
		return sysParmsService.insert(sysParms);
	}
	
	/**
	 * 新增保存
	 * @author 13346450@qq.com 童晟
	 * @date 2016-08-09
	 */
	@RequestMapping(value = "synchronized", method = RequestMethod.POST)
	@RequiresPermissions("system:SysParms:insert")
	@WriteLogs(Constants.OPERITION_INSERT)
	public String save(String insertDatagrid, String updateDatagrid, String deleteDatagrid){
		return sysParmsService.saveAll(insertDatagrid,updateDatagrid,deleteDatagrid);
	}

	/**
	 * 删除单条
	 * @param idKey
	 * @author 13346450@qq.com 童晟
	 * @date 2016-08-09
	 */
	@RequestMapping(value="delete/{idKey}")
	@RequiresPermissions("system:SysParms:delete")
	@WriteLogs(Constants.OPERITION_DELETE)
	public String delete(@PathVariable Integer idKey){
		return sysParmsService.delete(idKey);
	}

	/**
	 * 批量删除
	* @author 13346450@qq.com 童晟
	 * @date 2016-08-09
	 */
	@RequestMapping(value="deleteBatch")
	@RequiresPermissions("system:SysParms:delete")
	@WriteLogs(Constants.OPERITION_DELETE_BATCH)
	public String deleteBatch(SearchParam searchParam){
		return sysParmsService.deleteBatch(searchParam.getCheckedIds());
	}

	/**
	 * 修改保存
	 * @author 13346450@qq.com 童晟
	 * @date 2016-08-09
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@RequiresPermissions("system:SysParms:update")
	@WriteLogs(Constants.OPERITION_UPDATE)
	public String update(SysParms sysParms){
		return sysParmsService.update(sysParms);
	}

	/**
	 * 分页查询
	 * @param searchParam
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-08-09
	 */
	@RequestMapping(value = "listPage")
	@RequiresPermissions("system:SysParms:select")
	public Page<SysParmsVO> listPage(SearchParam searchParam){
		return sysParmsService.selectPage( searchParam);
	}
	
	/**
	 * 查所有
	 * @return
	 * @date 2016-08-09
	 */
	@RequestMapping(value = "chooseAll")
	@RequiresPermissions("system:SysParms:select")
	public List<SysParms> listAll(SearchParam searchParam) {
		return sysParmsService.selectAllBySelective(null);
	}

	/**
	 * 导出excel
	 * @param response
	 * @param searchParam
	 * return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-08-09
	 */
	@RequestMapping(value = "toExcel")
	@RequiresPermissions("system:SysParms:select")
	public void toExcel(HttpServletResponse response, SearchParam searchParam){
		ExcelUtil.outputExcel(response, "系统参数数据", sysParmsService.toExcel(searchParam));
	}

	/**
	 * 查询所有角色
	 * return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-08-09
	 */
	@RequestMapping(value = "listAll")
	@RequiresPermissions("system:SysParms:select")
	public List<SysParms> listAll(){
		return sysParmsService.selectAllBySelective(null);
	}

}
