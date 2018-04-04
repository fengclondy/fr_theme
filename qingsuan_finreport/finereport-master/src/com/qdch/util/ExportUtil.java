package com.qdch.util;

/**
 * wf
 * 2018年4月3日16:28:36
 * 导出
 */
import com.fr.third.org.apache.poi.hssf.util.HSSFColor;
import com.fr.write.WriteException;
import com.jfinal.plugin.activerecord.Model;
import com.qdch.xd.model.RiskEventModel;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.biff.RowsExceededException;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ExportUtil {


    public static void  toexcel(String [] tablename, String [][] content,HttpServletResponse response,String saveName) throws IOException, ClassNotFoundException {
        // 1.创建一个workbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 2.在workbook中添加一个sheet，对应Excel中的一个sheet
        HSSFSheet sheet = wb.createSheet("XXX表");
        // 3.在sheet中添加表头第0行，老版本poi对excel行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 4.创建单元格，设置值表头，设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        // 居中格式
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 设置表头
        HSSFCell cell = null;
        for(int i = 0;i<tablename.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(tablename[i]);
            cell.setCellStyle(style);
        }
//        for (int i = 0; i < lists.size(); i++) {
//            row = sheet.createRow((int) i + 1);
//            Model model = lists.get(i);
//            row.createCell(0).setCellValue(model.getStr("fxsj_id"));
//            row.createCell(1).setCellValue(model.getStr("bjsj"));
//            row.createCell(2).setCellValue(model.getStr("fxlb"));
//        }
        for(int i=0;i<content.length;i++){
            row = sheet.createRow(i + 1);
            for(int j=0;j<content[i].length;j++){
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(content[i][j]);
            }
        }

//        HSSFWorkbook wb = new HSSFWorkbook();

        OutputStream output = null;

        output = response.getOutputStream();
        response.reset();
        String headervar2 = "attachment; filename=\""+new String(saveName.getBytes("UTF-8"),"ISO8859-1")+".xls\"";
        response.setHeader("Content-disposition", headervar2);
        response.setContentType("application/x-download");
        wb.write(output);

//        FileOutputStream out =new FileOutputStream("E:/XXX.xls");
//        wb.write(out);
        output.close();
    }

}
