package com.stanley.uams.web;

import com.stanley.common.dao.MybatisRedisCache;
import com.stanley.common.dao.RedisDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Redis 操作测试
 *
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/8/19
 **/
@RestController
public class TestRedisController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private RedisDao redisDao;

    @RequestMapping(value = "/redisadd")
    public String add(HttpServletResponse response){
        redisDao.add("eacha05", String.valueOf(LocalDateTime.now()));
        redisDao.add("eacha06", String.valueOf(LocalDateTime.now()));
        redisDao.add("eacha07", String.valueOf(LocalDateTime.now()));
        redisDao.add("eacha08", String.valueOf(LocalDateTime.now()));
        logger.debug("添加成功！");
        return "添加成功！";
    }

    @RequestMapping(value = "/redisupdate")
    public String update(HttpServletResponse response,
                         HttpSession session,
                         String key){
        redisDao.set(key, String.valueOf(LocalDateTime.now()));
        logger.info(key+"修改成功！");
        return "修改成功！";
    }

    @RequestMapping(value = "/redisdelete")
    public String delete(HttpServletResponse response,
                         HttpSession session,
                         String key){
        redisDao.delete(key);
        logger.info(key+"删除成功！");
        return "删除成功！";
    }

    @RequestMapping(value = "/redisquery")
    public String query(HttpServletResponse response,
                        HttpSession session,
                        String key){
        logger.info(key+"="+redisDao.get(key));
        return redisDao.get(key);
    }

    @RequestMapping(value = "/listAdd")
    public String listAdd(HttpServletResponse response,
                          int count) throws InterruptedException{
        for(int i=0; i< count; i++){
            byte[] bytes = Integer.toHexString(i).getBytes();
            redisDao.listAdd("receivedData", bytes, false);
            logger.info("receivedData="+new String(bytes));
            Thread.sleep(20);
        }
        return "列表增加成功。。";
    }

    @RequestMapping(value = "/listDelete")
    public String listDelete(HttpServletResponse response,
                             int count){
        redisDao.listRemove("receivedData",count, Integer.toHexString(15).getBytes() );
        return "列表删除成功。。";
    }

    @RequestMapping(value = "/listQuery")
    public String listQuery(HttpServletResponse response,	long begin, long end){
        List<byte[]> list = redisDao.listQuery("receivedData", begin, end);
        for(byte[] bytes: list){
            logger.info("query=" + new String(bytes));
        }
        return  "条数："+redisDao.listLength("receivedData");
    }

}
