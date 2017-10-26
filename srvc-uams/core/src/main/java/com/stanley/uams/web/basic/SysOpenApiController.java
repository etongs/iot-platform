package com.stanley.uams.web.basic;

import com.stanley.common.annotation.WriteLogs;
import com.stanley.common.domain.SearchParam;
import com.stanley.common.domain.mybatis.Page;
import com.stanley.common.spring.BaseController;
import com.stanley.uams.domain.auth.SysUser;
import com.stanley.uams.domain.basic.SysOpenApi;
import com.stanley.uams.domain.basic.SysOpenApiVO;
import com.stanley.uams.service.basic.SysOpenApiService;
import com.stanley.utils.Constants;
import com.stanley.utils.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 开放接口接入管理
 * @Description
 * @date 2017-01-06
 * @since 1.0 
 * @version 1.0  
 * @author 13346450@qq.com 童晟
 */
@RestController
@RequestMapping("/system/openApi/*")
public class SysOpenApiController extends BaseController {

	@Resource
	private SysOpenApiService sysOpenApiService;

	/**
	 * 新增保存
	 * @param sysOpenApi
	 * @author 13346450@qq.com 童晟
	 * @date 2017-01-06
	 */
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@RequiresPermissions("system:SysOpenApi:insert")
	@WriteLogs(Constants.OPERITION_INSERT)
	public String insert(SysOpenApi sysOpenApi){
		return sysOpenApiService.insert(sysOpenApi);
	}

	/**
	 * 删除单条
	 * @param idKey
	 * @author 13346450@qq.com 童晟
	 * @date 2017-01-06
	 */
	@RequestMapping(value="delete/{idKey}")
	@RequiresPermissions("system:SysOpenApi:delete")
	@WriteLogs(Constants.OPERITION_DELETE)
	public String delete(@PathVariable Integer idKey){
		return sysOpenApiService.delete(idKey);
	}

	/**
	 * 批量删除
	* @author 13346450@qq.com 童晟
	 * @date 2017-01-06
	 */
	@RequestMapping(value="deleteBatch")
	@RequiresPermissions("system:SysOpenApi:delete")
	@WriteLogs(Constants.OPERITION_DELETE_BATCH)
	public String deleteBatch(SearchParam searchParam){
		return sysOpenApiService.deleteBatch(searchParam.getCheckedIds());
	}

	/**
	 * 修改保存
	 * @author 13346450@qq.com 童晟
	 * @date 2017-01-06
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@RequiresPermissions("system:SysOpenApi:update")
	@WriteLogs(Constants.OPERITION_UPDATE)
	public String update(SysOpenApi sysOpenApi){
		return sysOpenApiService.update(sysOpenApi);
	}

	/**
	 * 分页查询
	 * @param searchParam
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2017-01-06
	 */
	@RequestMapping(value = "listPage")
	@RequiresPermissions("system:SysOpenApi:select")
	public Page<SysOpenApiVO> listPage(SearchParam searchParam){
		return sysOpenApiService.selectPage( searchParam);
	}

	/**
	 * 导出excel
	 * @param response
	 * @param searchParam
	 * return
	 * @author 13346450@qq.com 童晟
	 * @date 2017-01-06
	 */
	@RequestMapping(value = "toExcel")
	@RequiresPermissions("system:SysOpenApi:select")
	public void toExcel(HttpServletResponse response, SearchParam searchParam){
		ExcelUtil.outputExcel(response, "开放接口接入数据", sysOpenApiService.toExcel(searchParam));
	}

	/**
	 * 检查用户标识是否已存在
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-08
	 */
	@RequestMapping(value = "validate", method = RequestMethod.GET)
	@RequiresPermissions("system:SysOpenApi:select")
	public String validate(SysOpenApi sysOpenApi){
		return sysOpenApiService.validate(sysOpenApi);
	}

}
