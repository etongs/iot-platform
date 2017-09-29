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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统参数管理
 * @Description
 * @date 2016-08-09
 * @since 1.0 
 * @version 1.0  
 * @author 13346450@qq.com 童晟
 */
@Service
public class SysParmsService extends BaseService {
	//private final static String FUNC_MENU ="系统参数表";

	@Resource
	private SysParmsMapper sysParmsMapper;

	@Transactional
	public String insert(SysParms sysParms) {
		sysParms.setCreateDt(new Timestamp(System.currentTimeMillis()));
		sysParmsMapper.insert(sysParms);
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_CREATE, "idKey:" +sysParms.getIdKey());
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	@Transactional
	public String delete(Integer idKey) {
		if (null == idKey) {
			return ResultBuilderUtil.resultException("2","id不能为空");
		}
		sysParmsMapper.deleteByPrimaryKey(idKey);
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_DELETE, "idKey:" + idKey);
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	@Transactional
	public String deleteBatch(String dataIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataListID", Arrays.asList(dataIds.split(",")));
		sysParmsMapper.deleteBatch(map);
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_DELETE, "ids:" + dataIds);
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	@Transactional
	public String update(SysParms sysParms) {
		sysParms.setCreateDt(new Timestamp(System.currentTimeMillis()));
		sysParmsMapper.updateByPrimaryKeySelective(sysParms);
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_UPDATE, "idKey:" +sysParms.getIdKey());
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	public SysParms selectByIdkey(Integer idKey) {
		return sysParmsMapper.selectByPrimaryKey(idKey);
	}

	public Page<SysParmsVO> selectPage(SearchParam searchParam) {
		Page<SysParmsVO> page = new Page<SysParmsVO>();
		page.setOffset(searchParam.getOffset());
		page.setLimit(searchParam.getLimit());
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("parmValue", searchParam.getSearchName());
		map.put("sort", searchParam.getSort());
		map.put("order", searchParam.getOrder());
		page.setParams(map);
		page.setRows(sysParmsMapper.selectPage(page));
		return page;
	}

	public HSSFWorkbook toExcel(SearchParam searchParam) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("parmValue", searchParam.getSearchName());
		List<SysParmsVO> list = sysParmsMapper.toExcel(map);
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
		//TODO Auto-generated method
		}
		return wb;
	}

	public SysParms selectOneBySelective(SearchParam searchParam) {
		SysParms sysParms = new SysParms();
		return sysParmsMapper.selectOneBySelective(sysParms);
	}

	public List<SysParms> selectAllBySelective(SearchParam searchParam) {
		SysParms sysParms = new SysParms();
		return sysParmsMapper.selectAllBySelective(sysParms);
	}

	@Transactional
	public String saveAll(String insertDatagrid, String updateDatagrid,
			String deleteDatagrid) {
		List<SysParms> insertSysParmsList = JSON.parseArray(insertDatagrid, SysParms.class);
		List<SysParms> updateSysParmsList = JSON.parseArray(updateDatagrid, SysParms.class);
		List<SysParms> deleteSysParmsList = JSON.parseArray(deleteDatagrid, SysParms.class);
		if(insertSysParmsList.size()>0 && !insertDatagrid.equals("[{}]")){
			for (SysParms sysParms : insertSysParmsList) {
				sysParms.setCreateDt(new Timestamp(System.currentTimeMillis()));
				sysParmsMapper.insert(sysParms);
				//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_CREATE, "idKey:" +sysParms.getIdKey());
			}
		}
		if(updateSysParmsList.size()>0 && !updateDatagrid.equals("[{}]")){
			for (SysParms sysParms : updateSysParmsList) {
				sysParms.setCreateDt(new Timestamp(System.currentTimeMillis()));
				sysParmsMapper.updateByPrimaryKeySelective(sysParms);
				//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_UPDATE, "idKey:" +sysParms.getIdKey());
			}
		}
		if(deleteSysParmsList.size()>0 && !deleteDatagrid.equals("[{}]")){
			for (SysParms sysParms : deleteSysParmsList) {
				sysParmsMapper.deleteByPrimaryKey(sysParms.getIdKey());
				//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_DELETE, "idKey:" + sysParms.getIdKey());
			}
		}
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	
}
