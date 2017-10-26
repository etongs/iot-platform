package com.stanley.uams.service.basic;

import com.stanley.common.domain.SearchParam;
import com.stanley.common.domain.mybatis.Page;
import com.stanley.common.spring.BaseService;
import com.stanley.uams.domain.basic.SysOpenApi;
import com.stanley.uams.domain.basic.SysOpenApiVO;
import com.stanley.uams.mapper.master.basic.SysOpenApiMapper;
import com.stanley.utils.*;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

/**
 * 开放接口接入管理
 * @Description
 * @date 2017-01-06
 * @since 1.0 
 * @version 1.0  
 * @author 13346450@qq.com 童晟
 */
@Service
public class SysOpenApiService extends BaseService<SysOpenApi, SysOpenApiVO> {
	@Resource
	public void setSysOpenApiMapper(SysOpenApiMapper sysOpenApiMapper) {
		super.setBaseMapper(sysOpenApiMapper);
	}

	@Transactional
	public String insert(SysOpenApi sysOpenApi) {
		sysOpenApi.setOperId(ShiroSessionUtil.getUserId());
		sysOpenApi.setOperNm(ShiroSessionUtil.getUserName());
		sysOpenApi.setOperDt(new Timestamp(System.currentTimeMillis()));
		sysOpenApi.setPrivateKey(EncryptUtil.SHA256(UuidUtil.generateUuid()+sysOpenApi.getUserCode()));
		sysOpenApi.setTimestampVal(1000000L);
		return super.insert(sysOpenApi);
	}

	@Transactional
	public String update(SysOpenApi sysOpenApi) {
		sysOpenApi.setOperId(ShiroSessionUtil.getUserId());
		sysOpenApi.setOperNm(ShiroSessionUtil.getUserName());
		sysOpenApi.setOperDt(new Timestamp(System.currentTimeMillis()));
		return super.update(sysOpenApi);
	}

	public Page<SysOpenApiVO> selectPage(SearchParam searchParam) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("userName", searchParam.getSearchName());
		return super.selectPage(searchParam, map);
	}

	public HSSFWorkbook toExcel(SearchParam searchParam) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userName", searchParam.getSearchName());
		List<SysOpenApiVO> list = getBaseMapper().toExcel(map);
		String[] excelHeader = { "接入的用户名称", "接入的用户唯一标识", "接入的用户私钥", "操作的用户id", "操作的用户名", "操作的备注", "操作的日期"};
		HSSFWorkbook wb = new HSSFWorkbook();
		//单元格列宽
		int[] excelHeaderWidth = { 150, 150, 150, 150, 150, 150, 150};
		HSSFSheet sheet = wb.createSheet("开放接口接入查询");
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
			SysOpenApiVO openApi = list.get(i);
			row.createCell(0).setCellValue(StringUtils.null2Blank(openApi.getUserName()));
			row.createCell(1).setCellValue(StringUtils.null2Blank(openApi.getUserCode()));
			row.createCell(2).setCellValue(StringUtils.null2Blank(openApi.getPrivateKey()));
			row.createCell(3).setCellValue(StringUtils.null2Blank(openApi.getOperId()));
			row.createCell(4).setCellValue(StringUtils.null2Blank(openApi.getOperNm()));
			row.createCell(5).setCellValue(StringUtils.null2Blank(openApi.getOperRemark()));
			row.createCell(6).setCellValue(null==openApi.getOperDt()?
						"": DateTimeUtil.getDateToStrFullFormat(openApi.getOperDt()));
		}
		return wb;
	}

	public String validate(SysOpenApi sysOpenApi) {
		String result = "";
		if(null == sysOpenApi.getIdKey()){//新增时
			result = (null == super.selectOneBySelective(sysOpenApi)) ?
					ResultBuilderUtil.VALIDATE_SUCCESS:ResultBuilderUtil.VALIDATE_ERROR;
		}else{//修改时
			result = (null == ((SysOpenApiMapper)getBaseMapper()).checkExistCode(sysOpenApi)) ?
					ResultBuilderUtil.VALIDATE_SUCCESS:ResultBuilderUtil.VALIDATE_ERROR;
		}
		return result;
	}

}
