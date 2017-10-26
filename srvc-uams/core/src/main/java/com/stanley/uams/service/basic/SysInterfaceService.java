package com.stanley.uams.service.basic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.stanley.common.domain.SearchParam;
import com.stanley.common.domain.mybatis.Page;
import com.stanley.common.spring.BaseService;
import com.stanley.uams.domain.basic.SysInterface;
import com.stanley.uams.domain.basic.SysInterfaceVO;
import com.stanley.uams.mapper.master.basic.SysInterfaceMapper;
import com.stanley.utils.*;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 外部接口配置管理
 * @Description
 * @date 2016-10-19
 * @since 1.0 
 * @version 1.0  
 * @author ts
 */
@Service
public class SysInterfaceService extends BaseService {
	@Resource
	public void setSysInterfaceMapper(SysInterfaceMapper sysInterfaceMapper) {
		super.setBaseMapper(sysInterfaceMapper);
	}
	@Resource
	private RestTemplate restTemplate;

	/**
	 * @Description 分页查询
	 * @date 2017/10/24
	 * @author 13346450@qq.com 童晟
	 * @param
	 * @return
	 */
	public Page<SysInterfaceVO> selectPage(SearchParam searchParam) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("infName", searchParam.getSearchName());
		return super.selectPage(searchParam, map);
	}

	/**
	 * @Description 导出excel
	 * @date 2017/10/24
	 * @author 13346450@qq.com 童晟
	 * @param
	 * @return
	 */
	public HSSFWorkbook toExcel(SearchParam searchParam) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("infName", searchParam.getSearchName());
		List<SysInterfaceVO> list = getBaseMapper().toExcel(map);
		String[] excelHeader = { "接口名称", "接口地址", "接口参数(json格式)", "加密公式", "加密算法字符集", "密码的key值", "http请求方式(get,post)", "接收到的数据格式(json,xml)", "收到数据后的实现类bean名称", "计划任务状态(1-启动，0-停止)", "上次执行时间", "创建用户id", "创建日期"};
		HSSFWorkbook wb = new HSSFWorkbook();
		//单元格列宽
		int[] excelHeaderWidth = { 150, 150, 150, 150, 150, 150, 150, 150, 150, 150, 150, 150, 150};
		HSSFSheet sheet = wb.createSheet("外部接口配置查询");
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
			SysInterfaceVO interfaceVO = list.get(i);
			row.createCell(0).setCellValue(StringUtils.null2Blank(interfaceVO.getInfName()));
			row.createCell(1).setCellValue(StringUtils.null2Blank(interfaceVO.getInfUrl()));
			row.createCell(2).setCellValue(StringUtils.null2Blank(interfaceVO.getInfParms()));
			row.createCell(3).setCellValue(StringUtils.null2Blank(interfaceVO.getEncryptExpression()));
			row.createCell(4).setCellValue(StringUtils.null2Blank(interfaceVO.getEncryptCharset()));
			row.createCell(5).setCellValue(StringUtils.null2Blank(interfaceVO.getPasswdKey()));
			row.createCell(6).setCellValue(StringUtils.null2Blank(interfaceVO.getHttpMethod()));
			row.createCell(7).setCellValue(StringUtils.null2Blank(interfaceVO.getReceivedDataFormat()));
			row.createCell(8).setCellValue(StringUtils.null2Blank(interfaceVO.getImplBeanName()));
			row.createCell(9).setCellValue(StringUtils.null2Blank(interfaceVO.getScheduledStatus()));
			row.createCell(10).setCellValue(null==interfaceVO.getLastExeTime()?
						"": DateTimeUtil.getDateToStrFullFormat(interfaceVO.getLastExeTime()));
			row.createCell(11).setCellValue(StringUtils.null2Blank(interfaceVO.getCreateId()));
			row.createCell(12).setCellValue(null==interfaceVO.getCreateDt()?
						"": DateTimeUtil.getDateToStrFullFormat(interfaceVO.getCreateDt()));
		}
		return wb;
	}

	/**
	 * @Description 运行接口
	 * @date 2017/10/24
	 * @author 13346450@qq.com 童晟
	 * @param
	 * @return
	 */
	public String runInterface(String dataIds) {
		List<String> list =  Arrays.asList(dataIds.split(","));
		for(int i=0 ;i<list.size();i++)
		{
			SysInterface sysInterface=(SysInterface)getBaseMapper().selectByPrimaryKey(Integer.parseInt(list.get(i)));
			Map<String, String> map = new HashMap<>();
			StringBuilder sb = new StringBuilder(sysInterface.getInfUrl());
			String encryptExpression=sysInterface.getEncryptExpression();
			String stringParms=sysInterface.getInfParms();
			String pwdKeys= encryptExpression.substring(encryptExpression.indexOf("(")+1, encryptExpression.lastIndexOf(")"));
			String pwdType= encryptExpression.substring(1, encryptExpression.lastIndexOf("("));
			JSONObject jsonObj = JSON.parseObject(stringParms);
	        for (Map.Entry<String, Object> entry : jsonObj.entrySet()) {
	        	if(entry.getKey().equals("timestamp"))
	        		map.put("timestamp", Long.toString(System.currentTimeMillis()));
	        	else
	        		map.put(entry.getKey(),entry.getValue().toString());
	        }
	        StringBuilder pwd = new StringBuilder("");
	        String[] pwdKeyArr =pwdKeys.split("[+]");
	        for(int j=0;j<pwdKeyArr.length;j++)
	        {
	        	if(map.get(pwdKeyArr[j])!=null)
		    	 {
		    		 pwd.append(map.get(pwdKeyArr[j]));
		    	 }
	        }
	        if(pwdType.equals("MD5"))
	        {
	        	try {
	        		map.put(sysInterface.getPasswdKey(), EncryptUtil.MD5(pwd.toString(), sysInterface.getEncryptCharset()));
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
	        }
	        else if(pwdType.equals("SHA1"))
	        {
	        	try {
					map.put(sysInterface.getPasswdKey(), EncryptUtil.SHA1(pwd.toString(), sysInterface.getEncryptCharset()));
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
	        }
	        else if(pwdType.equals("SHA256"))
	        {
	        	try {
					map.put(sysInterface.getPasswdKey(), EncryptUtil.SHA256(pwd.toString(), sysInterface.getEncryptCharset()));
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
	        }
	        else
	        {
	        	return ResultBuilderUtil.resultException("2","请选择 MD5,SHA1,SHA256方式加密");
	        }
	        String res = "";
	        /*if(sysInterface.getHttpMethod().equalsIgnoreCase("GET"))
	        	res = HttpClientUtil.httpGetMethod(sb, map);
	        else if(sysInterface.getHttpMethod().equalsIgnoreCase("POST"))
	        	res = HttpClientUtil.httpPostMethod(sb.toString(), map);*/

	        if(StringUtils.isNull(res))
	        	return ResultBuilderUtil.resultException("2","调用远程接口无响应，请稍候重试！");
	        log.debug("收到的接口数据——{}",res);
	        if(sysInterface.getReceivedDataFormat().equalsIgnoreCase("JSON")){
	        	JSONObject resultJson = JSON.parseObject(res);
	        	int status = resultJson.getIntValue("status");
	        	if(1 == status){
	        		try{
	        			/*externalInfService = SpringContextHolder.getBean(sysInterface.getImplBeanName());
        				externalInfService.receivedJsonHandle(resultJson.getJSONArray("data"));*/
	        		}catch(Exception e){
	        			log.error("接口处理类名错误，或者未实例化：{}",sysInterface.getImplBeanName());
	        			return ResultBuilderUtil.resultException("2","接口处理类名错误，或者未实例化："+sysInterface.getImplBeanName());
	        		}
	        	}else{
	        		log.error("接口返回数据错误：{}",resultJson.getString("info"));
	        		return ResultBuilderUtil.resultException("2","接口返回数据错误："+resultJson.getString("info"));
	        	}
	        }else if(sysInterface.getReceivedDataFormat().equalsIgnoreCase("XML")){
	        	log.info("收到的xml数据：{}",res);
	        	/*externalInfService = SpringContextHolder.getBean(sysInterface.getImplBeanName());
        		if(null == externalInfService){
        			log.error("接口处理类名错误，或者未实例化：{}",sysInterface.getImplBeanName());
        		}else{
        			externalInfService.receivedXmlHandle(res);
        		}*/
	        }
			sysInterface.setLastExeTime(new Timestamp(System.currentTimeMillis()));
			getBaseMapper().updateByPrimaryKeySelective(sysInterface);
		}
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	public String isStartInterfaceStatus(String dataIds, Boolean isStart) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataListID", Arrays.asList(dataIds.split(",")));
		map.put("scheduledStatus", isStart);
		((SysInterfaceMapper)getBaseMapper()).updateStatusBatch(map);
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

}
