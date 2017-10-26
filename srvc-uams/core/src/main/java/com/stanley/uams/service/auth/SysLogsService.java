package com.stanley.uams.service.auth;

import com.stanley.common.domain.SearchParam;
import com.stanley.common.domain.mybatis.Page;
import com.stanley.common.spring.BaseService;
import com.stanley.uams.domain.auth.SysLogs;
import com.stanley.uams.domain.auth.SysLogsVO;
import com.stanley.uams.mapper.master.auth.SysLogsMapper;
import com.stanley.utils.DateTimeUtil;
import com.stanley.utils.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
/**
 * 系统日志管理
 * @Description
 * @date 2016-04-13
 * @since 1.0 
 * @version 1.0  
 * @author 13346450@qq.com 童晟
 */
@Service
public class SysLogsService extends BaseService<SysLogs, SysLogsVO> {
	@Resource
	public void setSysLogsMapper(SysLogsMapper sysLogsMapper) {
		super.setBaseMapper(sysLogsMapper);
	}

	/**
	 * @Description 分页查询
	 * @date 2017/10/23
	 * @author 13346450@qq.com 童晟
	 * @param
	 * @return
	 */
	public Page<SysLogsVO> selectPage(SearchParam searchParam) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("startDatetime", searchParam.getStartDate());
		map.put("endDatetime", searchParam.getEndDate()+" 23:59:59");
		map.put("funcMenuNm", searchParam.getSelectedId());
		map.put("funcOperNm", searchParam.getSelectedId2());
		map.put("operNm", searchParam.getSearchName());
		map.put("operRemark", searchParam.getSearchName2());
		return super.selectPage(searchParam,map);
	}

	/**
	 * @Description 导出excel
	 * @date 2017/10/23
	 * @author 13346450@qq.com 童晟
	 * @param
	 * @return
	 */
	public HSSFWorkbook toExcel(SearchParam searchParam) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("startDatetime", searchParam.getStartDate());
		map.put("endDatetime", searchParam.getEndDate()+" 23:59:59");
		map.put("funcMenuNm", searchParam.getSelectedId());
		map.put("funcOperNm", searchParam.getSelectedId2());
		map.put("operNm", searchParam.getSearchName());
		map.put("operRemark", searchParam.getSearchName2());
		List<SysLogsVO> list = getBaseMapper().toExcel(map);
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

}
