package com.stanley.uams.web;


import com.stanley.common.domain.mybatis.Page;
import com.stanley.common.domain.SearchParam;
import com.stanley.uams.domain.SysLogs;
import com.stanley.uams.domain.SysLogsVO;
import com.stanley.uams.service.SysLogsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/7/31.
 */
@RestController
public class SysLogsController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SysLogsService sysLogsService;

    @RequestMapping("/selectPage")
    public Page<SysLogsVO> getPage(SearchParam searchParam){
        return sysLogsService.getPage(searchParam);
    }

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public String insert(HttpServletResponse response, SysLogs sysLogs) throws Exception{
        return sysLogsService.insert(sysLogs);
    }

    @RequestMapping("/getOne")
    public SysLogs getOne(Integer idkey){
        return sysLogsService.getOne(idkey);
    }

    /*@RequestMapping("/deleteOne")
    public String dddOne(Integer idKey){
        sysLogsMapper.deleteByPrimaryKey(idKey);
        return "删除成功";
    }

    @RequestMapping("/deleteAll")
    public String ddd(String  dataIds){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dataListID", Arrays.asList(dataIds.split(",")));
        sysLogsMapper.deleteBatch(map);
        return "批量删除成功";
    }*/

}
