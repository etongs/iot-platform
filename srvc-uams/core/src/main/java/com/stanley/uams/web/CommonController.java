package com.stanley.uams.web;

import com.stanley.common.domain.GeneralConstant;
import com.stanley.common.domain.NavigateMenu;
import com.stanley.common.spring.BaseController;
import com.stanley.uams.domain.basic.SysDict;
import com.stanley.uams.domain.basic.SysDistrict;
import com.stanley.uams.service.CommonService;
import com.stanley.uams.service.auth.SysResourceService;
import com.stanley.uams.service.basic.SysDictService;
import com.stanley.uams.service.basic.SysDistrictService;
import com.stanley.utils.Constants;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 权限系统公共控制器
 *
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/9/7
 **/
@RestController
public class CommonController extends BaseController {

    @Resource
    private CommonService commonService;
    @Resource
    private SysDictService sysDictService;
    @Resource
    private SysDistrictService sysDistrictService;
    @Resource
    private SysResourceService sysResourceService;

    /**
     * 获取某节点下的所有子节点（递归），根节点为1
     * @Description
     * @date 2017年3月28日
     * @author 童晟  13346450@qq.com
     * @param @param request
     * @param @return
     * @return List<EasyUiTreeModel>
     */
    @RequestMapping("/listMyMenu/{rootId}")
    public List<NavigateMenu> listMenuAll(@PathVariable Integer rootId){
        return commonService.listMenuAll(rootId);
    }

    /**
     * @Description 获取数据字典的值
     * @date 2017/9/8
     * @author 13346450@qq.com 童晟
     * @param
     * @return
     */
    @RequestMapping(value="/getDictList/{dictTypeId}")
    public List<SysDict> getDictList(@PathVariable String dictTypeId) {
        return sysDictService.selectAllBySelective(dictTypeId);
    }

    /**
     * 根据地区父节点查找子节点
     * @param idKey
     * @return
     * @author 13346450@qq.com 童晟
     * @date 2016-04-13
     */
    @RequestMapping(value = "/getDistrictChildren/{idKey}")
    public List<SysDistrict> getDistrictChildren(@PathVariable Integer idKey) {
        return sysDistrictService.selectChildrenByParentId(idKey);
    }

    /**
     * 查找地区同级的节点
     * @Description
     * @date 2017年5月4日
     * @author 童晟  13346450@qq.com
     * @param @param idKey
     * @param @return
     * @return List<SysDistrict>
     */
    @RequestMapping(value = "/getDistrictSamelevel/{idKey}")
    public List<SysDistrict> getDistrictSamelevel(@PathVariable Integer idKey) {
        return sysDistrictService.selectSameLevelNodes(idKey);
    }

    /**
     * 一次性加载资源树（除末级按钮外），供树形下拉菜单用
     * @Description
     * @date 2017年4月7日
     * @author 童晟  13346450@qq.com
     * @param @param response
     * @param @return
     * @return List<Map<String,Object>>
     */
    @RequestMapping(value = "/loadResourceTreeExceptButton")
    public List<Map<String, Object>> loadResourceTreeExceptButton() {
        return sysResourceService.loadAllChildrenExceptButton(1);
    }


    /**
     * 获取常量，返回值为List
     * @param request
     * @param constantId
     * @return
     * @author 13346450@qq.com 童晟
     * @date 2016年4月14日
     */
    @RequestMapping(value="getConstantCollection/{constantId}")
    public List<GeneralConstant> getConstantCollection(HttpServletRequest request,
                                                       @PathVariable("constantId") String constantId) {
        List<GeneralConstant> list = null;
        switch (constantId) {
            case "HTTP_METHOD" :
                list = Constants.HTTP_METHOR;
                break;
            case "RECEIVED_DATA_FORMAT" :
                list = Constants.RECEIVED_DATA_FORMAT;
                break;
            case "SYSLOG_CODE_TABLE":
                list = Constants.SYSLOG_CODE_TABLE;
                break;
            case "SYSLOG_CODE_OPERITION":
                list = Constants.SYSLOG_CODE_OPERITION;
                break;
            default:
                break;
        }
        return list;
    }

}
