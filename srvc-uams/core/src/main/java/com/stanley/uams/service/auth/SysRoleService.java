package com.stanley.uams.service.auth;

import com.stanley.common.domain.SearchParam;
import com.stanley.common.spring.BaseService;
import com.stanley.uams.domain.auth.SysRole;
import com.stanley.uams.domain.auth.SysRoleVO;
import com.stanley.uams.mapper.master.auth.SysRoleMapper;
import com.stanley.utils.DateTimeUtil;
import com.stanley.utils.ResultBuilderUtil;
import com.stanley.utils.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色服务
 * 基础的增删改查已经在基类中实现，此处只需写该功能特有的方法即可
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/10/19
 **/
@Service
public class SysRoleService extends BaseService<SysRole, SysRoleVO> {
    @Resource
    public void setSysRoleMapper(SysRoleMapper sysRoleMapper) {
        super.setBaseMapper(sysRoleMapper);
    }

    public HSSFWorkbook toExcel(SearchParam searchParam) {
        List<SysRoleVO> list = super.toExcel(null);
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

    /**
     * @Description 角色名校验
     * @date 2017/10/19
     * @author 13346450@qq.com 童晟
     * @param
     * @return
     */
    public String validate(SysRole sysRole) {
        String result = "";
        if(null == sysRole.getIdKey()){//新增时
            result = (null == getBaseMapper().selectOneBySelective(sysRole)) ?
                    ResultBuilderUtil.VALIDATE_SUCCESS: ResultBuilderUtil.VALIDATE_ERROR;
        }else{//修改时
            result = (null == ((SysRoleMapper)getBaseMapper()).checkExistRolename(sysRole)) ?
                    ResultBuilderUtil.VALIDATE_SUCCESS: ResultBuilderUtil.VALIDATE_ERROR;
        }
        return result;
    }

}
