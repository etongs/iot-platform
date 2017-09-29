package com.stanley.uams.service.auth;


import com.stanley.common.domain.SearchParam;
import com.stanley.common.domain.mybatis.Page;
import com.stanley.common.spring.BaseService;
import com.stanley.uams.domain.auth.SysLogs;
import com.stanley.uams.domain.auth.SysLogsVO;
import com.stanley.uams.mapper.master.auth.SysLogsMapper;
import com.stanley.utils.DateTimeUtil;
import com.stanley.utils.ResultBuilderUtil;
import com.stanley.utils.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统日志管理
 * @Description
 * @date 2016-04-13
 * @since 1.0 
 * @version 1.0  
 * @author 13346450@qq.com 童晟
 */
@Service
public class SysLogsService extends BaseService {

	@Resource
	private SysLogsMapper sysLogsMapper;

	@Transactional
	public String insert(SysLogs sysLogs) {
		sysLogsMapper.insert(sysLogs);
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	@Transactional
	public String delete(Integer idKey) {
		if (null == idKey) {
			return ResultBuilderUtil.resultException("2","id不能为空");
		}
		sysLogsMapper.deleteByPrimaryKey(idKey);
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	@Transactional
	public String deleteBatch(String dataIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataListID", Arrays.asList(dataIds.split(",")));
		sysLogsMapper.deleteBatch(map);
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	@Transactional
	public String update(SysLogs sysLogs) {
		sysLogsMapper.updateByPrimaryKeySelective(sysLogs);
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	public SysLogs selectByIdkey(Integer idKey) {
		return sysLogsMapper.selectByPrimaryKey(idKey);
	}

	public Page<SysLogsVO> selectPage(SearchParam searchParam) {
		Page<SysLogsVO> page = new Page<SysLogsVO>();
		page.setOffset(searchParam.getOffset());
		page.setLimit(searchParam.getLimit());
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startDatetime", searchParam.getStartDate());
		map.put("endDatetime", searchParam.getEndDate()+" 23:59:59");
		map.put("funcMenuNm", searchParam.getSelectedId());
		map.put("funcOperNm", searchParam.getSelectedId2());
		map.put("operNm", searchParam.getSearchName());
		map.put("operRemark", searchParam.getSearchName2());
		map.put("sort", searchParam.getSort());
		map.put("order", searchParam.getOrder());
		page.setParams(map);
		page.setRows(sysLogsMapper.selectPage(page));
		return page;
	}

	public HSSFWorkbook toExcel(SearchParam searchParam) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startDatetime", searchParam.getStartDate());
		map.put("endDatetime", searchParam.getEndDate()+" 23:59:59");
		map.put("funcMenuNm", searchParam.getSelectedId());
		map.put("funcOperNm", searchParam.getSelectedId2());
		map.put("operNm", searchParam.getSearchName());
		map.put("operRemark", searchParam.getSearchName2());
		List<SysLogsVO> list = sysLogsMapper.toExcel(map);
		String[] excelHeader = { "操作的菜单", "操作的动作", "操作的用户id", "操作的用户名", "操作的备注", "操作的日期"};
		HSSFWorkbook wb = new HSSFWorkbook();
		//单元格列宽
		int[] excelHeaderWidth = { 150, 150, 150, 150, 150, 150};
		HSSFSheet sheet = wb.createSheet("系统日志查询");
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
			SysLogsVO logs = list.get(i);
			row.createCell(0).setCellValue(StringUtils.null2Blank(logs.getFuncMenuNm()));
			row.createCell(1).setCellValue(StringUtils.null2Blank(logs.getFuncOperNm()));
			row.createCell(2).setCellValue(StringUtils.null2Blank(logs.getOperId()));
			row.createCell(3).setCellValue(StringUtils.null2Blank(logs.getOperNm()));
			row.createCell(4).setCellValue(StringUtils.null2Blank(logs.getOperRemark()));
			row.createCell(5).setCellValue(null==logs.getOperDt()?
					"": DateTimeUtil.getDateToStrFullFormat(logs.getOperDt()));
		}
		return wb;
	}

	public SysLogs selectOneBySelective(SearchParam searchParam) {
		SysLogs sysLogs = new SysLogs();
		return sysLogsMapper.selectOneBySelective(sysLogs);
	}

	public List<SysLogs> selectAllBySelective(SearchParam searchParam) {
		SysLogs sysLogs = new SysLogs();
		return sysLogsMapper.selectAllBySelective(sysLogs);
	}
	
}
