package com.stanley.uams.service.auth;

import com.stanley.common.domain.SearchParam;
import com.stanley.common.domain.mybatis.Page;
import com.stanley.common.spring.BaseService;
import com.stanley.uams.domain.auth.SysRole;
import com.stanley.uams.domain.auth.SysRoleVO;
import com.stanley.uams.mapper.master.auth.SysRoleMapper;
import com.stanley.utils.DateTimeUtil;
import com.stanley.utils.ResultBuilderUtil;
import com.stanley.utils.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;

/**
 * 角色管理
 * @Description
 * @date 2016-03-31
 * @since 1.0 
 * @version 1.0  
 * @author 13346450@qq.com 童晟
 */
@Service
public class SysRoleService extends BaseService {
	private final static String FUNC_MENU ="角色表";

	@Resource
	private SysRoleMapper sysRoleMapper;

	@Transactional
	public String insert(SysRole sysRole) {
		sysRole.setCreateId(getUserId());
		sysRole.setCreateDt(new Timestamp(System.currentTimeMillis()));
		sysRoleMapper.insert(sysRole);
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_CREATE, "idKey:" +sysRole.getIdKey());
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	@Transactional
	public String delete(Integer idKey) {
		if (null == idKey) {
			return ResultBuilderUtil.resultException("2","id不能为空");
		}
		sysRoleMapper.deleteByPrimaryKey(idKey);
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_DELETE, "idKey:" + idKey);
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	@Transactional
	public String deleteBatch(String dataIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataListID", Arrays.asList(dataIds.split(",")));
		sysRoleMapper.deleteBatch(map);
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_DELETE, "ids:" + dataIds);
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	@Transactional
	public String update(SysRole sysRole) {
		sysRole.setCreateId(getUserId());
		sysRole.setCreateDt(new Timestamp(System.currentTimeMillis()));
		sysRoleMapper.updateByPrimaryKeySelective(sysRole);
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_UPDATE, "idKey:" +sysRole.getIdKey());
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	public SysRole selectByIdkey(Integer idKey) {
		return sysRoleMapper.selectByPrimaryKey(idKey);
	}

	public Page<SysRoleVO> selectPage(SearchParam searchParam) {
		Page<SysRoleVO> page = new Page<SysRoleVO>();
		page.setOffset(searchParam.getOffset());
		page.setLimit(searchParam.getLimit());
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("roleName", searchParam.getSearchName());
		map.put("roleMarker", searchParam.getSearchName2());
		map.put("sort", searchParam.getSort());
		map.put("order", searchParam.getOrder());
		page.setParams(map);
		page.setRows(sysRoleMapper.selectPage(page));
		return page;
	}

	public HSSFWorkbook toExcel(SearchParam searchParam) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("roleName", searchParam.getSearchName());
		map.put("roleMarker", searchParam.getSearchName2());
		List<SysRoleVO> list = sysRoleMapper.toExcel(map);
		String[] excelHeader = {"角色名", "角色标识", "备注", "创建人", "创建时间"};
		HSSFWorkbook wb = new HSSFWorkbook();
		//单元格列宽
		int[] excelHeaderWidth = {150, 150, 300, 120, 145};
		HSSFSheet sheet = wb.createSheet("角色查询");
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
		// 设置列宽度（像素）
		for (int i = 0; i < excelHeaderWidth.length; i++) {
			sheet.setColumnWidth(i, 32 * excelHeaderWidth[i]);
		}
		// 添加表格头
		for (int i = 0; i < excelHeader.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(excelHeader[i]);
			cell.setCellStyle(style);
		}
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow(i + 1);
			SysRoleVO role = list.get(i);
			row.createCell(0).setCellValue(StringUtils.null2Blank(role.getRoleName()));
			row.createCell(1).setCellValue(StringUtils.null2Blank(role.getRoleMarker()));
			row.createCell(2).setCellValue(StringUtils.null2Blank(role.getRoleRemark()));
			row.createCell(3).setCellValue(StringUtils.null2Blank(role.getCreateName()));
			row.createCell(4).setCellValue(null==role.getCreateDt()?
					"": DateTimeUtil.getDateToStrFullFormat(role.getCreateDt()));
		}
		return wb;
	}

	public SysRole selectOneBySelective(SearchParam searchParam) {
		SysRole sysRole = new SysRole();
		return sysRoleMapper.selectOneBySelective(sysRole);
	}

	public List<SysRole> selectAllBySelective(SearchParam searchParam) {
		SysRole sysRole = new SysRole();
		return sysRoleMapper.selectAllBySelective(sysRole);
	}

	public String validate(SysRole sysRole) {
		String result = "";
		if(null == sysRole.getIdKey()){//新增时
			result = (null == sysRoleMapper.selectOneBySelective(sysRole)) ?
					ResultBuilderUtil.VALIDATE_SUCCESS: ResultBuilderUtil.VALIDATE_ERROR;
		}else{//修改时
			result = (null == sysRoleMapper.checkExistRolename(sysRole)) ?
					ResultBuilderUtil.VALIDATE_SUCCESS: ResultBuilderUtil.VALIDATE_ERROR;
		}
		return result;
	}
}
