package com.qdch.xd.controller;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;



import com.fr.hailian.util.JDBCUtil;
import com.jfinal.upload.UploadFile;
import com.qdch.core.BaseController;
import com.qdch.xd.model.MonthlyReportModel;


/*
* 文件上传
* @author ljm  
* @date 2018年4月16日
* 
*/
public class FileUpLoadController extends BaseController {
	public void index(){
		setAttr("jyslist", MonthlyReportModel.dao.getJys(getDataScopeByUserName()));//在进入页面时就获取权限内表为条件下拉框做准备
		render("xd/pages/06_02yuebaoshangchuan.html");
	}
	//文件上传
	public void upload(){  
        HttpServletRequest request = getRequest();  
        //存储路径  
        String path ="D:/apache-tomcat-7.0.78/webapps/WebReport/pdf-files";  
        UploadFile file = getFile("file");  
        String fileName = "";  
        if(file.getFile().length() > 3*1024*1024) {  
            System.err.println("文件长度超过限制，必须小于3M");  
        }else{  
            //上传文件  
            String type = file.getFileName().substring(file.getFileName().lastIndexOf(".")); // 获取文件的后缀  
            fileName = file.getFileName(); // 对文件重命名取得的文件名+后缀  
            String dest = path + "/" + fileName;  
            file.getFile().renameTo(new File(dest));  
        }  
        String fname = file.getFileName();
        String title = fname;
        fname = title;

        String jys = getPara("jys");
        //获取交易所分类
        int jysfl = 0;
        List<MonthlyReportModel> list = MonthlyReportModel.dao.getJyscfl(jys);
        for (MonthlyReportModel m : list) {
        	jysfl=Integer.parseInt(m.getStr("jysfl"));
		}
        String bglx = getPara("bglx");
        
        String date = new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
        String loginUser = getLoginUser().getUsername();
        
        
		String sql="insert into insight_regulatory_report "
				+ "(order_number,title,upload_time,upload_user,jys,type,file_name,jysfl) "
				+ "values(?,?,?,?,?,?,?,?)";
		
		Object[] params ={null,title,date,loginUser,jys,bglx,fname,jysfl};
		
		JDBCUtil.executeUpdate(sql, params, "insight");
		
		renderJson();  
		
    }  
	
	//文件下载
	public void downLoad(){ 
		String fileName = getPara("fileName");
		
        String downPath = "D:/apache-tomcat-7.0.78/webapps/WebReport/pdf-files";  
       
        File file=new File(downPath+"/"+fileName);  
          
        if(file.exists()){  
        	renderFile(file);

        }  
        else{  
            renderJson();  
        }  
    } 
	
	
	
}
