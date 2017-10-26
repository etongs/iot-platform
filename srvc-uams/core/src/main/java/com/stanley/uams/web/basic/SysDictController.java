package com.stanley.uams.web.basic;

import com.stanley.common.annotation.WriteLogs;
import com.stanley.common.domain.SearchParam;
import com.stanley.common.domain.mybatis.Page;
import com.stanley.common.spring.BaseController;
import com.stanley.uams.domain.basic.SysDict;
import com.stanley.uams.domain.basic.SysDictVO;
import com.stanley.uams.service.basic.SysDictService;
import com.stanley.utils.Constants;
import com.stanley.utils.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 数据字典管理
 * @Description
 * @date 2016-04-18
 * @since 1.0 
 * @version 1.0  
 * @author 13346450@qq.com 童晟
 */
@RestController
@RequestMapping("/system/dict/*")
public class SysDictController extends BaseController {

	@Resource
	private SysDictService sysDictService;

	/**
	 * 新增保存
	 * @param sysDict
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-18
	 */
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@RequiresPermissions("system:SysDict:insert")
	@WriteLogs(Constants.OPERITION_INSERT)
	public String insert(SysDict sysDict){
		return sysDictService.insert(sysDict);
	}

	/**
	 * 删除单条
	 * @param idKey
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-18
	 */
	@RequestMapping(value="delete/{idKey}")
	@RequiresPermissions("system:SysDict:delete")
	@WriteLogs(Constants.OPERITION_DELETE)
	public String delete(@PathVariable Integer idKey){
		return sysDictService.delete(idKey);
	}

	/**
	 * 批量删除
	* @author 13346450@qq.com 童晟
	 * @date 2016-04-18
	 */
	@RequestMapping(value="deleteBatch")
	@RequiresPermissions("system:SysDict:delete")
	@WriteLogs(Constants.OPERITION_DELETE_BATCH)
	public String deleteBatch(SearchParam searchParam){
		return sysDictService.deleteBatch(searchParam.getCheckedIds());
	}

	/**
	 * 修改保存
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-18
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@RequiresPermissions("system:SysDict:update")
	@WriteLogs(Constants.OPERITION_UPDATE)
	public String update(SysDict sysDict){
		return sysDictService.update(sysDict);
	}

	/**
	 * 分页查询
	 * @param searchParam
	 * @return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-18
	 */
	@RequestMapping(value = "listPage/{dictTypeId}")
	@RequiresPermissions("system:SysDict:select")
	public Page<SysDictVO> listPage(@PathVariable String dictTypeId, SearchParam searchParam){
		return sysDictService.selectPage(dictTypeId,searchParam);
	}

	/**
	 * 导出excel
	 * @param response
	 * @param searchParam
	 * return
	 * @author 13346450@qq.com 童晟
	 * @date 2016-04-18
	 */
	@RequestMapping(value = "toExcel")
	@RequiresPermissions("system:SysDict:select")
	public void toExcel(HttpServletResponse response, SearchParam searchParam){
		ExcelUtil.outputExcel(response, "数据字典数据", sysDictService.toExcel(searchParam));
	}

	/**
	 * 列出字典类别
	 * @Description
	 * @date 2017年5月5日
	 * @author 童晟  13346450@qq.com
	 * @param @return
	 * @return List<Map<String,Object>>
	 */
	@RequestMapping(value = "listDictType")
	@RequiresPermissions("system:SysDict:select")
	public List<Map<String, Object>> listDictType(){
		return sysDictService.listDictType();
	}
	
}
