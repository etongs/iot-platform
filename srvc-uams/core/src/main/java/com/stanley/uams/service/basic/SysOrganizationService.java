package com.stanley.uams.service.basic;

import com.stanley.common.domain.SearchParam;
import com.stanley.common.domain.mybatis.Page;
import com.stanley.common.spring.BaseService;
import com.stanley.uams.domain.basic.SysOrganization;
import com.stanley.uams.domain.basic.SysOrganizationVO;
import com.stanley.uams.mapper.master.basic.SysOrganizationMapper;
import com.stanley.utils.DateTimeUtil;
import com.stanley.utils.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 组织机构管理
 * @Description
 * @date 2016-04-07
 * @since 1.0 
 * @version 1.0  
 * @author 13346450@qq.com 童晟
 */
@Service
public class SysOrganizationService extends BaseService<SysOrganization, SysOrganizationVO> {
	@Resource
	public void setSysOrganizationMapper(SysOrganizationMapper sysOrganizationMapper) {
		super.setBaseMapper(sysOrganizationMapper);
	}

	/**
	 * @Description 分页查询
	 * @date 2017/10/23
	 * @author 13346450@qq.com 童晟
	 * @param
	 * @return
	 */
	public Page<SysOrganizationVO> selectPage(SearchParam searchParam) {
		Map<String, Object> map = new HashMap<>();
		map.put("provinceId", searchParam.getProvince());
		map.put("cityId", searchParam.getCity());
		map.put("districtId", searchParam.getDistrict());
		map.put("cdNm", searchParam.getSearchName());
		return super.selectPage(searchParam, map);
	}

	/**
	 * @Description 导出excel
	 * @date 2017/10/23
	 * @author 13346450@qq.com 童晟
	 * @param
	 * @return
	 */
	public HSSFWorkbook toExcel(SearchParam searchParam) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("provinceId", searchParam.getProvince());
		map.put("cityId", searchParam.getCity());
		map.put("districtId", searchParam.getDistrict());
		map.put("cdNm", searchParam.getSearchName());
		List<SysOrganizationVO> list = getBaseMapper().toExcel(map);
		String[] excelHeader = { "机构名称", "机构类型", "上级机构", "机构简称",
				"机构编码", "简拼", "拼音", "传真", "联系人", "联系电话", "负责人手机号", "Email", "机构网址", "地址", "X坐标-GPS", "Y坐标-GPS", 
				"X坐标-百度地图", "Y坐标-百度地图", "邮政编码", "开户银行", "收款银行账号", "收款人姓名", "银行卡预留手机号码", "创建人", "创建时间", 
				"省id", "省名", "市id", "市名", "县、区id", "县、区名", "机构图片缩略图", "机构图片原图", "机构描述"};
		HSSFWorkbook wb = new HSSFWorkbook();
		//单元格列宽
		int[] excelHeaderWidth = { 150, 150, 150, 75, 75, 50, 150, 150, 50, 150, 150, 150, 150, 150, 150, 150, 150, 
									150, 100, 150, 150, 50, 150, 50, 150, 50, 100, 50, 100, 50, 100, 150, 150, 150};
		HSSFSheet sheet = wb.createSheet("组织机构查询");
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
			SysOrganizationVO organization = list.get(i);
			row.createCell(0).setCellValue(StringUtils.null2Blank(organization.getCdNm()));
			row.createCell(1).setCellValue(StringUtils.null2Blank(organization.getCategoryName()));
			row.createCell(2).setCellValue(StringUtils.null2Blank(organization.getParentOrgId()));
			row.createCell(3).setCellValue(StringUtils.null2Blank(organization.getShortName()));
			row.createCell(4).setCellValue(StringUtils.null2Blank(organization.getCode()));
			row.createCell(5).setCellValue(StringUtils.null2Blank(organization.getPy()));
			row.createCell(6).setCellValue(StringUtils.null2Blank(organization.getPinyin()));
			row.createCell(7).setCellValue(StringUtils.null2Blank(organization.getLinkFax()));
			row.createCell(8).setCellValue(StringUtils.null2Blank(organization.getLinkMan()));
			row.createCell(9).setCellValue(StringUtils.null2Blank(organization.getLinkTel()));
			row.createCell(10).setCellValue(StringUtils.null2Blank(organization.getLinkMobile()));
			row.createCell(11).setCellValue(StringUtils.null2Blank(organization.getEmail()));
			row.createCell(12).setCellValue(StringUtils.null2Blank(organization.getUrl()));
			row.createCell(13).setCellValue(StringUtils.null2Blank(organization.getAddress()));
			row.createCell(14).setCellValue(StringUtils.null2Blank(organization.getGpsX()));
			row.createCell(15).setCellValue(StringUtils.null2Blank(organization.getGpsY()));
			row.createCell(16).setCellValue(StringUtils.null2Blank(organization.getBaiduX()));
			row.createCell(17).setCellValue(StringUtils.null2Blank(organization.getBaiduY()));
			row.createCell(18).setCellValue(StringUtils.null2Blank(organization.getPostCode()));
			row.createCell(19).setCellValue(StringUtils.null2Blank(organization.getBankName()));
			row.createCell(20).setCellValue(StringUtils.null2Blank(organization.getBankAccount()));
			row.createCell(21).setCellValue(StringUtils.null2Blank(organization.getBankAccountName()));
			row.createCell(22).setCellValue(StringUtils.null2Blank(organization.getBankReservedMobile()));
			row.createCell(23).setCellValue(StringUtils.null2Blank(organization.getCreateName()));
			row.createCell(24).setCellValue(null==organization.getCreateDt()?
						"": DateTimeUtil.getDateToStrFullFormat(organization.getCreateDt()));
			row.createCell(25).setCellValue(StringUtils.null2Blank(organization.getProvinceId()));
			row.createCell(26).setCellValue(StringUtils.null2Blank(organization.getProvinceName()));
			row.createCell(27).setCellValue(StringUtils.null2Blank(organization.getCityId()));
			row.createCell(28).setCellValue(StringUtils.null2Blank(organization.getCityName()));
			row.createCell(29).setCellValue(StringUtils.null2Blank(organization.getDistrictId()));
			row.createCell(30).setCellValue(StringUtils.null2Blank(organization.getDistrictName()));
			row.createCell(31).setCellValue(StringUtils.null2Blank(organization.getImageMinUrl()));
			row.createCell(32).setCellValue(StringUtils.null2Blank(organization.getImageUrl()));
			row.createCell(34).setCellValue(StringUtils.null2Blank(organization.getDescription()));
		}
		return wb;
	}

}