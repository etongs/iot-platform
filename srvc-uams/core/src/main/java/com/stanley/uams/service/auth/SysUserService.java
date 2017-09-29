package com.stanley.uams.service.auth;


import com.stanley.common.domain.SearchParam;
import com.stanley.common.domain.mybatis.Page;
import com.stanley.common.spring.BaseService;
import com.stanley.uams.domain.auth.SysUser;
import com.stanley.uams.domain.auth.SysUserVO;
import com.stanley.uams.mapper.master.auth.SysUserMapper;
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
 * 用户管理
 * @Description
 * @date 2016-04-11
 * @since 1.0 
 * @version 1.0  
 * @author 13346450@qq.com 童晟
 */
@Service
public class SysUserService extends BaseService {
	private final static String FUNC_MENU ="用户表";

	@Resource
	private SysUserMapper sysUserMapper;
	@Resource
	private SysUserRoleService sysUserRoleService;

	@Transactional
	public String insert(SysUser sysUser) {
		sysUser.setCreateId(getUserId());
		sysUser.setCreateDt(new Timestamp(System.currentTimeMillis())); 
		sysUser.setIsModify(true);
		sysUser.setPasswd(EncryptUtil.shiroEncrypt(sysUser.getAccount()+"#123", sysUser.getAccount()));
		sysUserMapper.insert(sysUser);
		sysUserRoleService.insertByUserId(sysUser.getIdKey(), sysUser.getRoleIds());
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_CREATE, "idKey:" +sysUser.getIdKey());
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	@Transactional
	public String delete(Integer idKey) {
		if (null == idKey) {
			return ResultBuilderUtil.resultException("2","id不能为空");
		}
		sysUserMapper.deleteByPrimaryKey(idKey);
		sysUserRoleService.deleteByUserid(idKey);
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_DELETE, "idKey:" + idKey);
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	@Transactional
	public String deleteBatch(String dataIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataListID", Arrays.asList(dataIds.split(",")));
		sysUserMapper.deleteBatch(map);
		sysUserRoleService.deleteBatch(dataIds);
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_DELETE, "ids:" + dataIds);
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	@Transactional
	public String update(SysUser sysUser) {
		sysUser.setCreateId(getUserId());
		sysUser.setCreateDt(new Timestamp(System.currentTimeMillis()));
		sysUserMapper.updateByPrimaryKeySelective(sysUser);
		sysUserRoleService.updateByUserId(sysUser.getIdKey(), sysUser.getRoleIds());
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_UPDATE, "idKey:" +sysUser.getIdKey());
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	public SysUser selectByIdkey(Integer idKey) {
		return sysUserMapper.selectByPrimaryKey(idKey);
	}

	public Page<SysUserVO> selectPage(SearchParam searchParam) {
		Page<SysUserVO> page = new Page<SysUserVO>();
		page.setOffset(searchParam.getOffset());
		page.setLimit(searchParam.getLimit());
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("nameCn", searchParam.getSearchName());
		map.put("sort", searchParam.getSort());
		map.put("order", searchParam.getOrder());
		page.setParams(map);
		page.setRows(sysUserMapper.selectPage(page));
		return page;
	}

	public HSSFWorkbook toExcel(SearchParam searchParam) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("nameCn", searchParam.getSearchName());
		List<SysUserVO> list = sysUserMapper.toExcel(map);
		String[] excelHeader = { "所属组织机构", "用户帐号", "用户姓名",  "昵称", "性别", "邮箱", "生日", "手机号", "创建人", "创建时间"};
		HSSFWorkbook wb = new HSSFWorkbook();
		//单元格列宽
		int[] excelHeaderWidth = { 150,150, 75, 100,  50, 150, 100, 150, 75, 150};
		HSSFSheet sheet = wb.createSheet("用户查询");
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
			SysUserVO user = list.get(i);
			row.createCell(0).setCellValue(StringUtils.null2Blank(user.getOrgName()));
			row.createCell(1).setCellValue(StringUtils.null2Blank(user.getAccount()));
			row.createCell(2).setCellValue(StringUtils.null2Blank(user.getNameCn()));
			row.createCell(3).setCellValue(StringUtils.null2Blank(user.getNickname()));
			row.createCell(4).setCellValue(StringUtils.null2Blank(user.getGender()));
			row.createCell(5).setCellValue(StringUtils.null2Blank(user.getEmail()));
			row.createCell(6).setCellValue(null==user.getBirthday()? 
						"": DateTimeUtil.formatDateToStr(user.getBirthday(), "yyyy-mm-dd"));
			row.createCell(7).setCellValue(StringUtils.null2Blank(user.getMobile()));
			row.createCell(8).setCellValue(StringUtils.null2Blank(user.getCreateName()));
			row.createCell(9).setCellValue(null==user.getCreateDt()?
						"": DateTimeUtil.getDateToStrFullFormat(user.getCreateDt()));
		}
		return wb;
	}

	public SysUser selectOneBySelective(SysUser sysUser) {
		return sysUserMapper.selectOneBySelective(sysUser);
	}

	public List<SysUser> selectAllBySelective(SysUser sysUser) {
		return sysUserMapper.selectAllBySelective(sysUser);
	}

	public String modifyPwd(String oldPwd, String newPwd) {
		SysUser sysUser = selectByIdkey(getUserId());
		if(null != oldPwd){
			if (!EncryptUtil.shiroEncrypt(oldPwd, sysUser.getAccount()).equals(sysUser.getPasswd())) {
				return ResultBuilderUtil.resultException("2", "原密码错误，请重新输入。");
			}
		}
		sysUser.setPasswd(EncryptUtil.shiroEncrypt(newPwd, sysUser.getAccount()));
		sysUser.setIsModify(false);
		sysUser.setCreateId(getUserId());
		sysUser.setCreateDt(new Timestamp(System.currentTimeMillis()));
		sysUserMapper.updateByPrimaryKeySelective(sysUser);
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_UPDATE, "idKey:" + sysUser.getIdKey());
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	@Transactional
	public String initializePwd(String dataIds) {
		List<String> list=Arrays.asList(dataIds.split(","));
		for(int i=0;i<list.size();i++)
		{
			SysUser sysUser=new SysUser();
			sysUser=sysUserMapper.selectByPrimaryKey((Integer.parseInt(list.get(i))));
			sysUser.setPasswd(EncryptUtil.shiroEncrypt(sysUser.getAccount()+"#123", sysUser.getAccount()));
			sysUser.setIsModify(true);
			sysUserMapper.updateByPrimaryKeySelective(sysUser);
		}
		//writeLog(FUNC_MENU, Constants.FUNC_OPER_NM_UPDATE, "ids:" + dataIds);
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	public String validate(SysUser sysUser) {
		String result = "";
		if(null == sysUser.getIdKey()){//新增时
			result = (null == sysUserMapper.selectOneBySelective(sysUser)) ?
					ResultBuilderUtil.VALIDATE_SUCCESS:ResultBuilderUtil.VALIDATE_ERROR;
		}else{//修改时
			result = (null == sysUserMapper.checkExistAccount(sysUser)) ?
					ResultBuilderUtil.VALIDATE_SUCCESS:ResultBuilderUtil.VALIDATE_ERROR;
		}
		return result;
	}

	public String validOldPasswd(String oldPwd) {
		return EncryptUtil.shiroEncrypt(oldPwd, getUserAccount()).equals(selectByIdkey(getUserId()).getPasswd())?
					ResultBuilderUtil.VALIDATE_SUCCESS: ResultBuilderUtil.VALIDATE_ERROR;
	}

	public SysUser queryMyself() {
		return this.selectByIdkey(getUserId());
	}

	/**
	 * 修改最后登录时间
	 * @param idKey
	 */
	public void updateLastLoginTime(Integer idKey){
		SysUser user = new SysUser();
		user.setIdKey(idKey);
		user.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
		sysUserMapper.updateByPrimaryKeySelective(user);
	}
	
}
