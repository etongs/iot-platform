package com.stanley.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

/**
 * Excel操作
 *
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/8/31
 **/
public final class ExcelUtil {

    /**
     * 输出到Excel文件
     * @param response
     * @param fileName
     * @param wb
     */
    public static void outputExcel(HttpServletResponse response, String fileName, HSSFWorkbook wb){
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename="
                    +java.net.URLEncoder.encode(fileName, Constants.CHARSET)+"_"
                    + DateTimeUtil.formatDateToStr(new Date(),"number")+".xls");
            OutputStream ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
