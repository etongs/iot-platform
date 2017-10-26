package com.stanley.uams.service.basic;

import com.stanley.common.spring.BaseTreeService;
import com.stanley.uams.domain.basic.SysDistrict;
import com.stanley.uams.mapper.master.basic.SysDistrictMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
/**
 * 行政区域管理
 * @Description
 * @date 2016-04-13
 * @since 1.0 
 * @version 1.0  
 * @author 13346450@qq.com 童晟
 */
@Service
public class SysDistrictService extends BaseTreeService<SysDistrict> {
	@Resource
	public void setSysDistrictMapper(SysDistrictMapper sysDistrictMapper) {
		super.setBaseTreeMapper(sysDistrictMapper);
	}
}
