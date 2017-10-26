package com.stanley.uams.service;

import com.stanley.common.annotation.WriteLogs;
import com.stanley.common.domain.GeneralConstant;
import com.stanley.common.domain.SearchParam;
import com.stanley.uams.domain.auth.SysLogs;
import com.stanley.uams.service.auth.SysLogsService;
import com.stanley.utils.Constants;
import com.stanley.utils.ShiroSessionUtil;
import com.stanley.utils.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.ibatis.annotations.Delete;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Timestamp;

/**
 * 写日志到数据库
 *
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/10/25
 **/
@Component
@Aspect
public class WriteLogsAspect {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    private ThreadLocal<Long> time=new ThreadLocal<>();
    private ThreadLocal<String> val=new ThreadLocal<>();

    @Resource
    private SysLogsService sysLogsService;

    /**
     * 前置：
     * 切入点为有该注解的方法
     * @author 13346450@qq.com 童晟
     * @date 2016年8月24日
     */
    @Before(value = "@annotation(w)", argNames = "w")
    public void doBefore(WriteLogs w) {
        time.set(System.currentTimeMillis());
        val.set(w.value());
    }

    /**
     * 后置：写日志到数据库
     * 此方法不在原方法的事务中，即此处提交失败不会导致原方法的回滚
     * 执行动作名取注解里面的value值，如果未指定value，则取方法名
     * @param joinPoint
     */
    @After("@annotation(com.stanley.common.annotation.WriteLogs)")
    public void doAfter(JoinPoint joinPoint) {
        SysLogs sysLogs = new SysLogs();
        String controlNm = "其他功能";
        String classNm = joinPoint.getTarget().getClass().getName();
        classNm = classNm.substring(classNm.lastIndexOf(".")+1);
        for(GeneralConstant g : Constants.SYSLOG_CODE_TABLE){
            if(g.getIdKeyString().equals(classNm)){
                controlNm = g.getTextValue();
                break;
            }
        }
        sysLogs.setFuncMenuNm(controlNm);
        String operNm = "其他操作";
        String action = val.get();
        if(StringUtils.isNull(action))
            action = joinPoint.getSignature().getName();

        for(GeneralConstant g : Constants.SYSLOG_CODE_OPERITION){
            if(g.getIdKeyString().equals(action)){
                operNm = g.getTextValue();
                break;
            }
        }
        sysLogs.setFuncOperNm(operNm);
        sysLogs.setOperId(ShiroSessionUtil.getUserId());
        sysLogs.setOperNm(ShiroSessionUtil.getUserAccount());
        sysLogs.setOperDt(new Timestamp(System.currentTimeMillis()));
        StringBuilder remark = new StringBuilder();
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            if(i !=  0)
                remark.append("<br>");
            Object arg = args[i];
            if(arg instanceof SearchParam &&  action.equals(Constants.OPERITION_DELETE_BATCH)){
                remark.append("批量删除的ids：").append(((SearchParam)arg).getCheckedIds());
                continue;
            }
            if(action.equals(Constants.OPERITION_DELETE)){
                remark.append("删除的id：").append(arg);
                continue;
            }
            if(action.equals(Constants.OPERITION_INSERT)){
                remark.append("新增的内容：").append(ReflectionToStringBuilder.toString(arg, ToStringStyle.SHORT_PREFIX_STYLE));
                continue;
            }
            if(action.equals(Constants.OPERITION_UPDATE)){
                remark.append("修改的内容：").append(ReflectionToStringBuilder.toString(arg, ToStringStyle.SHORT_PREFIX_STYLE));
                continue;
            }
            if(action.equals(Constants.OPERITION_TREE_DRAG)){
                if(i==0){
                    remark.append("树形节点拖拽-原节点id").append("：").append(arg);
                }else if(i==1){
                    remark.append("树形节点拖拽-原节点的父节点").append("：").append(arg);
                }else if(i==2){
                    remark.append("树形节点拖拽-目标节点(拖动后的节点)").append("：").append(arg);
                }
                continue;
            }
            if(action.equals("修改自己密码")){
                remark.append(action);
                continue;
            }
            //未知的动作，也要添加
            remark.append(action).append(i).append("：").append(ReflectionToStringBuilder.toString(arg, ToStringStyle.SHORT_PREFIX_STYLE));
        }
        sysLogs.setOperRemark(remark.toString());
        sysLogsService.insert(sysLogs);
        if(log.isDebugEnabled()) {
            log.debug("后置通知 =====> 执行 {}, 记录参数值大小：{}byte, 耗时：{}ms",
                    joinPoint.getTarget().getClass().getName()+"."+joinPoint.getSignature().getName()+"()",
                    remark.length(), System.currentTimeMillis()-time.get());
        }
    }

}
