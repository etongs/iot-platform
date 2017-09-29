package com.stanley.uams.service.basic;

import com.stanley.common.domain.SearchParam;
import com.stanley.common.domain.mybatis.Page;
import com.stanley.common.spring.BaseService;
import com.stanley.uams.domain.basic.SysDict;
import com.stanley.uams.mapper.master.basic.SysDictMapper;
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
 * 数据字典管理
 * @Description
 * @date 2016-04-18
 * @since 1.0 
 * @version 1.0  
 * @author 13346450@qq.com 童晟
 */
@Service
public class SysDictService extends BaseService {
	//private final static String FUNC_MENU ="数据字典表";

	@Resource
	private SysDictMapper sysDictMapper;

	@Transactional
	public String insert(SysDict sysDict) {
		sysDict.setCreateId(getUserId());
		sysDict.setCreateDt(new Timestamp(System.currentTimeMillis()));
		sysDictMapper.insert(sysDict);
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_CREATE, "idKey:" +sysDict.getIdKey());
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	@Transactional
	public String delete(Integer idKey) {
		if (null == idKey) {
			return ResultBuilderUtil.resultException("2","id不能为空");
		}
		sysDictMapper.deleteByPrimaryKey(idKey);
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_DELETE, "idKey:" + idKey);
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	@Transactional
	public String deleteBatch(String dataIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataListID", Arrays.asList(dataIds.split(",")));
		sysDictMapper.deleteBatch(map);
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_DELETE, "ids:" + dataIds);
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	@Transactional
	public String update(SysDict sysDict) {
		sysDict.setCreateId(getUserId());
		sysDict.setCreateDt(new Timestamp(System.currentTimeMillis()));
		sysDictMapper.updateByPrimaryKeySelective(sysDict);
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_UPDATE, "idKey:" +sysDict.getIdKey());
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	public SysDict selectByIdkey(Integer idKey) {
		return sysDictMapper.selectByPrimaryKey(idKey);
	}

	public Page<SysDict> selectPage(String dictTypeId , SearchParam searchParam) {
		Page<SysDict> page = new Page<SysDict>();
		page.setOffset(searchParam.getOffset());
		page.setLimit(searchParam.getLimit());
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("dictTypeId", dictTypeId);
		map.put("sort", searchParam.getSort());
		map.put("order", searchParam.getOrder());
		page.setParams(map);
		page.setRows(sysDictMapper.selectPage(page));
		return page;
	}

	public HSSFWorkbook toExcel(SearchParam searchParam) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("dictTypeId", searchParam.getSearchName());
		List<SysDict> list = sysDictMapper.toExcel(map);
		String[] excelHeader = { "字典id", "字典名称", "字典值", "备注", "创建人", "创建时间"};
		HSSFWorkbook wb = new HSSFWorkbook();
		//单元格列宽
		int[] excelHeaderWidth = { 150, 150, 150, 150, 150, 150};
		HSSFSheet sheet = wb.createSheet("数据字典查询");
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
			SysDict dict = list.get(i);
			row.createCell(0).setCellValue(StringUtils.null2Blank(dict.getDictTypeId()));
			row.createCell(1).setCellValue(StringUtils.null2Blank(dict.getDictTypeNm()));
			row.createCell(2).setCellValue(StringUtils.null2Blank(dict.getDictValue()));
			row.createCell(3).setCellValue(StringUtils.null2Blank(dict.getRemark()));
			row.createCell(4).setCellValue(StringUtils.null2Blank(dict.getCreateId()));
			row.createCell(5).setCellValue(null==dict.getCreateDt()?
						"": DateTimeUtil.getDateToStrFullFormat(dict.getCreateDt()));
		}
		return wb;
	}

	public SysDict selectOneBySelective(SearchParam searchParam) {
		SysDict sysDict = new SysDict();
		return sysDictMapper.selectOneBySelective(sysDict);
	}

	public List<SysDict> selectAllBySelective(String dictTypeId) {
		SysDict sysDict = new SysDict();
		sysDict.setDictTypeId(dictTypeId);
		return sysDictMapper.selectAllBySelective(sysDict);
	}
	
	public List<Map<String, Object>> listDictType() {
		return sysDictMapper.selectAllByDistinct();
	}

}
