package com.stanley.uams.service.basic;

import com.alibaba.fastjson.JSON;
import com.stanley.common.domain.SearchParam;
import com.stanley.common.domain.mybatis.Page;
import com.stanley.common.spring.BaseService;
import com.stanley.uams.domain.basic.SysParms;
import com.stanley.uams.domain.basic.SysParmsVO;
import com.stanley.uams.mapper.master.basic.SysParmsMapper;
import com.stanley.utils.DateTimeUtil;
import com.stanley.utils.ResultBuilderUtil;
import com.stanley.utils.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
/**
 * 系统参数管理
 * @Description
 * @date 2016-08-09
 * @since 1.0 
 * @version 1.0  
 * @author 13346450@qq.com 童晟
 */
@Service
public class SysParmsService extends BaseService<SysParms, SysParmsVO> {
	@Resource
	public void setSysParmsMapper(SysParmsMapper sysParmsMapper) {
		super.setBaseMapper(sysParmsMapper);
	}

	/**
	 * @Description 分页查询
	 * @date 2017/10/23
	 * @author 13346450@qq.com 童晟
	 * @param
	 * @return
	 */
	public Page<SysParmsVO> selectPage(SearchParam searchParam) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("parmValue", searchParam.getSearchName());
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
		map.put("parmValue", searchParam.getSearchName());
		List<SysParmsVO> list = getBaseMapper().toExcel(map);
		String[] excelHeader = { "参数key", "参数值", "备注", "最后修改时间"};
		HSSFWorkbook wb = new HSSFWorkbook();
		//单元格列宽
		int[] excelHeaderWidth = { 150, 150, 150, 150};
		HSSFSheet sheet = wb.createSheet("系统参数查询");
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
			SysParmsVO parms = list.get(i);
			row.createCell(0).setCellValue(StringUtils.null2Blank(parms.getParmKey()));
			row.createCell(1).setCellValue(StringUtils.null2Blank(parms.getParmValue()));
			row.createCell(2).setCellValue(StringUtils.null2Blank(parms.getRemarks()));
			row.createCell(3).setCellValue(null==parms.getCreateDt()?
						"": DateTimeUtil.getDateToStrFullFormat(parms.getCreateDt()));
		}
		return wb;
	}

	/**
	 * 一次性保存表格 //TODO
	 * @param insertDatagrid
	 * @param updateDatagrid
	 * @param deleteDatagrid
	 * @return
	 */
	@Transactional
	public String saveAll(String insertDatagrid, String updateDatagrid,
			String deleteDatagrid) {
		List<SysParms> insertSysParmsList = JSON.parseArray(insertDatagrid, SysParms.class);
		List<SysParms> updateSysParmsList = JSON.parseArray(updateDatagrid, SysParms.class);
		List<SysParms> deleteSysParmsList = JSON.parseArray(deleteDatagrid, SysParms.class);
		if(insertSysParmsList.size()>0 && !insertDatagrid.equals("[{}]")){
			for (SysParms sysParms : insertSysParmsList) {
				sysParms.setCreateDt(new Timestamp(System.currentTimeMillis()));
				getBaseMapper().insert(sysParms);
			}
		}
		if(updateSysParmsList.size()>0 && !updateDatagrid.equals("[{}]")){
			for (SysParms sysParms : updateSysParmsList) {
				sysParms.setCreateDt(new Timestamp(System.currentTimeMillis()));
				getBaseMapper().updateByPrimaryKeySelective(sysParms);
			}
		}
		if(deleteSysParmsList.size()>0 && !deleteDatagrid.equals("[{}]")){
			for (SysParms sysParms : deleteSysParmsList) {
				getBaseMapper().deleteByPrimaryKey(sysParms.getIdKey());
			}
		}
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	
}
