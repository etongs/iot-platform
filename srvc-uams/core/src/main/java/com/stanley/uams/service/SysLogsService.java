package com.stanley.uams.service;

import com.stanley.common.domain.mybatis.Page;
import com.stanley.common.domain.SearchParam;
import com.stanley.uams.domain.SysLogs;
import com.stanley.uams.domain.SysLogsVO;
import com.stanley.uams.mapper.master.SysLogsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/8/2.
 */
@Service
public class SysLogsService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SysLogsMapper sysLogsMapper;

    public Page<SysLogsVO> getPage(SearchParam searchParam){
        Page<SysLogsVO> page = new Page<SysLogsVO>();
        page.setOffset(searchParam.getOffset());
        page.setLimit(searchParam.getLimit());
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("startDatetime", searchParam.getStartDate());
        map.put("endDatetime", searchParam.getEndDate()+" 23:59:59");
        map.put("funcMenuNm", searchParam.getSelectedId());
        map.put("funcOperNm", searchParam.getSelectedId2());
        map.put("operNm", searchParam.getSearchName());
        map.put("operRemark", searchParam.getSearchName2());
        map.put("sort", searchParam.getSort());
        map.put("order", searchParam.getOrder());
        page.setParams(map);
        page.setRows(sysLogsMapper.selectPage(page));
        return page;
    }

    @Transactional
    public String insert(SysLogs sysLogs) throws Exception {
        for(int i=0;i<10;i++){
            sysLogs.setIdKey(null);
            sysLogs.setOperId(1);
            sysLogs.setOperNm("管理员"+i);
            sysLogs.setOperDt(new Timestamp(System.currentTimeMillis()));
            sysLogsMapper.insert(sysLogs);
        }

        return "{\"status\":1,\"message\":\"操作成功\"}";
    }

    public SysLogs getOne(Integer idKey) {
        return sysLogsMapper.selectByPrimaryKey(idKey);
    }

}
