package com.stanley.uams.web.basic;

import com.stanley.common.annotation.WriteLogs;
import com.stanley.common.domain.SearchParam;
import com.stanley.common.domain.mybatis.Page;
import com.stanley.common.spring.BaseController;
import com.stanley.uams.domain.basic.SysOrganization;
import com.stanley.uams.domain.basic.SysOrganizationVO;
import com.stanley.uams.service.basic.SysDictService;
import com.stanley.uams.service.basic.SysOrganizationService;
import com.stanley.utils.Constants;
import com.stanley.utils.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 组织机构管理
 * @Description
 * @date 2016-04-07
 * @since 1.0 
 * @version 1.0  
 * @author 13346450@qq.com 童晟
 */
@RestController
@RequestMapping("/system/organization/*")
public class SysOrganizationController extends BaseController {

	@Resource
	private SysOrganizationService sysOrganizationService;

	/**
	 * 新增保存
	 * @param sysOrganization
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-07
	 */
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@RequiresPermissions("system:SysOrganization:insert")
	@WriteLogs(Constants.OPERITION_INSERT)
	public String insert(SysOrganization sysOrganization){
		return sysOrganizationService.insert(sysOrganization);
	}

	/**
	 * 删除单条
	 * @param idKey
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-07
	 */
	@RequestMapping(value="delete/{idKey}")
	@RequiresPermissions("system:SysOrganization:delete")
	@WriteLogs(Constants.OPERITION_DELETE)
	public String delete(@PathVariable Integer idKey){
		return sysOrganizationService.delete(idKey);
	}
	
	/**
	 * 批量删除
	* @author 13346450@qq.com 童晟
	 * @date 2016-04-11
	 */
	@RequestMapping(value="deleteBatch")
	@RequiresPermissions("system:SysOrganization:delete")
	@WriteLogs(Constants.OPERITION_DELETE_BATCH)
	public String deleteBatch(SearchParam searchParam){
		return sysOrganizationService.deleteBatch(searchParam.getCheckedIds());
	}

	/**
	 * 修改保存
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-07
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@RequiresPermissions("system:SysOrganization:update")
	@WriteLogs(Constants.OPERITION_UPDATE)
	public String update(SysOrganization sysOrganization){
		return sysOrganizationService.update(sysOrganization);
	}
	
	/**
	 * 分页查询
	 * @param searchParam
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-07
	 */
	@RequestMapping(value = "listPage")
	@RequiresPermissions("system:SysOrganization:select")
	public Page<SysOrganizationVO> listPage(SearchParam searchParam){
		return sysOrganizationService.selectPage(searchParam);
	}

	/**
	 * 导出excel
	 * @param response
	 * @param searchParam
	 * @author 13346450@qq.com 童晟 
	 * @date 2016年4月14日
	 */
	@RequestMapping(value = "toExcel")
	@RequiresPermissions("system:SysOrganization:select")
	public void toExcel(HttpServletResponse response, SearchParam searchParam){
		ExcelUtil.outputExcel(response, "组织机构数据", sysOrganizationService.toExcel(searchParam));
	}
	
	/**
	 * 查询所有机构(非分站)
	 * return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-11
	 */
	@RequestMapping(value = "listAll")
	public List<SysOrganization> listAll(){
		return sysOrganizationService.selectAllBySelective(null);
	}

}
