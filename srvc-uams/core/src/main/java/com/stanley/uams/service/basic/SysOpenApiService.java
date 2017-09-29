package com.stanley.uams.service.basic;

import com.stanley.common.domain.SearchParam;
import com.stanley.common.domain.mybatis.Page;
import com.stanley.common.spring.BaseService;
import com.stanley.uams.domain.auth.SysUser;
import com.stanley.uams.domain.basic.SysOpenApi;
import com.stanley.uams.domain.basic.SysOpenApiVO;
import com.stanley.uams.mapper.master.basic.SysOpenApiMapper;
import com.stanley.utils.*;
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
 * 开放接口接入管理
 * @Description
 * @date 2017-01-06
 * @since 1.0 
 * @version 1.0  
 * @author 13346450@qq.com 童晟
 */
@Service
public class SysOpenApiService extends BaseService {
	private final static String FUNC_MENU ="开放接口接入表";

	@Resource
	private SysOpenApiMapper sysOpenApiMapper;

	@Transactional
	public String insert(SysOpenApi sysOpenApi) {
		sysOpenApi.setOperId(getUserId());
		sysOpenApi.setOperNm(getUserName());
		sysOpenApi.setOperDt(new Timestamp(System.currentTimeMillis()));
		sysOpenApi.setPrivateKey(EncryptUtil.SHA256(UuidUtil.generateUuid()+sysOpenApi.getUserCode()));
		sysOpenApi.setTimestampVal(1000000L);
		sysOpenApiMapper.insert(sysOpenApi);
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_CREATE, "idKey:" +sysOpenApi.getIdKey());
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	@Transactional
	public String delete(Integer idKey) {
		if (null == idKey) {
			return ResultBuilderUtil.resultException("2","id不能为空");
		}
		sysOpenApiMapper.deleteByPrimaryKey(idKey);
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_DELETE, "idKey:" + idKey);
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	@Transactional
	public String deleteBatch(String dataIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataListID", Arrays.asList(dataIds.split(",")));
		sysOpenApiMapper.deleteBatch(map);
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_DELETE, "ids:" + dataIds);
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	@Transactional
	public String update(SysOpenApi sysOpenApi) {
		sysOpenApi.setOperId(getUserId());
		sysOpenApi.setOperNm(getUserName());
		sysOpenApi.setOperDt(new Timestamp(System.currentTimeMillis()));
		sysOpenApiMapper.updateByPrimaryKeySelective(sysOpenApi);
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_UPDATE, "idKey:" +sysOpenApi.getIdKey());
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	public SysOpenApi selectByIdkey(Integer idKey) {
		return sysOpenApiMapper.selectByPrimaryKey(idKey);
	}

	public Page<SysOpenApiVO> selectPage(SearchParam searchParam) {
		Page<SysOpenApiVO> page = new Page<SysOpenApiVO>();
		page.setOffset(searchParam.getOffset());
		page.setLimit(searchParam.getLimit());
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userName", searchParam.getSearchName());
		map.put("sort", searchParam.getSort());
		map.put("order", searchParam.getOrder());
		page.setParams(map);
		page.setRows(sysOpenApiMapper.selectPage(page));
		return page;
	}

	public HSSFWorkbook toExcel(SearchParam searchParam) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userName", searchParam.getSearchName());
		List<SysOpenApiVO> list = sysOpenApiMapper.toExcel(map);
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

	public SysOpenApi selectOneBySelective(String userCode) {
		SysOpenApi sysOpenApi = new SysOpenApi();
		sysOpenApi.setUserCode(userCode);
		return sysOpenApiMapper.selectOneBySelective(sysOpenApi);
	}

	public List<SysOpenApi> selectAllBySelective(SearchParam searchParam) {
		SysOpenApi sysOpenApi = new SysOpenApi();
		return sysOpenApiMapper.selectAllBySelective(sysOpenApi);
	}

	public String validate(SysOpenApi sysOpenApi) {
		String result = "";
		if(null == sysOpenApi.getIdKey()){//新增时
			result = (null == sysOpenApiMapper.selectOneBySelective(sysOpenApi)) ?
					ResultBuilderUtil.VALIDATE_SUCCESS:ResultBuilderUtil.VALIDATE_ERROR;
		}else{//修改时
			result = (null == sysOpenApiMapper.checkExistCode(sysOpenApi)) ?
					ResultBuilderUtil.VALIDATE_SUCCESS:ResultBuilderUtil.VALIDATE_ERROR;
		}
		return result;
	}



}
